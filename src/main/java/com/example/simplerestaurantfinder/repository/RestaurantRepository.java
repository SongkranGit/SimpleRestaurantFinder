package com.example.simplerestaurantfinder.repository;

import com.example.simplerestaurantfinder.model.Restaurant;

/**
 * Created by BERM-PC on 1/1/2560.
 */


public interface RestaurantRepository extends GenericCRUDRepository<Restaurant , Long> {

    Restaurant getRestaurantByName(String name);


}
