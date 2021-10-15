package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Size;

@Service
@Validated
public class GoogleApiConversionService implements ConversionService {

    private final UrlProvider urlProvider;
    private final RestTemplate restTemplate;
    private final int minLat=-90;
    private int maxLat=90;
    private int minLng=-180;
    private int maxLng=180;
    private int maxAdr=5;

    public GoogleApiConversionService(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
        restTemplate = new RestTemplate();
    }

    @Override
    public String convertAddress(@Size(min=1,max = 300,message = "dfdf")String address) {
        String url = urlProvider.getUrl(address);
        int length = url.length();
        return null;//restTemplate.getForObject(url,String.class);
    }

    @Override
    public String convertCoordinates( double latitude, double longitude) {
        String url = urlProvider.getUrl(latitude,longitude);
        System.out.println(url);
        return restTemplate.getForObject(url,String.class);
    }
}
