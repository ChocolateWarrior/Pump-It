package com.pumpit.app.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pumpit.app.R;
import com.pumpit.app.ui.view.activity.registration.FirstStepRegistrationActivity;

public class MainActivity extends AppCompatActivity {

    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = findViewById(R.id.test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FirstStepRegistrationActivity.class));
            }
        });
    }
}
