package com.example.cartoon;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CartoonDao {
    @Query("SELECT * FROM cartoons")
    LiveData<List<Cartoon>> getAllCartoons();
}

