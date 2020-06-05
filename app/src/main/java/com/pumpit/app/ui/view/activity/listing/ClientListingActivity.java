package com.pumpit.app.ui.view.activity.listing;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.entity.Client;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.databinding.ActivityClientListingBinding;
import com.pumpit.app.ui.factory.ClientListingViewModelFactory;
import com.pumpit.app.ui.listener.listing.ClientListingListener;
import com.pumpit.app.ui.view.adapter.ClientAdapter;
import com.pumpit.app.ui.viewmodel.listing.ClientListingViewModel;
import com.pumpit.app.util.SessionManager;
import com.pumpit.app.util.ViewUtils;

import java.util.List;
import java.util.Objects;

public class ClientListingActivity extends AppCompatActivity implements ClientListingListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ClientListingViewModel viewModel;

    public ClientListingActivity() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final UserRepository repository = new UserRepository(api, db);
        final ClientListingViewModelFactory factory = new ClientListingViewModelFactory(repository);

        super.onCreate(savedInstanceState);
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        long currentTrainerId = getCurrentUserId(sessionManager);
        ActivityClientListingBinding activityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_client_listing);
        RecyclerView recyclerView = activityBinding.recyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this, factory).get(ClientListingViewModel.class);
        viewModel.setListener(this);
        viewModel.getAllClientsOfTrainer(currentTrainerId);

        this.recyclerView = recyclerView;
    }

    private long getCurrentUserId(SessionManager sessionManager) {
        return Long.parseLong(Objects.requireNonNull(sessionManager.getUserDetails().get("userId")));
    }

    @Override
    public void onFailure(String message) {
        ViewUtils.showToast(this, message);
    }

    @Override
    public void setAdapter(LiveData<List<Client>> clients, long currentTrainerId) {
        recyclerAdapter = new ClientAdapter(clients.getValue());
        recyclerView.setAdapter(recyclerAdapter);
    }


}