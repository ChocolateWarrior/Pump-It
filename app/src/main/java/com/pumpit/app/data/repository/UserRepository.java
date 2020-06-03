package com.pumpit.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.entity.Sex;
import com.pumpit.app.data.local.entity.User;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.response.BasicResponse;
import com.pumpit.app.data.remote.response.GenericCallback;
import com.pumpit.app.data.remote.response.LoginResponse;

public class UserRepository {

    private PumpItApi pumpItApi;
    private PumpItDatabase pumpItDatabase;

    public UserRepository(final PumpItApi pumpItApi, final PumpItDatabase pumpItDatabase) {
        this.pumpItApi = pumpItApi;
        this.pumpItDatabase = pumpItDatabase;
    }

    public LiveData<BasicResponse<LoginResponse>> userLogin(String username, String password) {
        MutableLiveData<BasicResponse<LoginResponse>> loginResponse = new MutableLiveData<>();

        JsonObject credentials = setupCredentials(username, password);

        pumpItApi.userLogin(credentials)
                .enqueue(new GenericCallback<>(loginResponse));

        return loginResponse;
    }

    public LiveData<BasicResponse<LoginResponse>> signUpClient(final String username,
                                                               final String firstName,
                                                               final String lastName,
                                                               final String dateOfBirth,
                                                               final String password,
                                                               final Sex sex) {
        MutableLiveData<BasicResponse<LoginResponse>> loginResponse = new MutableLiveData<>();

        JsonObject request = setupSignUpClientRequest(username,
                firstName,
                lastName,
                dateOfBirth,
                password,
                sex);

        pumpItApi.signUpClient(request)
                .enqueue(new GenericCallback<>(loginResponse));

        return loginResponse;
    }

    public void saveUser(final User user) {
        pumpItDatabase.getUserDao().save(user);
    }

    public LiveData<User> getUser() {
        return pumpItDatabase.getUserDao().getCurrentUser();
    }

    private JsonObject setupCredentials(String username, String password) {
        JsonObject credentials = new JsonObject();
        credentials.addProperty("username", username);
        credentials.addProperty("password", password);
        return credentials;
    }

    private JsonObject setupSignUpClientRequest(final String username,
                                                final String firstName,
                                                final String lastName,
                                                final String dateOfBirth,
                                                final String password,
                                                final Sex sex) {
        JsonObject request = new JsonObject();
        request.addProperty("username", username);
        request.addProperty("firstName", firstName);
        request.addProperty("lastName", lastName);
        request.addProperty("dateOfBirth", dateOfBirth);
        request.addProperty("password", password);
        request.addProperty("sex", sex.toString());
        return request;
    }
}
