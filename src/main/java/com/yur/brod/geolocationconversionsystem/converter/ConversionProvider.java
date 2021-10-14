package com.yur.brod.geolocationconversionsystem.converter;

import java.io.IOException;

public interface ConversionProvider {
    String convert(String address) ;
    String convert(String [] coordinates) ;
}
