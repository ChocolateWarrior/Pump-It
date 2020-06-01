package com.pumpit.app.ui.view.activity.registration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.databinding.ActivityClientSecondStepRegistrationBinding;
import com.pumpit.app.ui.viewmodel.registration.ClientSecondStepRegistrationViewModel;

public class TrainerSecondStepRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityClientSecondStepRegistrationBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_client_second_step_registration);
        final ClientSecondStepRegistrationViewModel viewModel = new ViewModelProvider(this)
                .get(ClientSecondStepRegistrationViewModel.class);

        binding.setViewmodel(viewModel);
    }
}
