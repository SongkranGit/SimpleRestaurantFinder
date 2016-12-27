package com.example.restaurantfinder.service.impl;

import com.example.restaurantfinder.repository.RestaurantRepository;
import com.example.restaurantfinder.model.Restaurant;
import com.example.restaurantfinder.service.RestaurantService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void updateRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(long id) {
        restaurantRepository.delete(id);
    }


    @Override
    public Restaurant getRestaurantById(long id) {
        return restaurantRepository.findOne(id);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    public Restaurant getRestaurantByLocation(double latitude, double longitude) {
        return restaurantRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    @Override
    public List<Restaurant> getRestaurantByNameOrDescription(String name, String description) {
        return restaurantRepository.findByNameOrDescription(name, description);
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



}
