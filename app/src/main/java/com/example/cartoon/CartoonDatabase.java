package com.example.cartoon;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cartoon.class}, version = 1, exportSchema = true)
public abstract class CartoonDatabase extends RoomDatabase {
    private static CartoonDatabase instance;

    public abstract CartoonDao cartoonDao();

    public static synchronized CartoonDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CartoonDatabase.class, "cartoon.db")
                    .createFromAsset("databases/cartoon.db")
                    .build();
        }
        return instance;
    }
}
