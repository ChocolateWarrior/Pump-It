package com.pumpit.app.ui.listener.registration;

public interface FirstStepRegistrationListener {
    void passwordDoNotMatch();
    void updateDateOfBirthLabel(final String text);
    void onFailure(final String message);
}
