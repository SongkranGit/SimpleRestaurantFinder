package com.example.simplerestaurantfinder.repository.impl;

import com.example.simplerestaurantfinder.model.Restaurant;
import com.example.simplerestaurantfinder.repository.BaseGenericRepository;
import com.example.simplerestaurantfinder.repository.RestaurantRepository;
import com.example.simplerestaurantfinder.utils.GeometryUtil;
import com.vividsolutions.jts.geom.Geometry;
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

    @Override
    public List<Restaurant> getNearbyRestaurantWithinRadius(double latitude, double longitude, double radius) {
        Geometry filter = GeometryUtil.createCircle(latitude , longitude , radius);
        String strQuery = "SELECT r FROM Restaurant r WHERE within(r.location , ? )= true";
        Query query = entityManager.createQuery(strQuery);
        query.setParameter(0, filter);
        return query.getResultList();
    }

    @Override
    public List<Restaurant> getNearbyRestaurantWithinRadiusAndOpenNow(double latitude, double longitude, double radius, Time currentTime) {
        StringBuffer sbQuery = new StringBuffer();
        sbQuery.append(" SELECT r , od.dayOfWeek , oh.startTime , oh.endTime FROM Restaurant  r ");
        sbQuery.append(" INNER JOIN r.openingDays od");
        sbQuery.append(" INNER JOIN od.openingHours oh");
        sbQuery.append(" WHERE oh.startTime <= :st AND oh.endTime >= :et ");
        sbQuery.append(" AND within(r.location , :filter )= true");

        Geometry filter = GeometryUtil.createCircle(latitude , longitude , radius);

        Query query = entityManager.createQuery(sbQuery.toString());
        query.setParameter("st", currentTime);
        query.setParameter("et", currentTime);
        query.setParameter("filter", filter);
        return query.getResultList();
    }


}
