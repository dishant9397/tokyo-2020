package com.example.tokyo2020;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tokyo2020.DAO.RatingDAO;
import com.example.tokyo2020.DAO.TouristAttractionDAO;
import com.example.tokyo2020.DAO.UserDAO;
import com.example.tokyo2020.DAO.WishlistDAO;
import com.example.tokyo2020.Model.Rating;
import com.example.tokyo2020.Model.TouristAttraction;
import com.example.tokyo2020.Model.User;
import com.example.tokyo2020.Model.Wishlist;

@Database(entities = {User.class, TouristAttraction.class, Rating.class, Wishlist.class}, version = 5)
public abstract class myDatabase extends RoomDatabase {

    public abstract UserDAO userDAO();
    public abstract TouristAttractionDAO touristAttractionDAO();
    public abstract RatingDAO ratingDAO();
    public abstract WishlistDAO wishlistDAO();

}
