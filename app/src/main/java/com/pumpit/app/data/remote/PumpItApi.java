package com.pumpit.app.data.remote;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PumpItApi {
    static PumpItApi invoke() {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PumpItApi.class);
    }

    @POST(value = "login")
    Call<ResponseBody> userLogin(@Body JsonObject credentials);
}
