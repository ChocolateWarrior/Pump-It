package com.pumpit.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.response.LoginResponse;

import java.io.IOException;
import java.util.Optional;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    public LiveData<LoginResponse> userLogin(String username, String password) {
        MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

        JsonObject credentials = setupCredentials(username, password);

        PumpItApi.invoke().userLogin(credentials)
                .enqueue(getLoginCallback(loginResponse));

        return loginResponse;
    }

    private Callback<LoginResponse> getLoginCallback(final MutableLiveData<LoginResponse> loginResponse) {
        return new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println("SUCC");
                        loginResponse
                                .setValue(response.body());
                } else {
                    System.out.println("FAIL");
                        loginResponse.setValue(Optional.ofNullable(response.errorBody())
                                .map(body -> LoginResponse.builder().message(body.toString()).build())
                                .orElse(null));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                loginResponse.setValue(LoginResponse.builder().message(t.getMessage()).build());
            }
        };
    }

    private JsonObject setupCredentials(String username, String password) {
        JsonObject credentials = new JsonObject();
        credentials.addProperty("username", username);
        credentials.addProperty("password", password);
        return credentials;
    }
}
