package com.example.studia.projekt.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.studia.projekt.db.model.FieldRecord;
import com.example.studia.projekt.db.model.FieldRecords;
import com.example.studia.projekt.db.model.User;

@Database(entities = {User.class, FieldRecord.class, FieldRecords.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

  private static final Object LOCK = new Object();
  private static AppDatabase sInstance;

  public static AppDatabase getInstance(Context context) {
    if (sInstance == null) {
      synchronized (LOCK) {
        sInstance = Room.databaseBuilder(context.getApplicationContext(),
            AppDatabase.class, "FieldsRecordDatabase")
            .build();
      }
    }
    return sInstance;
  }

  public abstract UserDao userDao();

  public abstract FieldsDao fieldDao();

  public abstract FieldsRecordDao fieldRecordDao();
}
