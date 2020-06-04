package com.pumpit.app.ui.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.pumpit.app.R;
import com.pumpit.app.ui.view.activity.home.HomeActivity;
import com.pumpit.app.ui.view.activity.listing.ClientListingActivity;
import com.pumpit.app.ui.view.activity.registration.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button test;
    private Button home;
    private Button clients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = findViewById(R.id.test);
        test.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)));

        home = findViewById(R.id.home_btn);
        home.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), HomeActivity.class)));

        clients = findViewById(R.id.clients_btn);
        clients.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ClientListingActivity.class)));

    }

}
