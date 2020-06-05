package com.pumpit.app.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pumpit.app.R;
import com.pumpit.app.data.local.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.NamesViewHolder> {

    private List<Client> clients;

    @Override
    public NamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        return new NamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NamesViewHolder holder, int position) {
        Client currentClient = clients.get(position);
        String currentName = currentClient.getUsername();
        String currentDesc = currentClient.getTrainerFirstName() + " " + currentClient.getTrainerLastName();
        holder.nameView.setText(currentName);
        holder.descView.setText(currentDesc);
//        holder.avatarView.setImageDrawable();
    }

    @Override
    public int getItemCount() {
        if (Objects.nonNull(clients)) {
            return clients.size();
        } else {
            return 0;
        }
    }

    public static class NamesViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView descView;
        public ImageView avatarView;
        public Button removeButton;

        public NamesViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.view_username);
            descView = view.findViewById(R.id.view_desc);
            avatarView = view.findViewById(R.id.user_avatar);
            removeButton = view.findViewById(R.id.remove_button);
        }

    }

    public ClientAdapter(List<Client> clients) {
        this.clients = clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
        notifyDataSetChanged();
    }

}
