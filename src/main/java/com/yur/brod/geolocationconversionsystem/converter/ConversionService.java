package com.yur.brod.geolocationconversionsystem.converter;

public interface ConversionService {
    String convertAddress(String s);
    String convertCoordinates(double [] coordinates);
}
