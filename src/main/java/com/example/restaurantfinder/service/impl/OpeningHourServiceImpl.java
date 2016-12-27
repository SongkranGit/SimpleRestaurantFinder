package com.example.restaurantfinder.service.impl;

import com.example.restaurantfinder.repository.OpeningHourRepository;
import com.example.restaurantfinder.model.OpeningHour;
import com.example.restaurantfinder.service.OpeningHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BERM-PC on 25/12/2559.
 */
@Service
public class OpeningHourServiceImpl implements OpeningHourService {


    @Autowired
    OpeningHourRepository openingHourRepository;

    @Override
    public void saveOpeningHour(OpeningHour openingHour) throws Exception {
        openingHourRepository.save(openingHour);
    }

    @Override
    public void updateOpeningHour(OpeningHour openingHour) throws Exception {
        openingHourRepository.save(openingHour);
    }

    @Override
    public void deleteOpeningHour(long openingHourId) throws Exception {
        openingHourRepository.delete(openingHourId);
    }

    @Override
    public OpeningHour getById(long openingHourId) {
        return openingHourRepository.findOne(openingHourId);
    }

    @Override
    public List<OpeningHour> getAllOpeningHour() {
        return openingHourRepository.findAll();
    }
}
