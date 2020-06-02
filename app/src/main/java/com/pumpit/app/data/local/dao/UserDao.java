package com.pumpit.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pumpit.app.data.local.DatabaseConstants;
import com.pumpit.app.data.local.entity.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(final User user);

    @Query("SELECT * FROM user WHERE uid = " + DatabaseConstants.CURRENT_USER_ID)
    LiveData<User> getCurrentUser();
}
