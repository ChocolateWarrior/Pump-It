package com.pumpit.app.ui.listener.registration;

public interface LoginListener {
    void onStarted();

    void onSuccess();

    void onFailure(final String message);
}
