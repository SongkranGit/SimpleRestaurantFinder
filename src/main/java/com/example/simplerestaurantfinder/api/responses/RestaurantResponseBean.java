package com.example.simplerestaurantfinder.api.responses;

import com.example.simplerestaurantfinder.model.OpeningDay;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by BERM-PC on 26/12/2559.
 */
public class RestaurantResponseBean {

    private long id;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private DateTime createdDate;
    private DateTime updatedDate;
    private List<OpeningDay> openingDays;

    public RestaurantResponseBean(long id, String name, String description, Double latitude, Double longitude, DateTime createdDate, DateTime updatedDate, List<OpeningDay> openingDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.openingDays = openingDays;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(DateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<OpeningDay> getOpeningDays() {
        return openingDays;
    }

    public void setOpeningDays(List<OpeningDay> openingDays) {
        this.openingDays = openingDays;
    }
}
