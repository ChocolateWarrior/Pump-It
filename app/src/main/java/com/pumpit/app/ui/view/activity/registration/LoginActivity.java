package com.pumpit.app.ui.view.activity.registration;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.entity.Authority;
import com.pumpit.app.data.local.entity.User;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.databinding.ActivityLoginBinding;
import com.pumpit.app.ui.factory.LoginViewModelFactory;
import com.pumpit.app.ui.listener.registration.LoginListener;
import com.pumpit.app.ui.view.activity.home.HomeActivity;
import com.pumpit.app.ui.viewmodel.registration.LoginViewModel;
import com.pumpit.app.util.SessionManager;
import com.pumpit.app.util.ViewUtils;

import java.util.Optional;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private static final String LOG_IN_STARTED_MESSAGE = "Log In process started";
    private static final String LOG_IN_SUCCESSFUL_MESSAGE = "Log In process finished successfully";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final UserRepository repository = new UserRepository(api, db);
        final LoginViewModelFactory factory = new LoginViewModelFactory(repository);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        final LoginViewModel viewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);

        binding.setViewmodel(viewModel);

        viewModel.setListener(this);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    @Override
    public void onStarted() {
        ViewUtils.showProgressBar(findViewById(R.id.login_progress_bar));
    }

    @Override
    public void onSuccess(User user) {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.createLoginSession(String.valueOf(user.getId()), getUserAuthorityName(user));
        ViewUtils.hideProgressBar(findViewById(R.id.login_progress_bar));
        startHomeActivity();
    }

    @Override
    public void onFailure(final String message) {
        ViewUtils.hideProgressBar(findViewById(R.id.login_progress_bar));
        ViewUtils.showToast(this, message);
    }

    private String getUserAuthorityName(User user) {
        Optional<Authority> authorityOptional = user.getAuthorities().stream().findFirst();
        String authority = null;
        if (authorityOptional.isPresent()) {
            authority = authorityOptional.get().name();
        }
        return authority;
    }
}