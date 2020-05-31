package com.pumpit.app.ui.view.activity.registration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.databinding.ActivityLoginBinding;
import com.pumpit.app.ui.listener.registration.LoginListener;
import com.pumpit.app.ui.view.utils.ViewUtils;
import com.pumpit.app.ui.viewmodel.registration.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private static final String LOG_IN_STARTED_MESSAGE = "Log In process started";
    private static final String LOG_IN_SUCCESSFUL_MESSAGE = "Log In process finished successfully";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        final LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.setViewmodel(viewModel);

        viewModel.setListener(this);
    }


    @Override
    public void onStarted() {
        ViewUtils.showToast(this, LOG_IN_STARTED_MESSAGE);
    }

    @Override
    public void onSuccess() {
        ViewUtils.showToast(this, LOG_IN_SUCCESSFUL_MESSAGE);
    }

    @Override
    public void onFailure(final String message) {
        ViewUtils.showToast(this, message);
    }
}