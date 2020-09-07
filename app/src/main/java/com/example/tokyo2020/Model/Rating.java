package com.example.tokyo2020.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "rating")
public class Rating implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;
    int userId;
    int touristAttractionId;
    int rating;

    public Rating(int userId, int touristAttractionId, int rating) {
        this.userId = userId;
        this.touristAttractionId = touristAttractionId;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
