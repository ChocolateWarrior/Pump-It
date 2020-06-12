package com.pumpit.app.ui.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.viewmodel.registration.LoginViewModel;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private UserRepository repository;

    public LoginViewModelFactory(final UserRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(repository);
    }
}
