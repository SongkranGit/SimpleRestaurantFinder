package com.example.restaurantfinder.service;

import com.example.restaurantfinder.model.OpeningHour;

import java.util.List;

/**
 * Created by BERM-PC on 25/12/2559.
 */
public interface OpeningHourService {

    void saveOpeningHour(OpeningHour openingHour) throws Exception;

    void updateOpeningHour(OpeningHour openingHour) throws Exception;

    void deleteOpeningHour(long openingHourId) throws Exception;

    OpeningHour getById(long openingHourId);

    List<OpeningHour> getAllOpeningHour();


}
