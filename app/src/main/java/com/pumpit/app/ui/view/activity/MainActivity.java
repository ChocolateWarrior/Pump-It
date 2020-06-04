package com.pumpit.app.ui.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pumpit.app.R;
import com.pumpit.app.ui.view.activity.home.HomeActivity;
import com.pumpit.app.ui.view.activity.registration.LoginActivity;
import com.pumpit.app.util.SessionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        String userId = sessionManager.getUserDetails().get(SessionManager.KEY_USER_ID);
        if (userId != null) {
            startActivity(HomeActivity.class);
        } else {
            startActivity(LoginActivity.class);
        }
    }

    private void startActivity(Class<?> homeActivityClass) {
        Intent intent = new Intent(this, homeActivityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
