package com.pumpit.app.data.repository;

import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.entity.Exercise;
import com.pumpit.app.data.remote.PumpItApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import retrofit2.Call;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseRepositoryTest {
    @InjectMocks
    private ExerciseRepository testedInstance;
    @Mock
    private PumpItApi pumpItApi;
    @Mock
    private PumpItDatabase pumpItDatabase;
    @Mock
    private Call<List<Exercise>> listCall;

    @Before
    public void setUp() throws Exception {
        when(pumpItApi.getAllExercises()).thenReturn(listCall);
        doNothing().when(listCall).enqueue(any());
    }

    @Test
    public void shouldCallPumpItApiWhenGetMutableData() {
        testedInstance.getMutableLiveData();

        verify(pumpItApi).getAllExercises();
    }
}