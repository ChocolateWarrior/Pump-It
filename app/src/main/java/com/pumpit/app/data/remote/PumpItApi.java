package com.pumpit.app.data.remote;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PumpItApi {
    static PumpItApi invoke() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.100:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PumpItApi.class);
    }

    @FormUrlEncoded
    @POST(value = "login")
    Call<ResponseBody> userLogin(
            @Field("username") String username,
            @Field("password") String password);
}
