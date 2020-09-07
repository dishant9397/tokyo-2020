package com.example.tokyo2020.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "touristAttraction")
public class TouristAttraction implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String touristAttractionName;
    String touristAttractionAddress;
    String touristAttractionDescription;

    public TouristAttraction(String touristAttractionName, String touristAttractionAddress, String touristAttractionDescription) {
        this.touristAttractionName = touristAttractionName;
        this.touristAttractionAddress = touristAttractionAddress;
        this.touristAttractionDescription = touristAttractionDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTouristAttractionName() {
        return touristAttractionName;
    }

    public void setTouristAttractionName(String touristAttractionName) {
        this.touristAttractionName = touristAttractionName;
    }

    public String getTouristAttractionAddress() {
        return touristAttractionAddress;
    }

    public void setTouristAttractionAddress(String touristAttractionAddress) {
        this.touristAttractionAddress = touristAttractionAddress;
    }

    public String getTouristAttractionDescription() {
        return touristAttractionDescription;
    }

    public void setTouristAttractionDescription(String touristAttractionDescription) {
        this.touristAttractionDescription = touristAttractionDescription;
    }
}
