package com.example.simplerestaurantfinder.service.impl;


import com.example.simplerestaurantfinder.model.Restaurant;
import com.example.simplerestaurantfinder.repository.RestaurantRepository;
import com.example.simplerestaurantfinder.service.RestaurantService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;

/**
 * Created by BERM-PC on 22/12/2559.
 */

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public void saveRestaurant(Restaurant restaurant) throws Exception {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) throws Exception {
        restaurantRepository.update(restaurant);
    }

    @Override
    public void deleteRestaurant(long id) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(id);
        restaurantRepository.delete(restaurant);
    }


    @Override
    public Restaurant getRestaurantById(long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.getRestaurantByName(name);
    }

    @Override
    public Restaurant getRestaurantByLocation(double latitude, double longitude) {
        return restaurantRepository.getRestaurantByLocation(latitude , longitude);
    }


    @Override
    public List<Restaurant> getRestaurantsWithInRadius(double latitude, double longitude, double radius) {
        return null;
       // return restaurantRepository.findByLocationNear(new Point(latitude , longitude), new Distance(radius));
    }

    @Override
    public List<Restaurant> getRestaurantsWithInRadiusAndOpenNow(double latitude, double longitude, double radius, DateTime currentDateTime) {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getRestaurantsOpenNow(Time currentTime) {
        return restaurantRepository.getOpenNowRestaurants(currentTime);
    }


}
