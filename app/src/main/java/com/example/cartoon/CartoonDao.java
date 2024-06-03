package com.example.cartoon;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CartoonDao {
    @Query("SELECT * FROM cartoon")
    LiveData<List<Cartoon>> getAllCartoons();

//    @Query("SELECT * FROM cartoon WHERE id = :id")
//    Cartoon getCartoonById(int id);


}

