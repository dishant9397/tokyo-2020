package com.example.tokyo2020.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tokyo2020.Model.Rating;

import java.util.List;

@Dao
public interface RatingDAO {

    @Insert
    public void addRating(Rating rating);

    @Query("select * from rating where userId=:userId and touristAttractionId=:touristAttractionId")
    public List<Rating> getRating(int userId, int touristAttractionId);

    @Query("update rating set rating=:rating where userId=:userId and touristAttractionId=:touristAttractionId")
    public void editRating(int userId, int touristAttractionId, int rating);
}
