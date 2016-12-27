package com.example.restaurantfinder.api.controllers;


import com.example.restaurantfinder.model.*;
import com.example.restaurantfinder.service.RestaurantService;
import com.example.restaurantfinder.utils.GeographyUtil;
import com.vividsolutions.jts.geom.Point;
import io.swagger.annotations.*;
import org.joda.time.DateTime;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.restaurantfinder.api.responses.ApiResponse;


import java.util.List;

/**
 * Created by BERM-PC on 22/12/2559.
 */

@RestController
@RequestMapping("/Api/Restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

   /** ============================= Create Restaurant ====================================*/

    @RequestMapping(value = "/createRestaurant", method = RequestMethod.POST ,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE ,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
       )
    @ApiOperation( value = "Add new restaurant ")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ApiResponse> createRestaurant(
        @RequestParam(value = "name" , required = true  ) String name,
        @RequestParam(value = "description" , required = false) String description,
        @RequestParam(value = "latitude" , required = true) Double latitude,
        @RequestParam(value = "longitude" , required = true) Double longitude
        ){

        ApiResponse response = new ApiResponse();
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(name);
            restaurant.setDescription(description);
            restaurant.setLatitude(latitude);
            restaurant.setLongitude(longitude);
            restaurant.setCreatedDate(DateTime.now().toDate());

            if(!latitude.isNaN() && !longitude.isNaN()){
                Point point = GeographyUtil.createPoint(latitude , longitude);
                restaurant.setLocation(point);
                restaurantService.saveRestaurant(restaurant);
                response.setStatus("success");
            }else {
                response.setStatus("failed");
            }

        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity( response , HttpStatus.CREATED);
    }

    /** ============================= Update Restaurant ====================================*/

    @RequestMapping(method = RequestMethod.PUT, value="/updateRestaurant", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiMethod(description = "Update restaurant")
    @ApiOperation(value = "Update an existing restaurant")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ApiResponse updateRestaurant(
            @RequestParam(value = "restaurantId" , required = true ) long restaurantId,
            @RequestParam(value = "name" , required = true ) String name,
            @RequestParam(value = "description" , required = false) String description,
            @RequestParam(value = "latitude" , required = true) Double latitude,
            @RequestParam(value = "longitude" , required = true) Double longitude
       ){
        ApiResponse response = new ApiResponse();
        try {
            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
            if(restaurant != null){
                restaurant.setName(name);
                restaurant.setDescription(description);
                restaurant.setLatitude(latitude);
                restaurant.setLongitude(longitude);
                restaurant.setUpdatedDate(DateTime.now().toDate());
                restaurantService.updateRestaurant(restaurant);
                response.setStatus("success");
            }else{
                response.setStatus("failed");
                response.setMessage("Not found record");
            }
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    /** ============================= Delete Restaurant ===================================*/

    @RequestMapping(method = RequestMethod.DELETE, value="/deleteRestaurant")
    @ApiOperation(value = "Delete a restaurant")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ApiResponse deleteRestaurant(
            @RequestParam(value = "restaurantId" , required = true )  long restaurantId){
        ApiResponse response = new ApiResponse();
        try {
            restaurantService.deleteRestaurant(restaurantId);
            response.setStatus("success");
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /** ============================= Get all Restaurants =====================================*/

    @RequestMapping(method = RequestMethod.GET, value="/getAllRestaurants")
    @ApiMethod(description = "Finds all restaurant where the price per night is less than the provided value")
    @ApiOperation( value = "Finds all restaurant" , responseContainer = "List")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        return new ResponseEntity(restaurants, HttpStatus.OK);
    }

    /** ============================= GetRestaurantById  =====================================*/

    @RequestMapping(method = RequestMethod.GET, value="/getRestaurantById")
    @ApiOperation( value = "Finds restaurant by Id")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<Restaurant> getRestaurantById(@RequestParam(value = "restaurantId" , required = true) long restaurantId){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity(restaurant, HttpStatus.OK);
    }


    /** ============================= GetRestaurantByName  =====================================*/

    @RequestMapping(method = RequestMethod.GET, value="/getRestaurantByName")
    @ApiOperation( value = "Finds restaurant by Name")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<Restaurant> getRestaurantByName(@RequestParam(value = "name" ) String name){
        Restaurant restaurant = restaurantService.getRestaurantByName(name);
        return new ResponseEntity(restaurant, HttpStatus.OK);
    }

    /** ============================= GetRestaurantsByNameOrDescription =====================================*/

    @RequestMapping(method = RequestMethod.GET, value="/getRestaurantByNameOrDescription")
    @ApiOperation( value = "Finds restaurant by Name or Description" , responseContainer = "List")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Restaurant>> getRestaurantByNameOrDescription(@RequestParam(value = "name" , required = false) String name ,
                                                                             @RequestParam(value = "description" , required = false) String description){
        List<Restaurant> restaurants = restaurantService.getRestaurantByNameOrDescription(name , description);
        return new ResponseEntity(restaurants, HttpStatus.OK);
    }


    /** ============================= Search Restaurant within a specified area =====================================*/

    @RequestMapping(method = RequestMethod.GET, value="/getRestaurantByLocation")
    @ApiOperation( value = "Search Restaurant within a specified area" )
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<Restaurant> getRestaurantByLocation(
            @RequestParam(value = "latitude" , required = true) Double latitude ,
            @RequestParam(value = "longitude" , required = true) Double longitude){

        Restaurant restaurant = null;
        if(!latitude.isNaN() && !longitude.isNaN()){
            restaurant =  restaurantService.getRestaurantByLocation(latitude , longitude);
        }
        return new ResponseEntity<Restaurant>(restaurant , HttpStatus.OK);
    }



    /** ============================= Search Restaurants within radius to define search area =====================================*/

    @RequestMapping(method = RequestMethod.GET, value="/getRestaurantsWithInRadius")
    @ApiOperation( value = "Search Restaurants within radius to define search area" , responseContainer = "List")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Restaurant>> getRestaurantsWithInRadius(
            @RequestParam(value = "latitude" , required = true) double latitude ,
            @RequestParam(value = "longitude" , required = true) double longitude,
            @RequestParam(value = "radius" , required = true) double radius
       ){

        List<Restaurant> restaurants = restaurantService.getRestaurantsWithInRadius(latitude , longitude , radius);

        return new ResponseEntity(restaurants, HttpStatus.OK);
    }



    /** ============================= Search Restaurants with in radius and  open now =====================================*/

    @RequestMapping(method = RequestMethod.GET, value="/getRestaurantsWithInRadiusAndOpenNow")
    @ApiOperation( value = "Search Restaurants within radius and  now open" , responseContainer = "List")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Restaurant>> getRestaurantsWithInRadiusAndOpenNow(
            @RequestParam(value = "latitude" , required = true) double latitude ,
            @RequestParam(value = "longitude" , required = true) double longitude,
            @RequestParam(value = "radius" , required = true) double radius,
            @RequestParam(value = "currentDateTime" , required = true) DateTime currentDateTime
    ){
        List<Restaurant> restaurants = restaurantService.getRestaurantsWithInRadiusAndOpenNow(latitude , longitude , radius , currentDateTime);

        return new ResponseEntity(restaurants, HttpStatus.OK);
    }




}
