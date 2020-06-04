package com.pumpit.app.ui.viewmodel.empty;

import androidx.lifecycle.ViewModel;

import com.pumpit.app.data.repository.UserRepository;

public class LogoutViewModel extends ViewModel {
    private UserRepository userRepository;

    public LogoutViewModel(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void clearCurrentUser(String authority) {
        userRepository.removeUser();
        if (authority != null) {
            switch (authority) {
                case "TRAINER":
                    userRepository.removeTrainer();
                    break;
                case "CLIENT":
                    userRepository.removeClient();
                    break;
                default:
            }
        }
    }


}
