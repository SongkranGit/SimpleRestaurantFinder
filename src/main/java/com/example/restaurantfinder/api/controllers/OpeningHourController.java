package com.example.restaurantfinder.api.controllers;

import com.example.restaurantfinder.api.responses.ApiResponse;
import com.example.restaurantfinder.model.OpeningDay;
import com.example.restaurantfinder.model.OpeningHour;
import com.example.restaurantfinder.service.OpeningDayService;
import com.example.restaurantfinder.service.OpeningHourService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse createOpeningHour(
            @RequestParam(value = "openingDayId" , required = true) long openingDayId,
            @RequestParam(value = "startTime" , required = true) Time startTime,
            @RequestParam(value = "endTime" , required = true) Time endTime
    ) {
        ApiResponse response = new ApiResponse();
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
    public ApiResponse updateOpeningHour(
            @RequestParam(value = "openingHourId" , required = true) long openingHourId,
            @RequestParam(value = "startTime" , required = false) Time startTime,
            @RequestParam(value = "endTime" , required = false) Time endTime
    ) {
        ApiResponse response = new ApiResponse();
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
    public ApiResponse deleteOpeningHour(
            @RequestParam(value = "openingHourId", required = true) long openingHourId
    ) {
        ApiResponse response = new ApiResponse();
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
