package com.example.studia.projekt.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private String login;
  private String password;


  // do odczytywania
  public User(int id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
  }

  // do zapisywania
  @Ignore
  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
