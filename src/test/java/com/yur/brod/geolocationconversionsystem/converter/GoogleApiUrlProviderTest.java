package com.yur.brod.geolocationconversionsystem.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoogleApiUrlProviderTest {
    private final UrlProvider provider;

    GoogleApiUrlProviderTest(UrlProvider provider) {
        this.provider = provider;
    }

    /*@BeforeAll
    public static void init(){
        provider = new GoogleApiUrlProvider(new MapConverterImpl());
    }*/
    @ParameterizedTest
    public void ShouldBeEqualsCaseOne(){
        String param = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D1%8F%2C+%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0%2C+%D1%83%D0%BB%D0%B8%D1%86%D0%B0+%D0%9F%D0%BE%D0%BA%D0%BB%D0%BE%D0%BD%D0%BD%D0%B0%D1%8F%2C+12";
        String address ="Россия, Москва, улица Поклонная, 12";
        Assertions.assertEquals(param,provider.getUrl(address));

    }

}