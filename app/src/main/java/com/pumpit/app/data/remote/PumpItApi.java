package com.pumpit.app.data.remote;

import com.google.gson.JsonObject;
import com.pumpit.app.data.remote.response.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PumpItApi {
    static PumpItApi invoke() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.107:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PumpItApi.class);
    }

    @POST(value = "login")
    Call<LoginResponse> userLogin(@Body JsonObject credentials);

    @GET("{id}")
    Call<ResponseBody> getUser(@Path("id") int id);
}
