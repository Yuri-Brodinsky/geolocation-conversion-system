package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleApiConversionService implements ConversionService {

    private final UrlProvider urlProvider;
    private final RestTemplate restTemplate;

    public GoogleApiConversionService(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
        restTemplate = new RestTemplate();
    }

    @Override
    public String convertAddress(final String address) {
        final String url = urlProvider.getUrl(address);
        return restTemplate.getForObject(url,String.class);
    }

    @Override
    public String convertCoordinates(final double[] coordinates) {
         final String [] coor = new String[2];
         if((coordinates[0]>90||coordinates[0]<-90)||(coordinates[0]>90||coordinates[0]<-90))
         coor[0] = new String(String.valueOf(coordinates[0]));
         coor[1] = new String(String.valueOf(coordinates[1]));
         final String url = urlProvider.getUrl(coor);
         return restTemplate.getForObject(url,String.class);
    }
    public String convertCoordinates(final double latitude,final double longitude) {
       String [] coor = new String[2];
            coor[0] = new String(String.valueOf(latitude));
        coor[1] = new String(String.valueOf(longitude));
        final String url = urlProvider.getUrl(coor);
        return restTemplate.getForObject(url,String.class);
    }
}
