package com.pumpit.app.ui.view.activity.registration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.data.remote.repository.UserRepository;
import com.pumpit.app.databinding.ActivityLoginBinding;
import com.pumpit.app.ui.listener.registration.LoginListener;
import com.pumpit.app.ui.viewmodel.registration.LoginViewModel;
import com.pumpit.app.util.ViewUtils;

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
        viewModel.setUserRepository(new UserRepository());
    }


    @Override
    public void onStarted() {
        //TODO Find a way to remove call findViewById method
        ViewUtils.showProgressBar(findViewById(R.id.login_progress_bar));
    }

    @Override
    public void onSuccess(LiveData<String> loginResponse) {
        loginResponse.observe(this, s -> {
            ViewUtils.hideProgressBar(findViewById(R.id.login_progress_bar));
            ViewUtils.showToast(LoginActivity.this, s);
        });
    }

    @Override
    public void onFailure(final String message) {
        ViewUtils.hideProgressBar(findViewById(R.id.login_progress_bar));
        ViewUtils.showToast(this, message);
    }
}