package com.pumpit.app.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.pumpit.app.data.remote.response.ClientResponse;
import com.pumpit.app.data.remote.response.LoginResponse;

import java.time.LocalDate;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PumpItApi {
    static PumpItApi invoke() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                        LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString()))
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(PumpItApi.class);
    }

    @POST(value = "login")
    Call<LoginResponse> userLogin(@Body JsonObject credentials);

    @GET(value = "clients/{id}")
    Call<ClientResponse> getClientById(@Path("id") int id);

    @GET(value = "trainers/{id}")
    Call<ClientResponse> getTrainerById(@Path("id") int id);

}
