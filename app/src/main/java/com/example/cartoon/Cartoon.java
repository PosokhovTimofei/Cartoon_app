package com.example.cartoon;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cartoon")
public class Cartoon implements Serializable {

    @PrimaryKey
    public Integer id;
    public String name;
    public String image;
    public String description;
    public Integer seasons;
    public String age;
    public String character1;
    public String character2;
    public String character3;

    // Геттеры и сеттеры для поля id
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    // Геттеры и сеттеры для поля name
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    // Геттеры и сеттеры для поля image
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    // Геттеры и сеттеры для поля description
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    // Геттеры и сеттеры для поля seasons
//    public int getSeasons() {
//        return seasons;
//    }
//
//    public void setSeasons(int seasons) {
//        this.seasons = seasons;
//    }
//
//    // Геттеры и сеттеры для поля age
//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }
//
//    // Геттеры и сеттеры для поля character1
//    public String getCharacter1() {
//        return character1;
//    }
//
//    public void setCharacter1(String character1) {
//        this.character1 = character1;
//    }
//
//    // Геттеры и сеттеры для поля character2
//    public String getCharacter2() {
//        return character2;
//    }
//
//    public void setCharacter2(String character2) {
//        this.character2 = character2;
//    }
//
//    // Геттеры и сеттеры для поля character3
//    public String getCharacter3() {
//        return character3;
//    }
//
//    public void setCharacter3(String character3) {
//        this.character3 = character3;
//    }
}

