package com.yur.brod.geolocationconversionsystem.converter;




public interface ConversionService {
    Double [] convertAddress( String s);
    String convertCoordinates(double latitude, double longitude);
}
