package com.pumpit.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.entity.Client;
import com.pumpit.app.data.local.entity.Sex;
import com.pumpit.app.data.local.entity.Trainer;
import com.pumpit.app.data.local.entity.User;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.response.BasicResponse;
import com.pumpit.app.data.remote.response.ClientResponse;
import com.pumpit.app.data.remote.response.GenericCallback;
import com.pumpit.app.data.remote.response.LoginResponse;
import com.pumpit.app.data.remote.response.TrainerResponse;

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

        JsonObject request = setupSignUpRequest(username,
                firstName,
                lastName,
                dateOfBirth,
                password,
                sex);

        pumpItApi.signUpClient(request)
                .enqueue(new GenericCallback<>(loginResponse));

        return loginResponse;
    }

    public LiveData<BasicResponse<LoginResponse>> signUpTrainer(final String username,
                                                               final String firstName,
                                                               final String lastName,
                                                               final String dateOfBirth,
                                                               final String password,
                                                               final Sex sex,
                                                               final String company) {

        MutableLiveData<BasicResponse<LoginResponse>> loginResponse = new MutableLiveData<>();

        JsonObject request = setupSignUpRequest(username,
                firstName,
                lastName,
                dateOfBirth,
                password,
                sex);

        setupSignUpTrainerRequest(request, company);

        pumpItApi.signUpTrainer(request)
                .enqueue(new GenericCallback<>(loginResponse));

        return loginResponse;
    }

    public LiveData<BasicResponse<TrainerResponse>> getTrainerById(long id) {
        MutableLiveData<BasicResponse<TrainerResponse>> basicTrainerResponse = new MutableLiveData<>();

        pumpItApi.getTrainerById(id)
                .enqueue(new GenericCallback<>(basicTrainerResponse));

        return basicTrainerResponse;
    }

    public LiveData<BasicResponse<ClientResponse>> getClientById(long id) {
        MutableLiveData<BasicResponse<ClientResponse>> basicClientResponse = new MutableLiveData<>();

        pumpItApi.getClientById(id)
                .enqueue(new GenericCallback<>(basicClientResponse));

        return basicClientResponse;
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

    private JsonObject setupSignUpRequest(final String username,
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

    private void setupSignUpTrainerRequest(final JsonObject request, final String company) {
        request.addProperty("password", company);
    }

    public void saveClient(Client client) {
        pumpItDatabase.getClientDao().save(client);
    }

    public void saveTrainer(Trainer trainer) {
        pumpItDatabase.getTrainerDao().save(trainer);
    }
}
