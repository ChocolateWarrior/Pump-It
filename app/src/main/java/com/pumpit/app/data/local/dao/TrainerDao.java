package com.pumpit.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pumpit.app.data.local.DatabaseConstants;
import com.pumpit.app.data.local.entity.Trainer;

@Dao
public interface TrainerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(final Trainer trainer);

    @Query("SELECT * FROM trainers WHERE uid = " + DatabaseConstants.CURRENT_USER_ID)
    LiveData<Trainer> getCurrentUser();
}
