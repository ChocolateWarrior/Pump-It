package com.pumpit.app.ui.view.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationView;
import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.databinding.ActivityHomeBinding;
import com.pumpit.app.databinding.NavHeaderBinding;
import com.pumpit.app.ui.factory.HomeViewModelFactory;
import com.pumpit.app.ui.listener.registration.HomeListener;
import com.pumpit.app.ui.view.activity.empty.LogoutActivity;
import com.pumpit.app.ui.view.activity.listing.ClientListingActivity;
import com.pumpit.app.ui.view.activity.listing.ExercisesActivity;
import com.pumpit.app.ui.view.activity.listing.TrainingsActivity;
import com.pumpit.app.ui.viewmodel.home.HomeViewModel;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final UserRepository repository = new UserRepository(api, db);
        final HomeViewModelFactory factory = new HomeViewModelFactory(repository);

        final ActivityHomeBinding activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        final NavHeaderBinding navHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header, activityHomeBinding.navView, false);

        activityHomeBinding.navView.addHeaderView(navHeaderBinding.getRoot());

        final HomeViewModel viewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);

        activityHomeBinding.setViewmodel(viewModel);
        activityHomeBinding.setLifecycleOwner(this);
        navHeaderBinding.setViewmodel(viewModel);
        navHeaderBinding.setLifecycleOwner(this);
        viewModel.setListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_clients:
                startActivity(new Intent(getApplicationContext(), ClientListingActivity.class));
                break;
            case R.id.nav_exercises:
                startActivity(new Intent(getApplicationContext(), ExercisesActivity.class));
                break;
            case R.id.nav_trainings:
                startActivity(new Intent(getApplicationContext(), TrainingsActivity.class));
                break;
            case R.id.nav_about:
                Toast.makeText(this, "MARCHEK, ROMCHEK, YURCHEK", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(getApplicationContext(), LogoutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}