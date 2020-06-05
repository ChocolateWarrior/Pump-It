package com.pumpit.app.ui.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.viewmodel.listing.ClientListingViewModel;

public class ClientListingViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private UserRepository repository;

    public ClientListingViewModelFactory(final UserRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ClientListingViewModel(repository);
    }
}
