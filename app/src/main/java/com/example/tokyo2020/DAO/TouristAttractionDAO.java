package com.example.tokyo2020.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tokyo2020.Model.TouristAttraction;

import java.util.List;

@Dao
public interface TouristAttractionDAO {
    @Insert
    public void addTouristAttraction(TouristAttraction touristAttraction);

    @Query("select * from touristAttraction")
    public List<TouristAttraction> getTouristAttractions();

    @Query("select * from touristAttraction where id=:id")
    public List<TouristAttraction> getTouristAttractionsById(int id);

    @Query("select * from touristAttraction Inner Join wishlist ON wishlist.touristAttractionId=touristAttraction.id where wishlist.userId=:userId")
    public List<TouristAttraction> getTouristAttractionByWishlist(int userId);
}
