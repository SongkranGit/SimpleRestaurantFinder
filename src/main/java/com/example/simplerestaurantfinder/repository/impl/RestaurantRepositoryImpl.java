package com.example.simplerestaurantfinder.repository.impl;

import com.example.simplerestaurantfinder.model.Restaurant;
import com.example.simplerestaurantfinder.repository.BaseGenericRepository;
import com.example.simplerestaurantfinder.repository.RestaurantRepository;
import com.example.simplerestaurantfinder.utils.DateTimeUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.Time;
import java.util.List;

/**
 * Created by BERM-PC on 1/1/2560.
 */


@Repository
public class RestaurantRepositoryImpl extends BaseGenericRepository<Restaurant, Long> implements RestaurantRepository {
    @Override
    public Restaurant getRestaurantByName(String name) {
        Query query = entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.name = ?1");
        query.setParameter(1, name);
        Restaurant restaurant = (Restaurant)query.getSingleResult();
        return restaurant;
    }

    @Override
    public Restaurant getRestaurantByLocation(double latitude, double longitude) {
        Query query = entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.latitude = ?1 AND r.longitude = ?2");
        query.setParameter(1, latitude);
        query.setParameter(2, longitude);
        Restaurant restaurant = (Restaurant)query.getSingleResult();
        return restaurant;
    }

    @Override
    public List<Restaurant> getOpenNowRestaurants(Time currentTime) {
        String strQuery = "SELECT r , od.dayOfWeek , oh.startTime , oh.endTime FROM Restaurant  r INNER JOIN r.openingDays od INNER JOIN od.openingHours oh WHERE oh.startTime <= :st AND oh.endTime >= :et ";
        Query query = entityManager.createQuery(strQuery);
        query.setParameter("st", currentTime);
        query.setParameter("et", currentTime);
        return query.getResultList();
    }



}
