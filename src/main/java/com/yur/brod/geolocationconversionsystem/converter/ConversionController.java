package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/converter")
public class ConversionController {

    private final ConversionService service;
    private final GoogleApiUrlProvider provider;

    public ConversionController(ConversionService service,GoogleApiUrlProvider provider) {
        this.service = service;
        this.provider = provider;
    }
    @PostMapping("/ll")
    public String getAddress(@RequestBody double [] coordinates){

        return service.convertCoordinates(coordinates);
    }
    @PostMapping("/adr")
    public String getCoordinates(@RequestBody String address){

        return service.convertAddress(address);
    }
}
