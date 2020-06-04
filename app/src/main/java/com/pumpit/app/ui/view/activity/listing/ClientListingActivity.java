package com.pumpit.app.ui.view.activity.listing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.pumpit.app.R;
import com.pumpit.app.ui.view.adapter.MyAdapter;

import java.util.ArrayList;

public class ClientListingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public ClientListingActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_listing);
        recyclerView = findViewById(R.id.recyclerView);
        System.out.println(recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> names = new ArrayList<>();

        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");
        names.add("Baba");
        names.add("Sraka");

        recyclerAdapter = new MyAdapter(names);
        recyclerView.setAdapter(recyclerAdapter);

    }
}