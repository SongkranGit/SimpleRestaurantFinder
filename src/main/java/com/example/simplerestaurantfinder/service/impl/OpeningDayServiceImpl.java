package com.example.simplerestaurantfinder.service.impl;


import com.example.simplerestaurantfinder.model.OpeningDay;
import com.example.simplerestaurantfinder.repository.OpeningDayRepository;
import com.example.simplerestaurantfinder.service.OpeningDayService;
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
        openingDayRepository.update(openingDay);
    }

    @Override
    public void deleteOpeningDay(long OpeningDayId) throws Exception {
        OpeningDay openingDay = openingDayRepository.findById(OpeningDayId);
        openingDayRepository.delete(openingDay);
    }

    @Override
    public OpeningDay getById(long OpeningDayId) {
        return openingDayRepository.findById(OpeningDayId);
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
