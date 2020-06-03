package com.pumpit.app.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pumpit.app.data.local.dao.UserDao;
import com.pumpit.app.data.local.entity.User;

import java.util.Objects;

@Database(entities = {User.class}, version = 1)
public abstract class PumpItDatabase extends RoomDatabase {

    private static PumpItDatabase instance;

    public abstract UserDao getUserDao();

    public static synchronized PumpItDatabase getInstance(final Context context) {
        PumpItDatabase localInstance = instance;
        if (Objects.isNull(localInstance)) {
            synchronized (PumpItDatabase.class) {
                localInstance = instance;
                if (Objects.isNull(localInstance)) {
                    instance = localInstance = createInstance(context);
                }
            }
        }
        return localInstance;
    }

    private static PumpItDatabase createInstance(final Context context) {
        return Room.databaseBuilder(context,
                PumpItDatabase.class, "pump_it_db")
                .allowMainThreadQueries()
                .build();
    }
}
