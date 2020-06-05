package com.pumpit.app.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.entity.Exercise;
import com.pumpit.app.data.remote.PumpItApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseRepository {

    private static final String TAG = "ExerciseRepository";
    private PumpItApi pumpItApi;
    private PumpItDatabase pumpItDatabase;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private MutableLiveData<List<Exercise>> exercisesMutableLiveData = new MutableLiveData<>();

    public ExerciseRepository(final PumpItApi pumpItApi, final PumpItDatabase pumpItDatabase) {
        this.pumpItApi = pumpItApi;
        this.pumpItDatabase = pumpItDatabase;
    }

    public ExerciseRepository() {
    }

    public MutableLiveData<List<Exercise>> getMutableLiveData() {
        pumpItApi.getAllExercises().enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                List<Exercise> exercises = response.body();
                if (exercises == null) {
                    exercises = new ArrayList<>();
                }
                exercisesMutableLiveData.setValue(exercises);
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
            }
        });
        return exercisesMutableLiveData;
    }
}
