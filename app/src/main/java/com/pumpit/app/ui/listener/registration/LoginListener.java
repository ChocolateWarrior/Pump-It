package com.pumpit.app.ui.listener.registration;

import com.pumpit.app.data.local.entity.User;

public interface LoginListener {
    void onStarted();

    void onSuccess(final User userResponse);

    void onFailure(final String message);
}
