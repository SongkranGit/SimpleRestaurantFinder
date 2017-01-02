package com.example.simplerestaurantfinder.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BERM-PC on 1/1/2560.
 */
public interface GenericCRUDRepository<T, PK extends Serializable> {
    void save(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(T t) throws Exception;
    T findById(PK id);
    List<T> findAll();
}