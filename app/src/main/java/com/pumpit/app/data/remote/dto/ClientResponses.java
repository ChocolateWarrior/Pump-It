package com.pumpit.app.data.remote.dto;

import com.pumpit.app.data.remote.response.ClientResponse;

import java.util.List;

public class ClientResponses {

    private List<ClientResponse> clientResponses;

    public ClientResponses(List<ClientResponse> clientResponses) {
        this.clientResponses = clientResponses;
    }

    public List<ClientResponse> getClientResponses() {
        return clientResponses;
    }

    public void setClientResponses(List<ClientResponse> clientResponses) {
        this.clientResponses = clientResponses;
    }
}
