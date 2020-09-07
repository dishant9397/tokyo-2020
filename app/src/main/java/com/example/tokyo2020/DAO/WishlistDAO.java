package com.example.tokyo2020.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tokyo2020.Model.Wishlist;

import java.util.List;

@Dao
public interface WishlistDAO {

    @Insert
    public void addWishlist(Wishlist wishlist);

    @Query("select * from wishlist where userId=:userId and touristAttractionId=:touristAttractionId")
    public List<Wishlist> getWishlist(int userId, int touristAttractionId);

    @Query("select * from wishlist")
    public List<Wishlist> getWishlist();

    @Delete
    public void deleteWishlist(Wishlist wishlist);
}
