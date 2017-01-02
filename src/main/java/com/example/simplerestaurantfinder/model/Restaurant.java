package com.example.simplerestaurantfinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by BERM-PC on 22/12/2559.
 */

@Entity
@Table(name = "Restaurant")
public class Restaurant  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RestaurantId" )
    private long id;

    @Column(name = "Name" , nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Latitude" , nullable = false)
    private Double latitude;

    @Column(name = "Longitude" , nullable = false)
    private Double longitude;

    @Column(name = "Location" , columnDefinition = "geometry")
    @JsonIgnore
    private Point location;

    @Column(name = "CreatedDate", columnDefinition="DATETIME" , nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column( name = "UpdatedDate", columnDefinition="DATETIME" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;


    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private Set<OpeningDay> openingDays;


    public Long getId() {
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

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<OpeningDay> getOpeningDays() {
        return openingDays;
    }

    public void setOpeningDays(Set<OpeningDay> openingDays) {
        this.openingDays = openingDays;
    }

}
