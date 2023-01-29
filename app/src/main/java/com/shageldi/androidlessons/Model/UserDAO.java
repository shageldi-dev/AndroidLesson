package com.shageldi.androidlessons.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users")
    List<Users> getAll();

    @Insert
    void insertAll(Users users);

    @Delete
    void delete(Users users);

    @Query("DELETE FROM users WHERE username=:username AND phone_number=:phone")
    void deleteByUsernamePhone(String username,String phone);

    @Query("UPDATE users SET username=:username,phone_number=:phone WHERE username=:oldUsername")
    void update(String username,String phone,String oldUsername);
}
