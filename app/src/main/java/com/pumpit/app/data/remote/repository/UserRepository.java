package com.pumpit.app.data.remote.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.pumpit.app.data.remote.PumpItApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    public LiveData<String> userLogin(String username, String password) {
        MutableLiveData<String> loginResponse = new MutableLiveData<>();

        JsonObject credentials = setupCredentials(username, password);

        PumpItApi.invoke().userLogin(credentials)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                loginResponse
                                        .setValue(response.body() != null ? response.body().string() : null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                loginResponse
                                        .setValue(response.errorBody() != null ? response.errorBody().string() : null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loginResponse.setValue(t.getMessage());
                    }
                });

        return loginResponse;
    }

    private JsonObject setupCredentials(String username, String password) {
        JsonObject credentials = new JsonObject();
        credentials.addProperty("username", username);
        credentials.addProperty("password", password);
        return credentials;
    }
}
