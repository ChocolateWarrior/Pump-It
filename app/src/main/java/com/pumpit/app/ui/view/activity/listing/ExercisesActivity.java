package com.pumpit.app.ui.view.activity.listing;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.local.entity.Exercise;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.ExerciseRepository;
import com.pumpit.app.databinding.ActivityExercisesBinding;
import com.pumpit.app.ui.factory.ExercisesViewModelFactory;
import com.pumpit.app.ui.view.adapter.ExerciseDataAdapter;
import com.pumpit.app.ui.viewmodel.listing.ExercisesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {
    private ExercisesViewModel exercisesViewModel;
    private ExerciseDataAdapter exerciseDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final ExerciseRepository exerciseRepository = new ExerciseRepository(api, db);

        ActivityExercisesBinding activityExercisesBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_exercises);

        RecyclerView recyclerView = activityExercisesBinding.viewExercises;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        exercisesViewModel = new ViewModelProvider(this,
                new ExercisesViewModelFactory(this.getApplication(), exerciseRepository))
                .get(ExercisesViewModel.class);

        exerciseDataAdapter = new ExerciseDataAdapter();
        recyclerView.setAdapter(exerciseDataAdapter);
        getAllExercises();
    }

    private void getAllExercises() {
        exercisesViewModel.getAllEmployee().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable List<Exercise> employees) {
                exerciseDataAdapter.setExercises((ArrayList<Exercise>) employees);
            }
        });
    }
}