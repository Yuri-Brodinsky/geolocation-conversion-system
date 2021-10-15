package com.yur.brod.geolocationconversionsystem.converter;


import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GoogleApiUrlProvider implements UrlProvider{
    private final String apiKey;
    private final String baseUrl;
    private final String language;
    private final String sensor;

    public GoogleApiUrlProvider(@Value("${google-api.url}")String url,
                                @Value("${google-api.format}")String format,
                                @Value("${google-api.format}")String sensor,
                                @Value("${google-api.language}")String language,
                                @Value("${google-api.api-key}")String apiKey) {
        this.apiKey = apiKey;
        this.sensor = sensor;
        this.language = language;
        baseUrl = url + format + "?";
    }


    private Map<String,String> getParamMap( String address){
        String encodedAddress = URLEncoder.encode(address,StandardCharsets.UTF_8);
        return Map.of(
                "address",encodedAddress,
                "sensor",sensor,
                "key",apiKey);
    }
    private Map<String,String> getParamMap( double latitude, double longitude){
        String lat = String.valueOf(latitude);
        String lng = String.valueOf(longitude);
        return Map.of(
                "sensor",sensor,
                "language",language,
                "latlng",lat+","+lng ,
                "key",apiKey);
    }
    public String getParameters(final Map<String, String> parameters) {
        return Joiner.on("&").join(parameters
                .entrySet().stream()
                .map(e->e.getKey()+"="+e.getValue())
                .collect(Collectors.toList()));
    }

    public String getUrl(String address){
        return baseUrl + getParameters(getParamMap(address));
    }
    public String getUrl( double latitude, double longitude){
        return baseUrl + getParameters(getParamMap(latitude,longitude));
    }

}
