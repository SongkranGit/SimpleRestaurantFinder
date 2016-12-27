package com.example.simplerestaurantfinder.api.controller;


import com.example.simplerestaurantfinder.api.responses.ApiResponseBean;
import com.example.simplerestaurantfinder.model.OpeningDay;
import com.example.simplerestaurantfinder.model.OpeningHour;
import com.example.simplerestaurantfinder.service.OpeningDayService;
import com.example.simplerestaurantfinder.service.OpeningHourService;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;

/**
 * Created by BERM-PC on 25/12/2559.
 */
@RestController
@RequestMapping("/Api/OpeningHour")
public class OpeningHourController {

    @Autowired
    OpeningHourService openingHourService;

    @Autowired
    OpeningDayService openingDayService;

    @RequestMapping(value = "/createOpeningHour", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new OpeningHour")
    public ApiResponseBean createOpeningHour(
            @RequestParam(value = "openingDayId" , required = true) long openingDayId,
            @RequestParam(value = "startTime" , required = true) Time startTime,
            @RequestParam(value = "endTime" , required = true) Time endTime
    ) {
        ApiResponseBean response = new ApiResponseBean();
        try {
            OpeningDay openingDay = openingDayService.getById(openingDayId);
            OpeningHour openingHour = new OpeningHour();
            openingHour.setStartTime(startTime);
            openingHour.setEndTime(endTime);
            openingHour.setOpeningDay(openingDay);
            openingHour.setCreatedDate(DateTime.now().toDate());
            openingHourService.saveOpeningHour(openingHour);
            response.setStatus("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("failed");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/updateOpeningHour", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update an existing OpeningHour")
    public ApiResponseBean updateOpeningHour(
            @RequestParam(value = "openingHourId" , required = true) long openingHourId,
            @RequestParam(value = "startTime" , required = false) Time startTime,
            @RequestParam(value = "endTime" , required = false) Time endTime
    ) {
        ApiResponseBean response = new ApiResponseBean();
        try {
            OpeningHour openingHour = openingHourService.getById(openingHourId);
            openingHour.setUpdatedDate(DateTime.now().toDate());
            if(startTime != null ){
                openingHour.setStartTime(startTime);
            }

            if(endTime != null){
                openingHour.setEndTime(endTime);
            }

            openingHourService.updateOpeningHour(openingHour);
            response.setStatus("success");
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @RequestMapping(value = "/deleteOpeningHour", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete OpeningHour")
    public ApiResponseBean deleteOpeningHour(
            @RequestParam(value = "openingHourId", required = true) long openingHourId
    ) {
        ApiResponseBean response = new ApiResponseBean();
        try {
            openingHourService.deleteOpeningHour(openingHourId);
            response.setStatus("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("failed");
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
