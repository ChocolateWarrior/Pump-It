package com.pumpit.app.ui.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.viewmodel.home.HomeViewModel;

public class HomeViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private UserRepository repository;

    public HomeViewModelFactory(final UserRepository userRepository) {
        this.repository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(repository);
    }
}
