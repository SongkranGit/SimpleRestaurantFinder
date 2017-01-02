package com.example.simplerestaurantfinder.repository;

import com.example.simplerestaurantfinder.model.Restaurant;
import org.joda.time.DateTime;

import java.sql.Time;
import java.util.List;

/**
 * Created by BERM-PC on 1/1/2560.
 */


public interface RestaurantRepository extends GenericCRUDRepository<Restaurant , Long> {

    Restaurant getRestaurantByName(String name);

    Restaurant getRestaurantByLocation(double latitude, double longitude );

    List<Restaurant> getOpenNowRestaurants(Time currentTime);

}
