package com.example.studia.projekt.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "fieldrecordsField",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
public class FieldRecords {

    @PrimaryKey(autoGenerate = true)
    private int recordFieldsId;   // ten nam zwraca recordFieldsId
    private String number;
    private String area;
    private String name;
    private int userId;

    // 2 konstruktory

    @Ignore
    public FieldRecords(String number, String area, String name, int userId) {
        this.number = number;
        this.area = area;
        this.name = name;
        this.userId = userId;
    }


    public FieldRecords(int recordFieldsId, String number, String area, String name, int userId) {
        this.recordFieldsId = recordFieldsId;
        this.number = number;
        this.area = area;
        this.name = name;
        this.userId = userId;

    }


    //gettery i settery
    public int getRecordFieldsId() {
        return recordFieldsId;
    }

    public void setRecordFieldsId(int recordFieldsId) {
        this.recordFieldsId = recordFieldsId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

   /* public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }*/
}
