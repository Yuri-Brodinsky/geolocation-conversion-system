package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

@Component
public class GoogleApiConversionProvider implements ConversionProvider {

    private final UrlProvider provider;
    private final JsonReader reader;
    private final RestTemplate restTemplate;

    public GoogleApiConversionProvider(UrlProvider provider,JsonReader reader) {
        this.reader = reader;
        this.provider = provider;
        restTemplate = new RestTemplate();
    }

    /*public String convert(final String address) throws IOException {
        final URL url = new URL(provider.getUrl(address));
        return reader.convert(url);
    }
    public String convert(final String [] coordinates) throws IOException {
        final URL url = new URL(provider.getUrl(coordinates));
        return reader.convert(url);
    }*/
    public String convert(final String address) {
        final String url = provider.getUrl(address);
        return restTemplate.getForObject(url,String.class);
    }
    public String convert(final String [] coordinates)  {
        final String url = provider.getUrl(coordinates);
        return restTemplate.getForObject(url,String.class);
    }
}
