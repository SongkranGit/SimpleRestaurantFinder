package com.example.simplerestaurantfinder.repository;

import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by BERM-PC on 1/1/2560.
 */

public class BaseGenericRepository<T, PK extends Serializable> implements GenericCRUDRepository<T, PK> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseGenericRepository() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public void save(T t) throws Exception {
        this.entityManager.persist(t);
    }

    @Override
    public void update(T t) throws Exception {
        this.entityManager.merge(t);
    }

    @Override
    public void delete(T t) throws Exception {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }

    @Override
    public T findById(PK id) {
       return  this.entityManager.find(entityClass , id);
    }

    @Override
    public List<T> findAll() {
        Query query = this.entityManager.createQuery("from " + entityClass.getName());
        return query.getResultList();
    }




}