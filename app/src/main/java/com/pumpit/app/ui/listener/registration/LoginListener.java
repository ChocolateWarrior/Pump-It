package com.pumpit.app.ui.listener.registration;

import androidx.lifecycle.LiveData;

import com.pumpit.app.data.remote.response.LoginResponse;

public interface LoginListener {
    void onStarted();

    void onSuccess(LiveData<LoginResponse> loginResponse);

    void onFailure(final String message);
}
