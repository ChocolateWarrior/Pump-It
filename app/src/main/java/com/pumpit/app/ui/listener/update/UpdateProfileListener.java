package com.pumpit.app.ui.listener.update;

public interface UpdateProfileListener {
    void toggleFinish();
    void disableClientsAttributes();
    void disableTrainerAttributes();
    void checkMaleSex();
    void checkFemaleSex();
    void onFailure(String message);
}
