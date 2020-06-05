package com.pumpit.app.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pumpit.app.R;
import com.pumpit.app.data.local.entity.Exercise;
import com.pumpit.app.databinding.ExerciseListItemBinding;

import java.util.ArrayList;

public class ExerciseDataAdapter extends RecyclerView.Adapter<ExerciseDataAdapter.ExerciseViewHolder> {
    private ArrayList<Exercise> exercises;

    @NonNull
    @Override
    public ExerciseDataAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExerciseListItemBinding exerciseListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.exercise_list_item, parent, false);

        return new ExerciseViewHolder(exerciseListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.exerciseListItemBinding.setExercise(exercise);
    }

    @Override
    public int getItemCount() {
        if (exercises != null) {
            return exercises.size();
        } else {
            return 0;
        }
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private ExerciseListItemBinding exerciseListItemBinding;

        public ExerciseViewHolder(ExerciseListItemBinding exerciseListItemBinding) {
            super(exerciseListItemBinding.getRoot());
            this.exerciseListItemBinding = exerciseListItemBinding;
        }
    }
}
