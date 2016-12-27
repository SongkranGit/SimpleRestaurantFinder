package com.example.restaurantfinder.repository;

import com.example.restaurantfinder.model.Restaurant;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BERM-PC on 22/12/2559.
 */

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant , Long> {

    Restaurant findByName(String name);

    Restaurant findByLatitudeAndLongitude(double latitude, double longitude);

    List<Restaurant> findByNameOrDescription(String name, String description);

   // @Query("select l from Restaurant l where within(l.shape, ?) = true")
   // List<Restaurant> findByLocationNear(Point location, Distance distance);

//    @Query("select c from Car c where dwithin(c.location, :geom, :dist) = true")
//    List<Restaurant> findByLocationWithin(Point location, Distance distance);

    public List<Restaurant> findByLocationNear(Point location, Distance distance);

//
//    @Query("select * from restaurant where st_contains(geom, point(-122.409153, 37.77765));")
//     List<Restaurant> find(@Param("lastName") String lastName);
}
