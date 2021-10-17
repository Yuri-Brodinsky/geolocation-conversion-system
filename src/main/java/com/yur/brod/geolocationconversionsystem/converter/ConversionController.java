package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/conversion-system")
public class ConversionController {

    private final ConversionService service;

    public ConversionController(ConversionService service) {
        this.service = service;
    }
    @PostMapping("/address")
    public String  getAddress(@RequestBody double [] coordinates){
        return service.convertCoordinates(coordinates[1],coordinates[0]);
    }
    @PostMapping("/latlng")
    public double [] getCoordinates(@RequestBody String address){
        return service.convertAddress(address);
    }
}
