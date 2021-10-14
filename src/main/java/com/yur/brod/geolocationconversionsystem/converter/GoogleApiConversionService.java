package com.yur.brod.geolocationconversionsystem.converter;

import org.springframework.stereotype.Service;

@Service
public class GoogleApiConversionService implements ConversionService {
    private final ConversionProvider provider;

    public GoogleApiConversionService(ConversionProvider provider) {
        this.provider = provider;
    }

    @Override
    public String convertAddress(final String address) {
      return provider.convert(address);

    }

    @Override
    public String convertCoordinates(final double[] coordinates) {
        try{ final String [] coor = new String[2];
            coor[0] = new String(String.valueOf(coordinates[0]));
            coor[1] = new String(String.valueOf(coordinates[1]));
            final String address = provider.convert(coor);
            return address;}
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
