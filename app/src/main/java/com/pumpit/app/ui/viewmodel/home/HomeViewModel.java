package com.pumpit.app.ui.viewmodel.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pumpit.app.data.local.entity.Authority;
import com.pumpit.app.data.local.entity.Client;
import com.pumpit.app.data.local.entity.Trainer;
import com.pumpit.app.data.local.entity.User;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.listener.registration.HomeListener;

import java.util.Optional;

public class HomeViewModel extends ViewModel {
    private static final String DEFAULT_DESCRIPTION = "Just a good person";
    private static final String TRAINER_PREFIX = "Trainer: ";
    private static final String HEIGHT_AND_WEIGHT_TITLE = "Height ࿕ Weight";
    private static final String TRAINEES_TITLE = "Trainees";

    private String name;
    private String description;
    private String optionalInfo;
    private String sideNumber;
    private String sideTitle;
    private String sideMenuName;
    private String sideMenuStatus;
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
        Optional<Authority> authority = user.getAuthorities()
                .stream()
                .findFirst();

        name = user.getFirstName() + " " + user.getLastName();
        sideMenuStatus = description = authority
                .map(Authority::toString)
                .orElse(DEFAULT_DESCRIPTION);

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
        optionalInfo = TRAINER_PREFIX + client.getTrainerFirstName() + " " + client.getTrainerLastName();
        sideTitle = HEIGHT_AND_WEIGHT_TITLE;
        sideNumber = client.getHeight() + "࿕" + client.getWeight();



        System.out.println(name);
        System.out.println(sideMenuStatus);
        System.out.println(description);
        System.out.println(optionalInfo);
        System.out.println(sideTitle);
        System.out.println(sideNumber);
    }

    private void populateTrainerData(final Trainer trainer) {
        optionalInfo = trainer.getCompany();
        sideTitle = TRAINEES_TITLE;
        sideNumber = String.valueOf(trainer.getClientCount());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptionalInfo() {
        return optionalInfo;
    }

    public void setOptionalInfo(String optionalInfo) {
        this.optionalInfo = optionalInfo;
    }

    public String getSideNumber() {
        return sideNumber;
    }

    public void setSideNumber(String sideNumber) {
        this.sideNumber = sideNumber;
    }

    public String getSideTitle() {
        return sideTitle;
    }

    public void setSideTitle(String sideTitle) {
        this.sideTitle = sideTitle;
    }

    public String getSideMenuName() {
        return sideMenuName;
    }

    public void setSideMenuName(String sideMenuName) {
        this.sideMenuName = sideMenuName;
    }

    public String getSideMenuStatus() {
        return sideMenuStatus;
    }

    public void setSideMenuStatus(String sideMenuStatus) {
        this.sideMenuStatus = sideMenuStatus;
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
}
