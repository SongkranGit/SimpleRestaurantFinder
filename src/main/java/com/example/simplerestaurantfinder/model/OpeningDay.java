package com.example.simplerestaurantfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by BERM-PC on 23/12/2559.
 */

@Entity
@Table(name = "OpeningDay")
public class OpeningDay implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OpeningDayId" )
    private long id;

    @Column(name = "DayOfWeek" , nullable = false)
    private String dayOfWeek;

    @Column(name = "IsOpen" , nullable = false)
    private Boolean  isOpen;

    @Column(name = "CreatedDate", columnDefinition="DATETIME" , nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column( name = "UpdatedDate", columnDefinition="DATETIME" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "RestaurantId" ,  nullable = false)
    @JsonBackReference
    private Restaurant restaurant;


    @OneToMany(mappedBy = "openingDay", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private Set<OpeningHour> openingHours;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<OpeningHour> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Set<OpeningHour> openingHours) {
        this.openingHours = openingHours;
    }
}
