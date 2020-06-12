package com.pumpit.app.ui.viewmodel.listing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pumpit.app.data.local.entity.Client;
import com.pumpit.app.data.remote.dto.ClientResponses;
import com.pumpit.app.data.remote.response.BasicResponse;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.ui.listener.listing.ClientListingListener;

import java.util.List;
import java.util.stream.Collectors;

public class ClientListingViewModel extends ViewModel {

    private UserRepository userRepository;
    private ClientListingListener listener;

    public ClientListingViewModel(final UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public void getAllClientsOfTrainer(long currentTrainerId) {
        MutableLiveData<List<Client>> clientsLiveData = new MutableLiveData<List<Client>>();
        LiveData<BasicResponse<ClientResponses>> clientResponse =
                userRepository.getClientsForTrainer(currentTrainerId);
        clientResponse.observeForever(s -> {
            if (s.isSuccessful()) {
                clientsLiveData.setValue(retrieveClientFromClientResponse(s.getResponse()));
                listener.setAdapter(clientsLiveData, currentTrainerId);
            } else {
                listener.onFailure(s.getMessage());
            }
        });
    }

    private List<Client> retrieveClientFromClientResponse(ClientResponses response) {
        return response.getClientResponses()
                .stream()
                .peek(System.out::println)
                .map(r -> {
                    Client client = new Client();
                    client.setTrainerFirstName(r.getTrainerFirstName());
                    client.setTrainerLastName(r.getTrainerLastName());
                    client.setUsername(r.getUsername());
                    return client;
                })
                .collect(Collectors.toList());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ClientListingListener getListener() {
        return listener;
    }

    public void setListener(ClientListingListener listener) {
        this.listener = listener;
    }
}
