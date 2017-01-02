package com.example.simplerestaurantfinder.repository.impl;

import com.example.simplerestaurantfinder.model.OpeningDay;
import com.example.simplerestaurantfinder.repository.BaseGenericRepository;
import com.example.simplerestaurantfinder.repository.OpeningDayRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BERM-PC on 1/1/2560.
 */

@Repository
public class OpeningDayRepositoryImpl extends BaseGenericRepository<OpeningDay, Long> implements OpeningDayRepository {

}
