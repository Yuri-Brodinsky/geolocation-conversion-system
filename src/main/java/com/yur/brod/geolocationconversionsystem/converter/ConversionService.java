package com.yur.brod.geolocationconversionsystem.converter;




public interface ConversionService {
    double [] convertAddress( String s);
    String convertCoordinates(double latitude, double longitude);
}
