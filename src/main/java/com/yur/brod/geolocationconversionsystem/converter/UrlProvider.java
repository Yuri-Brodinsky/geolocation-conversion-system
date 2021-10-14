package com.yur.brod.geolocationconversionsystem.converter;

import java.util.Map;

public interface UrlProvider {
    String getUrl(String address);
    String getUrl(String latitude,String longitude);
}
