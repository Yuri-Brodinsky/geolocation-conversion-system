package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.stereotype.Component;

@Component
public class GoogleApiConversionProvider {

    private final UrlProvider provider;

    public GoogleApiConversionProvider(UrlProvider provider) {
        this.provider = provider;
    }


    public String convert(String address){
        return null;
    }
}
