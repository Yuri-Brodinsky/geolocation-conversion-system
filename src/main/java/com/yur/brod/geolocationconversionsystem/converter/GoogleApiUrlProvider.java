package com.yur.brod.geolocationconversionsystem.converter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GoogleApiUrlProvider implements UrlProvider{
    private final String url;
    private final String format;
    private final String sensor;
    private final String language;
    private final String baseUrl;
    private final MapConverter mapConverter;

    public GoogleApiUrlProvider(MapConverter mapConverter,
                                @Value("${google-api.url}")String url,
                                @Value("${google-api.format}")String format,
                                @Value("${google-api.format}")String sensor,
                                @Value("${google-api.language}")String language) {
        this.mapConverter = mapConverter;
        this.url =  url;
        this.format = format;
        this.sensor = sensor;
        this.language = language;
        baseUrl = new StringBuilder()
                .append(url)
                .append(format)
                .append("?").toString();
    }

    private Map<String,String> getParamMap(final String address){
        System.out.println(url);
        final Map<String,String> map = Map.of(
                "address",address,
                "sensor",sensor);
        return map;
    }
    private Map<String,String> getParamMap(final String [] geolocation){
        final String coordinates = new StringBuffer()
                .append(geolocation[0])
                .append(", ")
                .append(geolocation[1]).toString();
        final Map<String,String> map = Map.of(
                "sensor",sensor,
                "language",language,
                "latlng",coordinates);
        return map;
    }

    public String getUrl(final String address){
        final String parameters = mapConverter.convert(getParamMap(address));
        final StringBuffer finalUrl=new StringBuffer(baseUrl).append(parameters);
        return finalUrl.toString();
    }
    public String getUrl(final String [] coordinates){
        final String parameters = mapConverter.convert(getParamMap(coordinates));
        final StringBuffer finalUrl=new StringBuffer(baseUrl).append(parameters);
        return finalUrl.toString();
    }

}
