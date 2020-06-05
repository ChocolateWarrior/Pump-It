package com.pumpit.app.ui.viewmodel.qr;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pumpit.app.data.local.entity.Trainer;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.listener.qr.QrScannerListener;

public class QrScannerViewModel extends ViewModel {
    private MutableLiveData<String> trainerName = new MutableLiveData<>();
    private MutableLiveData<String> trainerId = new MutableLiveData<>();
    private Trainer trainer;
    private UserRepository userRepository;
    private QrScannerListener listener;

    public QrScannerViewModel(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void onResult(final String id) {
        userRepository.getTrainerById(Long.parseLong(id))
                .observeForever(trainer -> {
                    trainerName.setValue(trainer.getResponse().getFirstName() + " " + trainer.getResponse().getLastName());
                });
    }

    public MutableLiveData<String> getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(MutableLiveData<String> trainerName) {
        this.trainerName = trainerName;
    }

    public MutableLiveData<String> getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(MutableLiveData<String> trainerId) {
        this.trainerId = trainerId;
    }

    public QrScannerListener getListener() {
        return listener;
    }

    public void setListener(QrScannerListener listener) {
        this.listener = listener;
    }
}
