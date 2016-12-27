package com.example.simplerestaurantfinder.service;



import com.example.simplerestaurantfinder.model.OpeningDay;

import java.util.List;

/**
 * Created by BERM-PC on 24/12/2559.
 */
public interface OpeningDayService {

    void saveOpeningDay(OpeningDay openingDay) throws Exception;

    void updateOpeningDay(OpeningDay openingDay) throws Exception;

    void deleteOpeningDay(long OpeningDayId) throws Exception;

    OpeningDay getById(long OpeningDayId);

    List<OpeningDay> getAllOpeningDay();

    List<OpeningDay> getListOfOpeningDayByRestaurantId(long restaurantId);


}
