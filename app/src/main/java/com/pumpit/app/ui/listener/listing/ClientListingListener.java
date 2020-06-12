package com.pumpit.app.ui.listener.listing;

import androidx.lifecycle.LiveData;

import com.pumpit.app.data.local.entity.Client;

import java.util.List;

public interface ClientListingListener {
    void onFailure(final String message);

    void setAdapter(LiveData<List<Client>> clients, long id);
}
