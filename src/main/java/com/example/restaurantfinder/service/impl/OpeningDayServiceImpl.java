package com.example.restaurantfinder.service.impl;

import com.example.restaurantfinder.repository.OpeningDayRepository;
import com.example.restaurantfinder.model.OpeningDay;
import com.example.restaurantfinder.service.OpeningDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by BERM-PC on 24/12/2559.
 */

@Service
@Transactional
public class OpeningDayServiceImpl implements OpeningDayService {

    @Autowired
    OpeningDayRepository openingDayRepository;

    @Override
    public void saveOpeningDay(OpeningDay openingDay) throws Exception {
        openingDayRepository.save(openingDay);
    }

    @Override
    public void updateOpeningDay(OpeningDay openingDay) throws Exception {
        openingDayRepository.save(openingDay);
    }

    @Override
    public void deleteOpeningDay(long OpeningDayId) throws Exception {
        openingDayRepository.delete(OpeningDayId);
    }

    @Override
    public OpeningDay getById(long OpeningDayId) {
        return openingDayRepository.findOne(OpeningDayId);
    }

    @Override
    public List<OpeningDay> getAllOpeningDay() {
        return (List<OpeningDay>) openingDayRepository.findAll();
    }

    @Override
    public List<OpeningDay> getListOfOpeningDayByRestaurantId(long restaurantId) {
        return null;
    }
}
