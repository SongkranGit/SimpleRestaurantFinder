package com.example.restaurantfinder.api.controllers;

import com.example.restaurantfinder.api.responses.ApiResponse;
import com.example.restaurantfinder.model.OpeningDay;
import com.example.restaurantfinder.model.Restaurant;
import com.example.restaurantfinder.service.OpeningDayService;
import com.example.restaurantfinder.service.RestaurantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BERM-PC on 24/12/2559.
 */

@RestController
@RequestMapping("/Api/OpeningDay")
public class OpeningDayController {


    @Autowired
    OpeningDayService openingDayService;

    @Autowired
    RestaurantService restaurantService;

    /** ============================= Create OpeningDay ====================================*/

    @RequestMapping(value = "/createOpeningDay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new OpeningDay")
    public ApiResponse createOpeningDay(
            @RequestParam(value = "restaurantId" , required = true  ) long restaurantId,
            @RequestParam(value = "dayOfWeek" , required = true  ) String dayOfWeek,
            @RequestParam(value = "isOpen" , required = false) boolean isOpen
    ) {
        ApiResponse response = new ApiResponse();
        try {
            OpeningDay openingDay = new OpeningDay();
            openingDay.setDayOfWeek(dayOfWeek);
            openingDay.setOpen(isOpen);
            openingDay.setCreatedDate(DateTime.now().toDate());

            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
            if(restaurant != null){
                openingDay.setRestaurant(restaurant);
                openingDayService.saveOpeningDay(openingDay);
                response.setStatus("success");
            }else{
                response.setStatus("failed");
                response.setMessage("Restaurant id "+restaurantId+" is not found");
            }
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    /** ============================= Update OpeningDay ====================================*/

    @RequestMapping(value = "/updateOpeningDay", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update an existing OpeningDay")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ApiResponse updateOpeningDay(
            @RequestParam(value = "openingDayId" , required = true  ) long openingDayId,
            @RequestParam(value = "dayOfWeek" , required = false  ) String dayOfWeek,
            @RequestParam(value = "isOpen" , required = false) Boolean isOpen
    ) {
        ApiResponse response = new ApiResponse();
        try {
            OpeningDay openingDay = openingDayService.getById(openingDayId);
            openingDay.setUpdatedDate(DateTime.now().toDate());
            if(!dayOfWeek.isEmpty()){
                openingDay.setDayOfWeek(dayOfWeek);
            }

            if(isOpen != null){
                openingDay.setOpen(isOpen);
            }
            openingDayService.updateOpeningDay(openingDay);
            response.setStatus("success");
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /** ============================= Delete OpeningDay ====================================*/

    @RequestMapping(value = "/deleteOpeningDay", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete OpeningDay")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ApiResponse deleteOpeningDay( @RequestParam(value = "openingDayId" , required = true  ) long openingDayId) {
        ApiResponse response = new ApiResponse();
        try {
            openingDayService.deleteOpeningDay(openingDayId);
            response.setStatus("success");
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /** ============================= Get OpeningDay By Id====================================*/

    @RequestMapping(value = "/getOpeningDayById", method = RequestMethod.GET)
    @ApiOperation(value = "Find an openingDay by Id ")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = OpeningDay.class),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<OpeningDay> getOpeningDayById(
            @RequestParam(value = "openingDayId" , required = true  ) long openingDayId
    ) {
        OpeningDay openingDay = openingDayService.getById(openingDayId);
        return new ResponseEntity(openingDay, HttpStatus.OK);
    }

}
