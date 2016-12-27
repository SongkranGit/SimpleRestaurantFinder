package com.example.simplerestaurantfinder.repository;


import com.example.simplerestaurantfinder.model.OpeningDay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BERM-PC on 24/12/2559.
 */
public interface OpeningDayRepository extends JpaRepository<OpeningDay, Long> {
}
