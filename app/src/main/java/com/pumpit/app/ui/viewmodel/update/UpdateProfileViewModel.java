package com.pumpit.app.ui.viewmodel.update;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pumpit.app.data.local.entity.Authority;
import com.pumpit.app.data.local.entity.Client;
import com.pumpit.app.data.local.entity.Sex;
import com.pumpit.app.data.local.entity.Trainer;
import com.pumpit.app.data.local.entity.User;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.listener.update.UpdateProfileListener;

import java.util.Optional;

public class UpdateProfileViewModel extends ViewModel {
    public static final Sex MALE = Sex.MALE;
    public static final Sex FEMALE = Sex.FEMALE;

    private MutableLiveData<String> firstName = new MutableLiveData<>();
    private MutableLiveData<String> lastName = new MutableLiveData<>();
    private MutableLiveData<String> oldPassword = new MutableLiveData<>();
    private MutableLiveData<String> newPassword = new MutableLiveData<>();
    private MutableLiveData<String> newPasswordRepeat = new MutableLiveData<>();
    private MutableLiveData<String> height = new MutableLiveData<>();
    private MutableLiveData<String> weight = new MutableLiveData<>();
    private MutableLiveData<String> company = new MutableLiveData<>();
    private Sex sex;
    private LiveData<User> user;
    private UserRepository userRepository;
    private UpdateProfileListener listener;

    public UpdateProfileViewModel(final UserRepository userRepository) {
        this.userRepository = userRepository;
        user = userRepository.getUser();
        observeUser(user);
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    public void onCancelButton(final View view) {
        System.out.println("HERE");
        listener.toggleFinish();
    }

    public void onUpdateButton(final View view) {

    }

    private void observeUser(final LiveData<User> liveData) {
        liveData.observeForever(this::populateCommonUserData);
    }

    private void populateCommonUserData(final User user) {
        Optional<Authority> authority = user.getAuthorities()
                .stream()
                .findFirst();

        firstName.setValue(user.getFirstName());
        lastName.setValue(user.getLastName());
        sex = user.getSex();

        checkSex(sex);

        authority.ifPresent(this::populateAdditionalUserData);
    }

    private void checkSex(final Sex sex) {
        if (Sex.MALE.equals(sex)) {
            listener.checkMaleSex();
        } else if (Sex.FEMALE.equals(sex)) {
            listener.checkFemaleSex();
        }
    }

    private void populateAdditionalUserData(final Authority authority) {
        if (Authority.CLIENT.equals(authority)) {
            listener.disableTrainerAttributes();
            userRepository.getCurrentClient().observeForever(this::populateClientData);
        } else if (Authority.TRAINER.equals(authority)) {
            listener.disableClientsAttributes();
            userRepository.getCurrentTrainer().observeForever(this::populateTrainerData);
        }
    }

    private void populateClientData(final Client client) {
        height.setValue(String.valueOf(client.getHeight()));
        weight.setValue(String.valueOf(client.getWeight()));
    }

    private void populateTrainerData(final Trainer trainer) {
        company.setValue(trainer.getCompany());
    }

    public MutableLiveData<String> getFirstName() {
        return firstName;
    }

    public void setFirstName(MutableLiveData<String> firstName) {
        this.firstName = firstName;
    }

    public MutableLiveData<String> getLastName() {
        return lastName;
    }

    public void setLastName(MutableLiveData<String> lastName) {
        this.lastName = lastName;
    }

    public MutableLiveData<String> getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(MutableLiveData<String> oldPassword) {
        this.oldPassword = oldPassword;
    }

    public MutableLiveData<String> getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(MutableLiveData<String> newPassword) {
        this.newPassword = newPassword;
    }

    public MutableLiveData<String> getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(MutableLiveData<String> newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }

    public MutableLiveData<String> getHeight() {
        return height;
    }

    public void setHeight(MutableLiveData<String> height) {
        this.height = height;
    }

    public MutableLiveData<String> getWeight() {
        return weight;
    }

    public void setWeight(MutableLiveData<String> weight) {
        this.weight = weight;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UpdateProfileListener getListener() {
        return listener;
    }

    public void setListener(UpdateProfileListener listener) {
        this.listener = listener;
    }

    public MutableLiveData<String> getCompany() {
        return company;
    }

    public void setCompany(MutableLiveData<String> company) {
        this.company = company;
    }
}
