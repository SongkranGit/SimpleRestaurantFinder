package com.example.restaurantfinder.repository.custom;

import com.example.restaurantfinder.model.Restaurant;
import com.example.restaurantfinder.repository.RestaurantRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * Created by BERM-PC on 27/12/2559.
 */
public interface CustomRestaurantRepository extends RestaurantRepository , SolrCrudRepository<Restaurant, Long> {

     List<Restaurant> findByLocationNear(Point location, Distance distance);

}
