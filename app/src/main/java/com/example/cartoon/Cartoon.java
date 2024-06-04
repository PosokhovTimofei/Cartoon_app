package com.example.cartoon;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cartoons")
public class Cartoon implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "image")
    public String image;
    public String description;
    public Integer seasons;
    public String age;
    public String character1;
    public String character2;
    public String character3;
}

