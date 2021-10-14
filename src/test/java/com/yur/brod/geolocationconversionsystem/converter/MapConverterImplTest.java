package com.yur.brod.geolocationconversionsystem.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapConverterImplTest {
    MapConverter converter = new MapConverterImpl();

    @Test
    public void ShouldBeEquals(){
        String address = "Россия, Москва, улица Поклонная, 12";
        String sensor = "false";
        String expected = "sensor=false&address=%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D1%8F%2C+%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0%2C+%D1%83%D0%BB%D0%B8%D1%86%D0%B0+%D0%9F%D0%BE%D0%BA%D0%BB%D0%BE%D0%BD%D0%BD%D0%B0%D1%8F%2C+12";

        Map<String,String> map = Map.of(
                "address",address,
                "sensor",sensor);
        Assertions.assertEquals(expected,converter.convert(map));
    }

}