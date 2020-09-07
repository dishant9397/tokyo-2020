package com.example.tokyo2020.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tokyo2020.Model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    public void addUser(User user);

    @Query("select * from users where username=:username")
    public List<User> getUserByUsername(String username);
}

