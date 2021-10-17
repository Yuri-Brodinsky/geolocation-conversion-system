package com.yur.brod.geolocationconversionsystem.converter;


import com.google.common.base.Joiner;
import com.yur.brod.geolocationconversionsystem.handler.ValidationException;
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
    private final int LAT_MIN = -90;
    private final int LAT_MAX = 90;
    private final int LNG_MIN = -180;
    private final int LNG_MAX = 180;
    private final int LENGTH_MAX = 8192;

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
                "address",address,
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
        if(address.length()==0) {
            throw new ValidationException("Empty address");
        }
        String url = baseUrl + getParameters(getParamMap(address));
        if(url.length()>LENGTH_MAX) {
            throw new ValidationException("URL must be less then " + LENGTH_MAX+" characters");
        }
        return url;
    }
    public String getUrl( double latitude, double longitude){
        if(latitude<LAT_MIN||latitude>LAT_MAX) {
            throw new ValidationException("latitude must be in interval " + LAT_MIN + ".." + LAT_MAX);
        }
        if(longitude<LNG_MIN||longitude>LNG_MAX) {
            throw new ValidationException("longitude must be in interval " + LNG_MIN + ".." + LNG_MAX);
        }
        return baseUrl + getParameters(getParamMap(latitude,longitude));
    }

}
