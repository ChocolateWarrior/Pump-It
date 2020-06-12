package com.pumpit.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.entity.Client;
import com.pumpit.app.data.local.entity.Exercise;
import com.pumpit.app.data.local.entity.Sex;
import com.pumpit.app.data.local.entity.Trainer;
import com.pumpit.app.data.local.entity.User;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.dto.ClientResponses;
import com.pumpit.app.data.remote.response.BasicResponse;
import com.pumpit.app.data.remote.response.ClientResponse;
import com.pumpit.app.data.remote.response.GenericCallback;
import com.pumpit.app.data.remote.response.LoginResponse;
import com.pumpit.app.data.remote.response.TrainerResponse;
import com.pumpit.app.data.remote.response.UpdateResponse;

import java.util.List;

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

    public LiveData<BasicResponse<Void>> updateClient(final long id,
                                                        final String firstName,
                                                        final String lastName,
                                                        final String height,
                                                        final String weight,
                                                        final Sex sex,
                                                        final String oldPassword,
                                                        final String newPassword,
                                                        final String newPasswordRepeat) {

        final MutableLiveData<BasicResponse<Void>> updateResponse = new MutableLiveData<>();

        final JsonObject request = prepareUpdateRequest(firstName, lastName, sex, oldPassword, newPassword, newPasswordRepeat);
        prepareClientUpdateRequest(height, weight, request);

        pumpItApi.updateClient(id, request)
                .enqueue(new GenericCallback<>(updateResponse));

        return updateResponse;
    }

    public LiveData<BasicResponse<Void>> updateTrainer(final long id,
                                                        final String firstName,
                                                        final String lastName,
                                                        final String company,
                                                        final Sex sex,
                                                        final String oldPassword,
                                                        final String newPassword,
                                                        final String newPasswordRepeat) {

        final MutableLiveData<BasicResponse<Void>> updateResponse = new MutableLiveData<>();

        final JsonObject request = prepareUpdateRequest(firstName, lastName, sex, oldPassword, newPassword, newPasswordRepeat);
        prepareTrainerUpdateRequest(company, request);

        pumpItApi.updateTrainer(id, request)
                .enqueue(new GenericCallback<>(updateResponse));

        return updateResponse;
    }

    private JsonObject prepareUpdateRequest(final String firstName,
                                            final String lastName,
                                            final Sex sex,
                                            final String oldPassword,
                                            final String newPassword,
                                            final String newPasswordRepeat) {
        JsonObject request = new JsonObject();
        request.addProperty("firstName", firstName);
        request.addProperty("lastName", lastName);
        request.addProperty("sex", sex.toString());
        request.addProperty("oldPassword", oldPassword);
        request.addProperty("newPassword", newPassword);
        request.addProperty("newPasswordRepeat", newPasswordRepeat);
        return request;
    }

    private void prepareClientUpdateRequest(final String height,
                                           final String weight,
                                           final JsonObject request) {
        request.addProperty("height", height);
        request.addProperty("weight", weight);
    }

    private void prepareTrainerUpdateRequest(final String company,
                                            final JsonObject request) {
        request.addProperty("company", company);
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

    public LiveData<Client> getCurrentClient() {
        return pumpItDatabase.getClientDao().getCurrentClient();
    }

    public LiveData<Trainer> getCurrentTrainer() {
        return pumpItDatabase.getTrainerDao().getCurrentTrainer();
    }

    public void removeUser() {
        pumpItDatabase.getUserDao().removeCurrentUser();
    }

    public void removeClient() {
        pumpItDatabase.getClientDao().removeCurrentUser();
    }

    public void removeTrainer() {
        pumpItDatabase.getTrainerDao().removeCurrentUser();
    }

    public LiveData<BasicResponse<ClientResponses>> getClientsForTrainer(long id) {
        MutableLiveData<BasicResponse<ClientResponses>> clientsResponse = new MutableLiveData<>();
        System.out.println(pumpItApi.getClientsForTrainer(id));
        pumpItApi.getClientsForTrainer(id).enqueue(new GenericCallback<>(clientsResponse));
        return clientsResponse;
    }

    public LiveData<BasicResponse<List<Exercise>>> getAllExercises() {
        MutableLiveData<BasicResponse<List<Exercise>>> exercisesResponse = new MutableLiveData<>();
        pumpItApi.getAllExercises().enqueue(new GenericCallback<>(exercisesResponse));
        return exercisesResponse;
    }
}
