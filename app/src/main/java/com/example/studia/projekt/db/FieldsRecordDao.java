package com.example.studia.projekt.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.studia.projekt.db.model.FieldRecords;

import java.util.List;

@Dao
public interface FieldsRecordDao {

    @Insert
    void insert(FieldRecords record);

    @Query("SELECT * FROM fieldrecordsField WHERE userId=:id")
    LiveData<List<FieldRecords>> findFieldRecordsByUserId(int id);

    @Query("SELECT * FROM fieldrecordsField WHERE recordFieldsId=:id")
    LiveData<FieldRecords> getFieldRecordsById(int id);

}