package com.pumpit.app.ui.listener.registration;

import androidx.lifecycle.LiveData;

public interface LoginListener {
    void onStarted();

    void onSuccess(LiveData<String> loginResponse);

    void onFailure(final String message);
}
