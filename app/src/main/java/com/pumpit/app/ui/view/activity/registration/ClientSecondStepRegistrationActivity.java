package com.pumpit.app.ui.view.activity.registration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.databinding.ActivityTrainerSecondStepRegistrationBinding;
import com.pumpit.app.ui.viewmodel.registration.TrainerSecondStepRegistrationViewModel;

public class ClientSecondStepRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityTrainerSecondStepRegistrationBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_trainer_second_step_registration);
        final TrainerSecondStepRegistrationViewModel viewModel = new ViewModelProvider(this)
                .get(TrainerSecondStepRegistrationViewModel.class);

        binding.setViewmodel(viewModel);
    }
}
