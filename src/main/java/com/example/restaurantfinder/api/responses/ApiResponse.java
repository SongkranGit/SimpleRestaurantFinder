package com.example.restaurantfinder.api.responses;

/**
 * Created by BERM-PC on 23/12/2559.
 */
public class ApiResponse {

    private String status;
    private String message;

    public ApiResponse(){

    }

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
