package com.yur.brod.geolocationconversionsystem.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yur.brod.geolocationconversionsystem.handler.AddressNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;




@Service

public class GoogleApiConversionService implements ConversionService {

    private final UrlProvider urlProvider;
    private final RestOperations restOperations;


    public GoogleApiConversionService(UrlProvider urlProvider,RestOperations restOperations) {
        this.urlProvider = urlProvider;
        this.restOperations = restOperations;
    }


    @Override
    @Cacheable(cacheNames="coordinates")
    public double [] convertAddress(String address) {
        double [] coordinates = new double[2];
        String url = urlProvider.getUrl(address);
        String json = restOperations.getForObject(url,String.class);
        JsonNode node = getNode(json).get("geometry").get("location");;
        coordinates[1] = node.get("lat").asDouble();
        coordinates[0] = node.get("lng").asDouble();
        return coordinates;
    }

    @Override
    @Cacheable(cacheNames="addresses")
    public String convertCoordinates( double latitude, double longitude) {
        String url = urlProvider.getUrl(latitude,longitude);
        String json = restOperations.getForObject(url,String.class);
        JsonNode node = getNode(json);
        return node.get("formatted_address").asText();
    }
    private JsonNode getNode(String json){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node =null;
        try{
            node = mapper.readTree(json).get("results").get(0);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        if(node==null) throw new AddressNotFoundException("the address not found");
        return node;
    }
}
