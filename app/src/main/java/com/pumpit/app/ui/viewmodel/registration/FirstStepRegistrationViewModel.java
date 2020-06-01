package com.pumpit.app.ui.viewmodel.registration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;

import androidx.lifecycle.ViewModel;

import com.pumpit.app.databinding.ActivityFirstStepRegistrationBinding;
import com.pumpit.app.ui.listener.registration.FirstStepRegistrationListener;
import com.pumpit.app.ui.view.activity.registration.ClientSecondStepRegistrationActivity;
import com.pumpit.app.ui.view.activity.registration.TrainerSecondStepRegistrationActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FirstStepRegistrationViewModel extends ViewModel {
    private static final String FIRST_NAME_REQUIRED_MESSAGE = "First name is required!";
    private static final String LAST_NAME_REQUIRED_MESSAGE = "Last name is required!";
    private static final String EMAIL_REQUIRED_MESSAGE = "E-mail is required!";
    private static final String EMAIL_NOT_VALID = "E-mail address is not valid!";
    private static final String PASSWORD_REQUIRED_MESSAGE = "Password is required!";
    private static final String DATE_OF_BIRTH_REQUIRED_MESSAGE = "Date of Birth is required!";

    private String  firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
    private String dateOfBirth;
    private Boolean trainerFlag;
    private Calendar calendar = Calendar.getInstance();
    private FirstStepRegistrationListener listener;

    public void onContinueRegistrationButtonClick(final View view) {
        clearErrorMessages(view);
        boolean isValid = checkDataValid(view);
        if (isValid) {
            if (trainerFlag) {
                Intent trainerSecondStepRegistration = new Intent(view.getContext(),
                        TrainerSecondStepRegistrationActivity.class);
                fillSecondStepIntentExtras(trainerSecondStepRegistration);
                view.getContext().startActivity(trainerSecondStepRegistration);
            } else {
                Intent clientSecondStepRegistration = new Intent(view.getContext(),
                        ClientSecondStepRegistrationActivity.class);
                fillSecondStepIntentExtras(clientSecondStepRegistration);
                view.getContext().startActivity(clientSecondStepRegistration);
            }
        }
    }

    public void onDateOfBirthListener(final View view) {
        new DatePickerDialog(view.getContext(),
                (datePicker, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onRepeatPasswordTextChanged(CharSequence s, int start, int before, int count) {
        if (!password.equals(repeatPassword)) listener.passwordDoNotMatch();
    }

    public void onTrainerFlagChanged(final CompoundButton button, final boolean checked) {
        trainerFlag = checked;
    }

    private void updateLabel() {
        String dateFormat = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());

        listener.updateDateOfBirthLabel(simpleDateFormat.format(calendar.getTime()));
    }

    private boolean checkDataValid(final View view) {
        boolean valid = true;
        ActivityFirstStepRegistrationBinding binding = ActivityFirstStepRegistrationBinding.bind(view);
        if (TextUtils.isEmpty(firstName)) {
            binding.firstName.setError(FIRST_NAME_REQUIRED_MESSAGE);
            valid = false;
        }
        if (TextUtils.isEmpty(lastName)) {
            binding.lastName.setError(LAST_NAME_REQUIRED_MESSAGE);
            valid = false;
        }
        if (TextUtils.isEmpty(email)) {
            binding.email.setError(EMAIL_REQUIRED_MESSAGE);
            valid = false;
        }
        if (TextUtils.isEmpty(password)) {
            binding.password.setError(PASSWORD_REQUIRED_MESSAGE);
            valid = false;
        }
        if (!isEmail(email)) {
            binding.email.setError(EMAIL_NOT_VALID);
            valid = false;
        }
        if (TextUtils.isEmpty(dateOfBirth)) {
            binding.dateOfBirth.setError(DATE_OF_BIRTH_REQUIRED_MESSAGE);
            valid = false;
        }
        return valid;
    }

    private void clearErrorMessages(final View view) {
        ActivityFirstStepRegistrationBinding binding = ActivityFirstStepRegistrationBinding.bind(view);

        binding.firstName.setError(null);
        binding.lastName.setError(null);
        binding.email.setError(null);
        binding.password.setError(null);
        binding.repeatPassword.setError(null);
        binding.dateOfBirth.setError(null);
    }

    private boolean isEmail(final String text) {
        return Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

    private void fillSecondStepIntentExtras(Intent secondStepRegistrationIntent) {
        secondStepRegistrationIntent.putExtra("firstName", firstName);
        secondStepRegistrationIntent.putExtra("lastName", lastName);
        secondStepRegistrationIntent.putExtra("email", email);
        secondStepRegistrationIntent.putExtra("password", password);
        secondStepRegistrationIntent.putExtra("dateOfBirth", dateOfBirth);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public boolean getTrainerFlag() {
        return trainerFlag;
    }

    public void setTrainerFlag(boolean trainerFlag) {
        this.trainerFlag = trainerFlag;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public FirstStepRegistrationListener getListener() {
        return listener;
    }

    public void setListener(FirstStepRegistrationListener listener) {
        this.listener = listener;
    }
}
