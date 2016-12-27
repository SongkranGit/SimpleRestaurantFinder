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

    /**
     * Description :: createRestaurant
     * @param name          restaurant name
     * @param description   description of the restaurant
     * @param latitude      a location of restaurant
     * @param longitude     a location of restaurant
     * @return ApiResponse
     */
    @RequestMapping(value = "/createRestaurant", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiOperation(value = "Add new restaurant ")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ApiResponse> createRestaurant(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "latitude", required = true) Double latitude,
            @RequestParam(value = "longitude", required = true) Double longitude
    ) {

        ApiResponse response = new ApiResponse();
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(name);
            restaurant.setDescription(description);
            restaurant.setLatitude(latitude);
            restaurant.setLongitude(longitude);
            restaurant.setCreatedDate(DateTime.now().toDate());

            if (latitude != null && longitude != null) {
                Point point = GeographyUtil.createPoint(latitude, longitude);
                restaurant.setLocation(point);
                restaurantService.saveRestaurant(restaurant);
                response.setStatus("success");
                response.setMessage("Your data has been successfully saved");
            } else {
                response.setStatus("failed");
                response.setMessage("Your data fields latitude or longitude not have value");
            }

        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    /**
     * Description  ::  updateRestaurant
     * @param restaurantId
     * @param name          restaurant name
     * @param description   description of the restaurant
     * @param latitude      a location of restaurant
     * @param longitude     a location of restaurant
     * @return ApiResponse      response message to client
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/updateRestaurant", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiMethod(description = "Update restaurant")
    @ApiOperation(value = "Update an existing restaurant")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ApiResponse updateRestaurant(
            @RequestParam(value = "restaurantId", required = true) long restaurantId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude
    ) {
        ApiResponse response = new ApiResponse();
        try {
            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
            if (restaurant != null) {
                if (name != null && !name.isEmpty()) restaurant.setName(name);
                if (description != null && !description.isEmpty()) restaurant.setDescription(description);
                if (latitude != null) restaurant.setLatitude(latitude);
                if (longitude != null) restaurant.setLongitude(longitude);
                restaurant.setUpdatedDate(DateTime.now().toDate());
                restaurantService.updateRestaurant(restaurant);
                response.setStatus("success");
                response.setMessage("Your data has been successfully saved");
            } else {
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

    /**
     * Description ::  deleteRestaurant
     * @param restaurantId      restaurantId
     * @return ApiResponse      response message to client
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteRestaurant")
    @ApiOperation(value = "Delete a restaurant")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ApiResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ApiResponse deleteRestaurant(
            @RequestParam(value = "restaurantId", required = true) long restaurantId) {
        ApiResponse response = new ApiResponse();
        try {
            restaurantService.deleteRestaurant(restaurantId);
            response.setStatus("success");
            response.setMessage("Your data has been deleted!");
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Description  :: getAllRestaurants
     *
     * @return List<Restaurant>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAllRestaurants")
    @ApiMethod(description = "Finds all restaurant where the price per night is less than the provided value")
    @ApiOperation(value = "Finds all restaurant", responseContainer = "List")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        return new ResponseEntity(restaurants, HttpStatus.OK);
    }


    /**
     * Description ::  getRestaurantById
     *
     * @param restaurantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRestaurantById")
    @ApiOperation(value = "Find restaurant by Id")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<Restaurant> getRestaurantById(@RequestParam(value = "restaurantId", required = true) long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity(restaurant, HttpStatus.OK);
    }



    /**
     *  Description ::  GetRestaurantByName
     *
     * @param name  restaurant name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRestaurantByName")
    @ApiOperation(value = "Find restaurant by Name")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<Restaurant> getRestaurantByName(@RequestParam(value = "name") String name) {
        Restaurant restaurant = restaurantService.getRestaurantByName(name);
        return new ResponseEntity(restaurant, HttpStatus.OK);
    }


     /**
     *
     * Description :: GetRestaurantsByNameOrDescription
     *
     * @param name              restaurant name
     * @param description       description of the restaurant
     * @return List<Restaurant>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRestaurantByNameOrDescription")
    @ApiOperation(value = "Find restaurant by Name or Description", responseContainer = "List")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Restaurant>> getRestaurantByNameOrDescription(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description) {
        List<Restaurant> restaurants = restaurantService.getRestaurantByNameOrDescription(name, description);
        return new ResponseEntity(restaurants, HttpStatus.OK);
    }



    /**
     * Description :: Search Restaurant within a specified area
     *
     * @param latitude
     * @param longitude
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRestaurantByLocation")
    @ApiOperation(value = "Find Restaurant within a specified area")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<Restaurant> getRestaurantByLocation(
            @RequestParam(value = "latitude", required = true) Double latitude,
            @RequestParam(value = "longitude", required = true) Double longitude) {

        Restaurant restaurant = null;
        if (latitude != null && longitude != null) {
            restaurant = restaurantService.getRestaurantByLocation(latitude, longitude);
        }
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }


    /**
     * Description :: Search Restaurants nearby within radius to define search area
     *
     * @param latitude
     * @param longitude
     * @param radius
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRestaurantsNearbyWithInRadius")
    @ApiOperation(value = "Finds Restaurants nearby within radius to define search area", responseContainer = "List")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Restaurant>> getRestaurantsNearbyWithInRadius(
            @RequestParam(value = "latitude", required = true) double latitude,
            @RequestParam(value = "longitude", required = true) double longitude,
            @RequestParam(value = "radius", required = true) double radius
    ) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsWithInRadius(latitude, longitude, radius);
        return new ResponseEntity(restaurants, HttpStatus.OK);
    }


    /**
     *
     * Description :: Search Restaurants nearby within radius and  open now
     *
     * @param latitude          location of restaurant
     * @param longitude         location of restaurant
     * @param radius            radius of distance (Km)
     * @param currentDateTime   datetime now
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRestaurantsNearbyWithInRadiusAndOpenNow")
    @ApiOperation(value = "Finds Restaurants nearby within radius and  open now", responseContainer = "List")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Restaurant.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Restaurant>> getRestaurantsNearbyWithInRadiusAndOpenNow(
            @RequestParam(value = "latitude", required = true) double latitude,
            @RequestParam(value = "longitude", required = true) double longitude,
            @RequestParam(value = "radius", required = true) double radius,
            @RequestParam(value = "currentDateTime", required = true) DateTime currentDateTime
    ) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsWithInRadiusAndOpenNow(latitude, longitude, radius, currentDateTime);
        return new ResponseEntity(restaurants, HttpStatus.OK);
    }


}
