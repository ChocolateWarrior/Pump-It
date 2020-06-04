package com.pumpit.app.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pumpit.app.R;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NamesViewHolder> {
    private ArrayList<String> names;

    public static class NamesViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public NamesViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.view_username);
        }
    }

    public MyAdapter(ArrayList<String> myDataset) {
        names = myDataset;
    }

    @Override
    public NamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        return new NamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NamesViewHolder holder, int position) {
        String currentItem = names.get(position);
        holder.textView.setText(currentItem);

    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
