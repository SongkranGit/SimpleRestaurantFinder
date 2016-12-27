package com.example.simplerestaurantfinder.repository.custom;


import com.example.simplerestaurantfinder.model.Restaurant;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.List;

/**
 * Created by BERM-PC on 27/12/2559.
 */
public class CutomRestaurantRepositoryImpl implements CustomRestaurantRepository {


    @Override
    public List<Restaurant> findRestaurantNearby(Point location, Distance distance) {
        return null;
    }
}
