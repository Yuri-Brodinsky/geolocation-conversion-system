package com.yur.brod.geolocationconversionsystem.converter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Validated
public interface ConversionService {
    String convertAddress(@Size(min=1,max = 300,message = "dfdf")String s);
    String convertCoordinates(@Range(min=-90,max = 90,message = "22") double latitude,
                              @Range(min=-180,max = 180,message = "33")double longitude);
}
