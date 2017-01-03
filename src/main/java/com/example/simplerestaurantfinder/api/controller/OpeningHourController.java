package com.example.simplerestaurantfinder.api.controller;


import com.example.simplerestaurantfinder.api.responses.ApiResponseBean;
import com.example.simplerestaurantfinder.model.OpeningDay;
import com.example.simplerestaurantfinder.model.OpeningHour;
import com.example.simplerestaurantfinder.service.OpeningDayService;
import com.example.simplerestaurantfinder.service.OpeningHourService;
import com.example.simplerestaurantfinder.utils.DateTimeUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.LocalTime;

/**
 * Created by BERM-PC on 25/12/2559.
 */
@RestController
@RequestMapping("/Api/OpeningHour")
public class OpeningHourController {

    private static final String TAG = "OpeningHourController";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OpeningHourService openingHourService;

    @Autowired
    OpeningDayService openingDayService;

    /**
     * @param openingDayId
     * @param startTime
     * @param endTime
     * @return ApiResponseBean
     */
    @RequestMapping(value = "/createOpeningHour", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new OpeningHour")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openingDayId", value = "identify of OpeningDay", required = true, dataType = "long", paramType = "query" ),
            @ApiImplicitParam(name = "startTime", value = "Time(HH:mm)", required = true, dataType = "date-time", paramType = "query", defaultValue="09:30"),
            @ApiImplicitParam(name = "endTime", value = "Time(HH:mm)", required = true, dataType = "date-time", paramType = "query", defaultValue="10:30")
    })
    public ApiResponseBean createOpeningHour(
            @RequestParam(name = "openingDayId", required = true) long openingDayId,
            @RequestParam(name = "startTime", required = true ) @DateTimeFormat(pattern="HH:mm") DateTime startTime,
            @RequestParam(name = "endTime", required = true ) @DateTimeFormat(pattern="HH:mm") DateTime endTime
    ) {
        ApiResponseBean response = new ApiResponseBean();
        try {
            OpeningDay openingDay = openingDayService.getById(openingDayId);
            if(openingDay != null){
                OpeningHour openingHour = new OpeningHour();
                openingHour.setStartTime(DateTimeUtil.convertDateTimeToSqlTime(startTime));
                openingHour.setEndTime(DateTimeUtil.convertDateTimeToSqlTime(endTime));
                openingHour.setOpeningDay(openingDay);
                openingHour.setCreatedDate(DateTime.now().toDate());
                openingHourService.saveOpeningHour(openingHour);
                response.setStatus("success");
                response.setMessage("Your data has been successfully saved");
            }else{
                response.setStatus("failed");
                response.setMessage("OpeningDay id :: " + openingDayId + " is not found");
            }
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            logger.error(TAG, e.getMessage());
        }

        return response;
    }

    /**
     * @param openingHourId
     * @param startTime
     * @param endTime
     * @return ApiResponseBean
     */
    @RequestMapping(value = "/updateOpeningHour", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update an existing OpeningHour")
    public ApiResponseBean updateOpeningHour(
            @RequestParam(name = "openingHourId", required = true) long openingHourId,
            @RequestParam(name = "startTime", required = false) @DateTimeFormat(pattern="HH:mm") DateTime startTime,
            @RequestParam(name = "endTime", required = false) @DateTimeFormat(pattern="HH:mm") DateTime endTime
    ) {
        ApiResponseBean response = new ApiResponseBean();
        try {
            OpeningHour openingHour = openingHourService.getById(openingHourId);
            if(openingHour != null){
                openingHour.setUpdatedDate(DateTime.now().toDate());
                if (startTime != null) openingHour.setStartTime(DateTimeUtil.convertDateTimeToSqlTime(startTime));
                if (endTime != null) openingHour.setEndTime(DateTimeUtil.convertDateTimeToSqlTime(endTime));

                openingHourService.updateOpeningHour(openingHour);
                response.setStatus("success");
                response.setMessage("Your data has been successfully saved");
            }else{
                response.setStatus("failed");
                response.setMessage("openingHour id :: " + openingHourId + " is not found");
            }

        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            logger.error(TAG, e.getMessage());
        }

        return response;
    }

    /**
     * @param openingHourId
     * @return ApiResponseBean
     */
    @RequestMapping(value = "/deleteOpeningHour", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete OpeningHour")
    public ApiResponseBean deleteOpeningHour(
            @RequestParam(name = "openingHourId", required = true) long openingHourId
    ) {
        ApiResponseBean response = new ApiResponseBean();
        try {
            openingHourService.deleteOpeningHour(openingHourId);
            response.setStatus("success");
            response.setMessage("Your data has been deleted!");
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            logger.error(TAG, e.getMessage());
        }
        return response;
    }

}
