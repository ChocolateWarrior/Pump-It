package com.pumpit.app.ui.view.activity.update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.databinding.ActivityLoginBinding;
import com.pumpit.app.databinding.ActivityUpdateProfileBinding;
import com.pumpit.app.ui.factory.LoginViewModelFactory;
import com.pumpit.app.ui.factory.UpdateProfileViewModelFactory;
import com.pumpit.app.ui.listener.update.UpdateProfileListener;
import com.pumpit.app.ui.viewmodel.registration.LoginViewModel;
import com.pumpit.app.ui.viewmodel.update.UpdateProfileViewModel;
import com.pumpit.app.util.ViewUtils;

public class UpdateProfileActivity extends AppCompatActivity implements UpdateProfileListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final UserRepository repository = new UserRepository(api, db);
        final UpdateProfileViewModelFactory factory = new UpdateProfileViewModelFactory(repository);

        final ActivityUpdateProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile);
        final UpdateProfileViewModel viewModel = new ViewModelProvider(this, factory).get(UpdateProfileViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.setListener(this);
    }

    @Override
    public void toggleFinish() {
        finish();
    }

    @Override
    public void disableClientsAttributes() {
        findViewById(R.id.update_height).setVisibility(View.INVISIBLE);
        findViewById(R.id.update_weight).setVisibility(View.INVISIBLE);
        findViewById(R.id.update_height_label).setVisibility(View.INVISIBLE);
        findViewById(R.id.update_weight_label).setVisibility(View.INVISIBLE);
    }

    @Override
    public void disableTrainerAttributes() {
        findViewById(R.id.update_company).setVisibility(View.INVISIBLE);
    }

    @Override
    public void checkMaleSex() {
        ((RadioButton)findViewById(R.id.update_sex_male)).setChecked(Boolean.TRUE);
        ((RadioButton)findViewById(R.id.update_female_sex)).setChecked(Boolean.FALSE);
    }

    @Override
    public void checkFemaleSex() {
        ((RadioButton)findViewById(R.id.update_sex_male)).setChecked(Boolean.FALSE);
        ((RadioButton)findViewById(R.id.update_female_sex)).setChecked(Boolean.TRUE);
    }

    @Override
    public void onFailure(String message) {
        ViewUtils.showToast(this, message);
    }
}