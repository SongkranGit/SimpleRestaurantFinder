package com.example.simplerestaurantfinder.service;


import com.example.simplerestaurantfinder.model.Restaurant;
import org.joda.time.DateTime;

import java.sql.Time;
import java.util.List;

/**
 * Created by BERM-PC on 22/12/2559.
 */
public interface RestaurantService {

    void saveRestaurant(Restaurant restaurant) throws Exception;

    void updateRestaurant(Restaurant restaurant) throws Exception;

    void deleteRestaurant(long id) throws Exception;

    Restaurant getRestaurantById(long id);

    Restaurant getRestaurantByName(String name);

    Restaurant getRestaurantByLocation(double latitude, double longitude);

    List<Restaurant> getRestaurantsWithInRadius(double latitude, double longitude, double radius);

    List<Restaurant> getRestaurantsWithInRadiusAndOpenNow(double latitude, double longitude, double radius, DateTime currentDateTime);

    List<Restaurant> getAllRestaurant();

    List<Restaurant> getRestaurantsOpenNow(Time currentTime);


}
