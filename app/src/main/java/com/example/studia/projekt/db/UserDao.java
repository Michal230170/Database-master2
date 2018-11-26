package com.example.studia.projekt.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.studia.projekt.db.model.User;

@Dao
public interface UserDao {

  @Insert
  void insert(User user);

  @Query("SELECT * FROM users WHERE login=:login")
  User findUserByLogin(String login);
}
