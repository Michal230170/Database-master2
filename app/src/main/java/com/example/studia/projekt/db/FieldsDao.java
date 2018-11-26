package com.example.studia.projekt.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.studia.projekt.db.model.FieldRecord;
import java.util.List;

@Dao
public interface FieldsDao {

  @Insert
  void insert(FieldRecord record);

  @Query("SELECT * FROM fieldrecords WHERE fieldId=:id")
  LiveData<List<FieldRecord>> findFieldRecordsByFieldId(int id);

  @Query("SELECT * FROM fieldrecords WHERE singleFieldId=:id")
  LiveData<FieldRecord> getFieldRecordById(int id);

}
