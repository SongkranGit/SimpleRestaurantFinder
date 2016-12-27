package com.example.restaurantfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by BERM-PC on 23/12/2559.
 */

@Entity
@Table(name = "OpeningHour")
public class OpeningHour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OpeningHourId" )
    private long id;

    @Column(name = "StartTime", columnDefinition="TIME")
    private Time startTime;

    @Column(name = "EndTime", columnDefinition="TIME")
    private Time endTime;


    @Column(name = "CreatedDate", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column( name = "UpdatedDate", columnDefinition="DATETIME" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "OpeningDayId")
    @JsonBackReference
    private OpeningDay openingDay;


    public OpeningHour(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public OpeningDay getOpeningDay() {
        return openingDay;
    }

    public void setOpeningDay(OpeningDay openingDay) {
        this.openingDay = openingDay;
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
}
