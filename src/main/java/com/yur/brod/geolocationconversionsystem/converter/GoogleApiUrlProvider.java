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

    public static String convertEntry(final Map.Entry<String,String> entry){
        return entry.getKey() + "=" +
                URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);
    }

    public String getCoordinates(final String latitude,final String longitude){
        return latitude + ", " + longitude;
    }
    private Map<String,String> getParamMap(final String address){
        return Map.of(
                "address",address,
                "sensor",sensor,
                "key",apiKey);
    }
    private Map<String,String> getParamMap(final String latitude,final String longitude){
        return Map.of(
                "sensor",sensor,
                "language",language,
                "latlng",getCoordinates(latitude,longitude),
                "key",apiKey);
    }
    public String getParameters(final Map<String, String> parameters) {
        return Joiner.on("&").join(parameters
                .entrySet().stream()
                .map(GoogleApiUrlProvider::convertEntry)
                .collect(Collectors.toList()));
    }

    public String getUrl(final String address){
        return baseUrl + getParameters(getParamMap(address));
    }
    public String getUrl(final String latitude,final String longitude){
        return baseUrl + getParameters(getParamMap(latitude,longitude));
    }

}
