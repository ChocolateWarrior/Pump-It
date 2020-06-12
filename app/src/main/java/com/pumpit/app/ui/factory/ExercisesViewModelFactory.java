package com.pumpit.app.ui.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.data.repository.ExerciseRepository;
import com.pumpit.app.ui.viewmodel.listing.ExercisesViewModel;

public class ExercisesViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ExerciseRepository repository;
    private Application application;

    public ExercisesViewModelFactory(Application application, final ExerciseRepository repository) {
        this.application = application;
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ExercisesViewModel(application, repository);
    }
}
