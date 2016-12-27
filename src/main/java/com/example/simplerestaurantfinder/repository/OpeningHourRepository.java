package com.example.simplerestaurantfinder.repository;


import com.example.simplerestaurantfinder.model.OpeningHour;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BERM-PC on 25/12/2559.
 */
public interface OpeningHourRepository extends JpaRepository<OpeningHour, Long> {

}
