package com.yur.brod.geolocationconversionsystem.converter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GoogleApiUrlProvider implements UrlProvider{
    @Value("${google-api.url}")
    private String url;
    @Value("${google-api.format}")
    private String format;
    @Value("${google-api.sensor}")
    private String sensor;
    @Value("${google-api.language}")
    private String language;
    private final MapConverter mapConverter;

    public GoogleApiUrlProvider(MapConverter mapConverter,String sensor) {
        this.mapConverter = mapConverter;
    }

    private Map<String,String> getParamMap(final String address){
        System.out.println(url);
        final Map<String,String> map = Map.of(
                "address",address,
                "sensor",sensor);
        return map;
    }
    private Map<String,String> getParamMap(final String [] geolocation){
        final Map<String,String> map = Map.of(
                "sensor",sensor,
                "language",language,
                "latlang",geolocation[0]+","+geolocation[1]);
        return map;
    }
    public String getUrl(final String address){
        final String parameters = mapConverter.convert(getParamMap(address));
        final StringBuffer finalUrl=new StringBuffer()
                .append(url)
                .append(format)
                .append("?");
        return url.toString();
    }

}
