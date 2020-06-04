package com.pumpit.app.ui.view.activity.empty;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.databinding.ActivityLogoutBinding;
import com.pumpit.app.ui.factory.LogoutViewModelFactory;
import com.pumpit.app.ui.view.activity.registration.LoginActivity;
import com.pumpit.app.ui.viewmodel.empty.LogoutViewModel;
import com.pumpit.app.util.SessionManager;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final UserRepository repository = new UserRepository(api, db);
        final LogoutViewModelFactory factory = new LogoutViewModelFactory(repository);

        final ActivityLogoutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_logout);
        final LogoutViewModel viewModel = new ViewModelProvider(this, factory).get(LogoutViewModel.class);

        binding.setViewmodel(viewModel);
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        String authority = sessionManager.getUserDetails().get(SessionManager.KEY_AUTH_TYPE);
        viewModel.clearCurrentUser(authority);
        sessionManager.clearLoginSession();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}