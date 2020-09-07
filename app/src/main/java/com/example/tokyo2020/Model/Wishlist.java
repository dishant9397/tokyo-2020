package com.example.tokyo2020.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "wishlist")
public class Wishlist implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;
    int userId;
    int touristAttractionId;

    public Wishlist(int userId, int touristAttractionId) {
        this.userId = userId;
        this.touristAttractionId = touristAttractionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTouristAttractionId() {
        return touristAttractionId;
    }

    public void setTouristAttractionId(int touristAttractionId) {
        this.touristAttractionId = touristAttractionId;
    }
}
