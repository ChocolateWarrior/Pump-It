package com.pumpit.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pumpit.app.data.local.DatabaseConstants;
import com.pumpit.app.data.local.entity.Client;

@Dao
public interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(final Client client);

    @Query("SELECT * FROM clients WHERE uid = " + DatabaseConstants.CURRENT_USER_ID)
    LiveData<Client> getCurrentUser();
}
