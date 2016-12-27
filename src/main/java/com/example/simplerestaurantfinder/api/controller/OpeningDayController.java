package com.example.simplerestaurantfinder.api.controller;


import com.example.simplerestaurantfinder.api.responses.ApiResponseBean;
import com.example.simplerestaurantfinder.model.OpeningDay;
import com.example.simplerestaurantfinder.model.Restaurant;
import com.example.simplerestaurantfinder.service.OpeningDayService;
import com.example.simplerestaurantfinder.service.RestaurantService;
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
    public ApiResponseBean createOpeningDay(
            @RequestParam(value = "restaurantId" , required = true  ) long restaurantId,
            @RequestParam(value = "dayOfWeek" , required = true  ) String dayOfWeek,
            @RequestParam(value = "isOpen" , required = false) boolean isOpen
    ) {
        ApiResponseBean response = new ApiResponseBean();
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
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponseBean.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ApiResponseBean updateOpeningDay(
            @RequestParam(value = "openingDayId" , required = true  ) long openingDayId,
            @RequestParam(value = "dayOfWeek" , required = false  ) String dayOfWeek,
            @RequestParam(value = "isOpen" , required = false) Boolean isOpen
    ) {
        ApiResponseBean response = new ApiResponseBean();
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
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponseBean.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ApiResponseBean deleteOpeningDay( @RequestParam(value = "openingDayId" , required = true  ) long openingDayId) {
        ApiResponseBean response = new ApiResponseBean();
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
