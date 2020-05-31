package com.pumpit.app.ui.view.activity.registration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.pumpit.app.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FirstStepRegistrationActivity extends AppCompatActivity {

    private static final String FIRST_NAME_REQUIRED_MESSAGE = "First name is required!";
    private static final String LAST_NAME_REQUIRED_MESSAGE = "Last name is required!";
    private static final String EMAIL_REQUIRED_MESSAGE = "E-mail is required!";
    private static final String EMAIL_NOT_VALID = "E-mail address is not valid!";
    private static final String PASSWORD_REQUIRED_MESSAGE = "Password is required!";
    private static final String PASSWORDS_DO_NOT_MATCH_MESSAGE = "Passwords do not match!";
    private static final String DATE_OF_BIRTH_REQUIRED_MESSAGE = "Date of Birth is required!";

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText repeatPassword;
    private EditText dateOfBirth;
    private Switch trainerFlag;
    private Button continueRegistration;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step_registration);

        initUI();
        initListeners();
    }

    private void updateLabel() {
        String dateFormat = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());

        dateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));
    }


    private void initUI() {
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.repeatPassword);
        trainerFlag = findViewById(R.id.trainerFlag);
        continueRegistration = findViewById(R.id.continueRegistration);
        dateOfBirth = findViewById(R.id.dateOfBirth);
    }

    private void initListeners() {
        setRepeatPasswordListener();
        setContinueRegistrationListener();
        setDateOfBirthListener();
    }

    private boolean checkDataValid() {
        boolean valid = true;
        if (isEmpty(firstName)) {
            showMessage(firstName, FIRST_NAME_REQUIRED_MESSAGE);
            valid = false;
        }
        if (isEmpty(lastName)) {
            showMessage(lastName, LAST_NAME_REQUIRED_MESSAGE);
            valid = false;
        }
        if (isEmpty(email)) {
            showMessage(email, EMAIL_REQUIRED_MESSAGE);
            valid = false;
        }
        if (isEmpty(password)) {
            showMessage(password, PASSWORD_REQUIRED_MESSAGE);
            valid = false;
        }
        if (!isEmail(email)) {
            showMessage(email, EMAIL_NOT_VALID);
            valid = false;
        }
        if (isEmpty(dateOfBirth)) {
            showMessage(dateOfBirth, DATE_OF_BIRTH_REQUIRED_MESSAGE);
            valid = false;
        }
        return valid;
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
                if (!isEquals(password, repeatPassword))
                    showMessage(repeatPassword, PASSWORDS_DO_NOT_MATCH_MESSAGE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setDateOfBirthListener() {
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FirstStepRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                updateLabel();
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setContinueRegistrationListener() {
        continueRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearErrorMessages();
                boolean isValid = checkDataValid();
                if (isValid) {
                    Intent trainerSecondStepRegistration = new Intent(getApplicationContext(),
                            TrainerSecondStepRegistrationActivity.class);
                    Intent clientSecondStepRegistration = new Intent(getApplicationContext(),
                            ClientSecondStepRegistrationActivity.class);
                    fillSecondStepIntentExtras(trainerSecondStepRegistration);
                    fillSecondStepIntentExtras(clientSecondStepRegistration);

                    if (trainerFlag.isChecked()) {
                        startActivity(trainerSecondStepRegistration);
                    } else {
                        startActivity(clientSecondStepRegistration);
                    }
                }
            }
        });
    }

    private void fillSecondStepIntentExtras(Intent secondStepRegistrationIntent) {
        secondStepRegistrationIntent.putExtra("firstName", firstName.getText());
        secondStepRegistrationIntent.putExtra("lastName", lastName.getText());
        secondStepRegistrationIntent.putExtra("email", email.getText());
        secondStepRegistrationIntent.putExtra("password", password.getText());
        secondStepRegistrationIntent.putExtra("dateOfBirth", dateOfBirth.getText());
    }
}