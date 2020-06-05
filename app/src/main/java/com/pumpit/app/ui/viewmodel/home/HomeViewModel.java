package com.pumpit.app.ui.viewmodel.home;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.pumpit.app.data.local.entity.Authority;
import com.pumpit.app.data.local.entity.Client;
import com.pumpit.app.data.local.entity.Trainer;
import com.pumpit.app.data.local.entity.User;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.listener.registration.HomeListener;
import com.pumpit.app.ui.view.activity.update.UpdateProfileActivity;

import java.util.Optional;

public class HomeViewModel extends ViewModel {
    private static final String DEFAULT_DESCRIPTION = "Just a good person";
    private static final String TRAINER_PREFIX = "Trainer: ";
    private static final String HEIGHT_AND_WEIGHT_TITLE = "Height : Weight";
    private static final String TRAINEES_TITLE = "Trainees";

    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<String> optionalInfo = new MutableLiveData<>();
    private MutableLiveData<String> sideNumber = new MutableLiveData<>();
    private MutableLiveData<String> sideTitle = new MutableLiveData<>();
    private MutableLiveData<String> sideMenuName = new MutableLiveData<>();
    private MutableLiveData<String> sideMenuStatus = new MutableLiveData<>();
    private MutableLiveData<String> profilePicturePath = new MutableLiveData<>();
    private UserRepository userRepository;
    private LiveData<User> user;
    private HomeListener listener;

    public HomeViewModel(final UserRepository userRepository) {
        this.userRepository = userRepository;
        user = userRepository.getUser();
        observeUser(user);
    }

    private void observeUser(final LiveData<User> liveData) {
        liveData.observeForever(this::populateCommonUserData);
    }

    private void populateCommonUserData(final User user) {
        if (user == null) {
            return;
        }

        Optional<Authority> authority = user.getAuthorities()
                .stream()
                .findFirst();

        name.setValue(user.getFirstName() + " " + user.getLastName());
        description.setValue(authority
                .map(Authority::toString)
                .orElse(DEFAULT_DESCRIPTION));

        sideMenuStatus.setValue(authority
                .map(Authority::toString)
                .orElse(DEFAULT_DESCRIPTION));

        profilePicturePath.setValue(user.getProfilePicturePath());

        listener.updatePicture(PumpItApi.URL + user.getProfilePicturePath());

        authority.ifPresent(this::populateAdditionalUserData);
    }

    private void populateAdditionalUserData(final Authority authority) {
        if (Authority.CLIENT.equals(authority)) {
            userRepository.getCurrentClient().observeForever(this::populateClientData);
        } else if (Authority.TRAINER.equals(authority)) {
            userRepository.getCurrentTrainer().observeForever(this::populateTrainerData);
        }
    }

    private void populateClientData(final Client client) {
        optionalInfo.setValue(TRAINER_PREFIX + client.getTrainerFirstName() + " " + client.getTrainerLastName());
        sideTitle.setValue(HEIGHT_AND_WEIGHT_TITLE);
        sideNumber.setValue(client.getHeight() + ":" + client.getWeight());
    }

    private void populateTrainerData(final Trainer trainer) {
        optionalInfo.setValue(trainer.getCompany());
        sideTitle.setValue(TRAINEES_TITLE);
        sideNumber.setValue(String.valueOf(trainer.getClientCount()));
    }

    public void onUpdateProfileButton(final View view) {
        view.getContext().startActivity(new Intent(view.getContext(), UpdateProfileActivity.class));
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void setDescription(MutableLiveData<String> description) {
        this.description = description;
    }

    public MutableLiveData<String> getOptionalInfo() {
        return optionalInfo;
    }

    public void setOptionalInfo(MutableLiveData<String> optionalInfo) {
        this.optionalInfo = optionalInfo;
    }

    public MutableLiveData<String> getSideNumber() {
        return sideNumber;
    }

    public void setSideNumber(MutableLiveData<String> sideNumber) {
        this.sideNumber = sideNumber;
    }

    public MutableLiveData<String> getSideTitle() {
        return sideTitle;
    }

    public void setSideTitle(MutableLiveData<String> sideTitle) {
        this.sideTitle = sideTitle;
    }

    public MutableLiveData<String> getSideMenuName() {
        return sideMenuName;
    }

    public void setSideMenuName(MutableLiveData<String> sideMenuName) {
        this.sideMenuName = sideMenuName;
    }

    public MutableLiveData<String> getSideMenuStatus() {
        return sideMenuStatus;
    }

    public void setSideMenuStatus(MutableLiveData<String> sideMenuStatus) {
        this.sideMenuStatus = sideMenuStatus;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void setUser(LiveData<User> user) {
        this.user = user;
    }

    public HomeListener getListener() {
        return listener;
    }

    public void setListener(HomeListener listener) {
        this.listener = listener;
    }

    public MutableLiveData<String> getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(MutableLiveData<String> profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}
