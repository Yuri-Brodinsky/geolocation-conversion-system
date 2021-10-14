package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
@Component
public class JsonReader {
    public String readAll(final Reader reader) throws IOException {
        final StringBuilder builder = new StringBuilder();
        int cp;
        while ((cp = reader.read())!=-1){
            builder.append((char)cp);
        }
        return builder.toString();
    }
    public String convert(URL url) throws IOException {
        final InputStream inputStream = url.openStream();
        try{
            final BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            final String string = readAll(rd);
            return string;
        }
        finally {
            inputStream.close();
        }
    }
}
