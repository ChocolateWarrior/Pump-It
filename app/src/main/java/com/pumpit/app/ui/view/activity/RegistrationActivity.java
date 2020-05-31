package com.pumpit.app.ui.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.pumpit.app.R;

public class RegistrationActivity extends AppCompatActivity {

    private static final String FIRST_NAME_REQUIRED_MESSAGE = "First name is required!";
    private static final String LAST_NAME_REQUIRED_MESSAGE = "Last name is required!";
    private static final String EMAIL_REQUIRED_MESSAGE = "E-mail is required!";
    private static final String EMAIL_NOT_VALID = "E-mail address is not valid!";
    private static final String PASSWORD_REQUIRED_MESSAGE = "Password is required!";
    private static final String PASSWORDS_DO_NOT_MATCH_MESSAGE = "Passwords do not match!";

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText repeatPassword;
    private Switch trainerFlag;
    private Button continueRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initUI();
        initListeners();
    }

    private void initUI() {
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.repeatPassword);
        trainerFlag = findViewById(R.id.trainerFlag);
        continueRegistration = findViewById(R.id.continueRegistration);
    }

    private void initListeners() {
        setRepeatPasswordListener();
        setContinueRegistrationListener();
    }

    private void checkDataValid() {
        if (isEmpty(firstName)) showMessage(firstName, FIRST_NAME_REQUIRED_MESSAGE);
        if (isEmpty(lastName)) showMessage(lastName, LAST_NAME_REQUIRED_MESSAGE);
        if (isEmpty(email)) showMessage(email, EMAIL_REQUIRED_MESSAGE);
        if (isEmpty(password)) showMessage(password, PASSWORD_REQUIRED_MESSAGE);
        if (!isEmail(email)) showMessage(email, EMAIL_NOT_VALID);
    }

    private boolean isEmpty(final EditText text) {
        return TextUtils.isEmpty(text.getText().toString());
    }

    private void showMessage(final EditText text, final String message) {
        text.setError(message);
    }

    private boolean isEquals(final EditText text1, final EditText text2) {
        return text1.getText().toString()
                .equals(text2.getText().toString());
    }

    private boolean isEmail(final EditText text) {
        return Patterns.EMAIL_ADDRESS.matcher(text.getText().toString()).matches();
    }

    private void clearErrorMessages() {
        firstName.setError(null);
        lastName.setError(null);
        email.setError(null);
        password.setError(null);
        repeatPassword.setError(null);
    }

    private void setRepeatPasswordListener() {
        repeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println(password.getText().toString());
                System.out.println(repeatPassword.getText().toString());
                if (!isEquals(password, repeatPassword)) showMessage(repeatPassword, PASSWORDS_DO_NOT_MATCH_MESSAGE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setContinueRegistrationListener() {
        continueRegistration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clearErrorMessages();
                checkDataValid();
            }
        });
    }
}