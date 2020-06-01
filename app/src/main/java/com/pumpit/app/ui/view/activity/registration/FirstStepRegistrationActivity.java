package com.pumpit.app.ui.view.activity.registration;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.databinding.ActivityFirstStepRegistrationBinding;
import com.pumpit.app.ui.listener.registration.FirstStepRegistrationListener;
import com.pumpit.app.ui.viewmodel.registration.FirstStepRegistrationViewModel;

public class FirstStepRegistrationActivity extends AppCompatActivity implements FirstStepRegistrationListener {

    private static final String PASSWORDS_DO_NOT_MATCH_MESSAGE = "Passwords do not match!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityFirstStepRegistrationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_first_step_registration);
        final FirstStepRegistrationViewModel viewModel = new ViewModelProvider(this).get(FirstStepRegistrationViewModel.class);

        binding.setViewmodel(viewModel);

        viewModel.setListener(this);
    }

    @Override
    public void passwordDoNotMatch() {
        ((EditText)findViewById(R.id.repeatPassword)).setError(PASSWORDS_DO_NOT_MATCH_MESSAGE);
    }

    @Override
    public void updateDateOfBirthLabel(final String text) {
        ((EditText)findViewById(R.id.dateOfBirth)).setText(text);
    }
}