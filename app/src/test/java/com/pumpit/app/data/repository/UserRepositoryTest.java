package com.pumpit.app.data.repository;

import com.google.gson.JsonObject;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.dao.ClientDao;
import com.pumpit.app.data.local.entity.Client;
import com.pumpit.app.data.local.entity.Sex;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.response.LoginResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import retrofit2.Call;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
    private static final String DEFAULT_STRING = "";
    private static final Sex DEFAULT_SEX = Sex.MALE;

    @InjectMocks
    private UserRepository testedInstance;
    @Mock
    private PumpItApi pumpItApi;
    @Mock
    private PumpItDatabase pumpItDatabase;
    @Mock
    private Client client;
    @Mock
    private ClientDao clientDao;
    @Mock
    private Call<LoginResponse> loginResponseCall;

    @Test
    public void whenSignUpClientShouldCallApi() {
        when(pumpItApi.signUpClient(any(JsonObject.class))).thenReturn(loginResponseCall);
        testedInstance.signUpClient(DEFAULT_STRING,DEFAULT_STRING,DEFAULT_STRING,DEFAULT_STRING,DEFAULT_STRING, DEFAULT_SEX);


        verify(pumpItApi).signUpClient(any(JsonObject.class));
        verify(loginResponseCall).enqueue(any());
    }

    @Test
    public void whenSaveClientShouldCallLocalDatabase() {
        when(pumpItDatabase.getClientDao()).thenReturn(clientDao);
        testedInstance.saveClient(client);

        verify(pumpItDatabase).getClientDao();
        verify(clientDao).save(client);
    }
}