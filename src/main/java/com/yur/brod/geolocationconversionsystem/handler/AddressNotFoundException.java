package com.yur.brod.geolocationconversionsystem.handler;

public class AddressNotFoundException extends RuntimeException {
    private String message;
    public AddressNotFoundException(String message){
        super(message);
    }
}
