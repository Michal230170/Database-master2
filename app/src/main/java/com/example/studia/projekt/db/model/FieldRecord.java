package com.example.studia.projekt.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "fieldrecords",
    foreignKeys = @ForeignKey(entity = FieldRecords.class, parentColumns = "recordFieldsId", childColumns = "fieldId"))
//FieldRecords.class w entity parentColumns to id z usera chilf id powiazuje nam z ta tabela
public class FieldRecord {

  //foreign key muszą być intami
  @PrimaryKey(autoGenerate = true)
  private int singleFieldId; // ten nam zwraca singleFieldId;
  private String date;
  private String area;
  private String plant;
  private String substance;
  private String usageReason;
  private String dose;
  private int fieldId;

  @Ignore
  public FieldRecord(String date, String area, String plant, String substance,
      String usageReason, String dose, int fieldId) {
    this.date = date;
    this.area = area;
    this.plant = plant;
    this.substance = substance;
    this.usageReason = usageReason;
    this.dose = dose;
    this.fieldId = fieldId;
  }

  public FieldRecord(int singleFieldId, String date, String area, String plant, String substance,
      String usageReason, String dose, int fieldId) {
    this.singleFieldId = singleFieldId;
    this.date = date;
    this.area = area;
    this.plant = plant;
    this.substance = substance;
    this.usageReason = usageReason;
    this.dose = dose;
    this.fieldId = fieldId;
  }

  public int getSingleFieldId() {
    return singleFieldId;
  }

  public void setSingleFieldId(int singleFieldId) {
    this.singleFieldId = singleFieldId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getPlant() {
    return plant;
  }

  public void setPlant(String plant) {
    this.plant = plant;
  }

  public String getSubstance() {
    return substance;
  }

  public void setSubstance(String substance) {
    this.substance = substance;
  }

  public String getUsageReason() {
    return usageReason;
  }

  public void setUsageReason(String usageReason) {
    this.usageReason = usageReason;
  }

  public String getDose() {
    return dose;
  }

  public void setDose(String dose) {
    this.dose = dose;
  }

  public int getFieldId() {
    return fieldId;
  }

  public void setFieldId(int fieldId) {
    this.fieldId = fieldId;
  }
}
