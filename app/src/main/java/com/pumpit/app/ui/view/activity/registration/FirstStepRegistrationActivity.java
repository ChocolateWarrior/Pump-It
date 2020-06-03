package com.pumpit.app.ui.view.activity.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.databinding.ActivityFirstStepRegistrationBinding;
import com.pumpit.app.ui.factory.FirstStepRegistrationViewModelFactory;
import com.pumpit.app.ui.factory.LoginViewModelFactory;
import com.pumpit.app.ui.listener.registration.FirstStepRegistrationListener;
import com.pumpit.app.ui.view.activity.home.HomeActivity;
import com.pumpit.app.ui.viewmodel.registration.FirstStepRegistrationViewModel;
import com.pumpit.app.util.ViewUtils;

import java.util.Objects;

public class FirstStepRegistrationActivity extends AppCompatActivity implements FirstStepRegistrationListener {

    private static final String PASSWORDS_DO_NOT_MATCH_MESSAGE = "Passwords do not match!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final UserRepository repository = new UserRepository(api, db);
        final FirstStepRegistrationViewModelFactory factory = new FirstStepRegistrationViewModelFactory(repository);

        final ActivityFirstStepRegistrationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_first_step_registration);
        final FirstStepRegistrationViewModel viewModel = new ViewModelProvider(this, factory).get(FirstStepRegistrationViewModel.class);


        binding.setViewmodel(viewModel);
        viewModel.setBinding(binding);

        viewModel.getLoggedInUser().observe(this, user -> {
            if (Objects.nonNull(user)) {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

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

    @Override
    public void trainerSwitchChecked() {
        findViewById(R.id.company).setVisibility(View.VISIBLE);
        ConstraintLayout constraintLayout = findViewById(R.id.firstStepRegistrationActivity);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.sex, ConstraintSet.TOP, R.id.company, ConstraintSet.BOTTOM, 24);
        constraintSet.applyTo(constraintLayout);
    }

    @Override
    public void trainerSwitchUnchecked() {
        findViewById(R.id.company).setVisibility(View.INVISIBLE);
        ConstraintLayout constraintLayout = findViewById(R.id.firstStepRegistrationActivity);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.sex, ConstraintSet.TOP, R.id.trainerFlag, ConstraintSet.BOTTOM, 24);
        constraintSet.applyTo(constraintLayout);
    }

    @Override
    public void onFailure(String message) {
        ViewUtils.showToast(this, message);
    }
}