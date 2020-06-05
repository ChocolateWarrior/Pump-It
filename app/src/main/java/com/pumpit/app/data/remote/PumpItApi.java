package com.pumpit.app.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.pumpit.app.data.local.entity.Exercise;
import com.pumpit.app.data.remote.dto.ClientResponses;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.remote.response.ClientResponse;
import com.pumpit.app.data.remote.response.LoginResponse;
import com.pumpit.app.data.remote.response.TrainerResponse;

import java.time.LocalDate;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PumpItApi {
    String URL = "http://10.0.2.2:9000";

    static PumpItApi invoke(final InternetConnectionInterceptor interceptor) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                        LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString()))
                .setLenient()
                .create();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();


        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(PumpItApi.class);
    }

    @POST(value = "login")
    Call<LoginResponse> userLogin(@Body JsonObject credentials);

    @POST(value = "clients")
    Call<LoginResponse> signUpClient(@Body JsonObject data);

    @POST(value = "trainers")
    Call<LoginResponse> signUpTrainer(@Body JsonObject data);

    @GET(value = "clients/{id}")
    Call<ClientResponse> getClientById(@Path("id") long id);

    @GET(value = "trainers/{id}")
    Call<TrainerResponse> getTrainerById(@Path("id") long id);

    @GET(value = "trainers/{id}/clients")
    Call<ClientResponses> getClientsForTrainer(@Path("id") long id);

    @GET(value = "exercises")
    Call<List<Exercise>> getAllExercises();
}
