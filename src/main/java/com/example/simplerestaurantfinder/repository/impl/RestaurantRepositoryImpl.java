package com.example.simplerestaurantfinder.repository.impl;

import com.example.simplerestaurantfinder.model.Restaurant;
import com.example.simplerestaurantfinder.repository.BaseGenericRepository;
import com.example.simplerestaurantfinder.repository.RestaurantRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by BERM-PC on 1/1/2560.
 */


@Repository
public class RestaurantRepositoryImpl extends BaseGenericRepository<Restaurant, Long> implements RestaurantRepository {
    @Override
    public Restaurant getRestaurantByName(String name) {
        Query query = entityManager.createQuery("SELECT c FROM Restaurant c WHERE c.name = ?1");
        query.setParameter(1, name);
        Restaurant restaurant = (Restaurant)query.getSingleResult();
        return restaurant;
    }




}
