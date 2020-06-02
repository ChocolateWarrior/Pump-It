package com.pumpit.app.ui.viewmodel.registration;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pumpit.app.data.remote.response.LoginResponse;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.listener.registration.LoginListener;
import com.pumpit.app.ui.view.activity.registration.FirstStepRegistrationActivity;

public class LoginViewModel extends ViewModel {
    private static final String INVALID_CREDENTIALS = "Username or password is invalid.";

    private String username;
    private String password;
    private LoginListener listener;
    private UserRepository userRepository;

    public void onLoginButtonClick(final View view) {
        listener.onStarted();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            listener.onFailure(INVALID_CREDENTIALS);
            return;
        }

        LiveData<LoginResponse> loginResponse = userRepository.userLogin(username, password);
        listener.onSuccess(loginResponse);
    }

    public void onRegisterButtonClick(final View view) {
        view.getContext()
                .startActivity(new Intent(view.getContext(), FirstStepRegistrationActivity.class));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginListener getListener() {
        return listener;
    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
