package com.example.simplerestaurantfinder.api.responses;

/**
 * Created by BERM-PC on 23/12/2559.
 */
public class ApiResponseBean {

    private String status;
    private String message;

    public ApiResponseBean(){

    }

    public ApiResponseBean(String status, String message) {
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
