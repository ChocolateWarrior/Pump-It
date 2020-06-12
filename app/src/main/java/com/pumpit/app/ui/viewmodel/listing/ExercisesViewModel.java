package com.pumpit.app.ui.viewmodel.listing;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pumpit.app.data.local.entity.Exercise;
import com.pumpit.app.data.repository.ExerciseRepository;

import java.util.List;

public class ExercisesViewModel extends AndroidViewModel {
    private ExerciseRepository exerciseRepository;

    public ExercisesViewModel(@NonNull Application application, ExerciseRepository exerciseRepository) {
        super(application);
        this.exerciseRepository = exerciseRepository;
    }

    public LiveData<List<Exercise>> getAllEmployee() {
        return exerciseRepository.getMutableLiveData();
    }
}
