package com.pumpit.app.ui.viewmodel.qr;

import androidx.lifecycle.ViewModel;

import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.listener.qr.QrGeneratorListener;

public class QrGeneratorViewModel extends ViewModel {
    private UserRepository userRepository;
    private QrGeneratorListener listener;

    public QrGeneratorViewModel(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void generate() {
        userRepository.getCurrentTrainer().observeForever(trainer -> listener.showQrForId(trainer.getId()));
    }

    public QrGeneratorListener getListener() {
        return listener;
    }

    public void setListener(QrGeneratorListener listener) {
        this.listener = listener;
    }
}
