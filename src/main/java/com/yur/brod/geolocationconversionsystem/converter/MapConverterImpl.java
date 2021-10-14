package com.yur.brod.geolocationconversionsystem.converter;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Map;
@Component
public class MapConverterImpl implements MapConverter{

    @Override
    public String convert(Map<String, String> parameters) {
        final String param = Joiner.on("&").join(Iterables.transform(parameters.entrySet(), MapConverterImpl::conv));
        return param;
    }
    public static String conv(final Entry<String,String> entry){
        final StringBuffer buffer;
        try {
            buffer = new StringBuffer()
                    .append(entry.getKey())
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(),"utf-8"));
            return buffer.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
