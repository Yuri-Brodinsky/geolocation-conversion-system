package com.yur.brod.geolocationconversionsystem.converter;

import com.yur.brod.geolocationconversionsystem.handler.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class GoogleApiUrlProviderTest {

    private static GoogleApiUrlProvider provider;
    private final String message1 ="latitude must be in interval -90..90";
    private final String message2 ="longitude must be in interval -180..180";
    private final String message3 ="URL must be less then 2450 characters";
    @BeforeAll
    public static void init(){
        provider = new GoogleApiUrlProvider("url","json","false","ru","key");
    }
    @Test
    public void shouldThrowExceptionCauseLessMinLatitude(){
        Throwable thrown =Assertions.assertThrows(ValidationException.class
                ,()->provider.getUrl(-92,119));
        Assertions.assertEquals(message1,thrown.getMessage());
    }
    @Test
    public void shouldThrowExceptionCauseMoreMaxLatitude(){
        Throwable thrown =Assertions.assertThrows(ValidationException.class
                ,()->provider.getUrl(95,119));
        Assertions.assertEquals(message1,thrown.getMessage());
    }
    @Test
    public void shouldThrowExceptionCauseLessMinLongitude(){
        Throwable thrown =Assertions.assertThrows(ValidationException.class
                ,()->provider.getUrl(89,-185));
        Assertions.assertEquals(message2,thrown.getMessage());
    }
    @Test
    public void shouldThrowExceptionCauseMoreMaxLongitude(){
        Throwable thrown =Assertions.assertThrows(ValidationException.class
                ,()->provider.getUrl(89,185));
        Assertions.assertEquals(message2,thrown.getMessage());
    }
    @Test
    public void shouldThrowExceptionCauseMoreMaxUrlLength(){
        char [] chars = new char[2451];
        String url = new String(chars);
        Throwable thrown =Assertions.assertThrows(ValidationException.class
                ,()->provider.getUrl(url));
        Assertions.assertEquals(message3,thrown.getMessage());
    }
    @Test
    public void shouldNotThrowExceptionCauseValidLatlng(){
        Assertions.assertDoesNotThrow(()->provider.getUrl(90,180));
    }
    @Test
    public void shouldNotThrowExceptionCauseValidUrlLength(){
        Assertions.assertDoesNotThrow(()->provider.getUrl("http://..."));
    }
}