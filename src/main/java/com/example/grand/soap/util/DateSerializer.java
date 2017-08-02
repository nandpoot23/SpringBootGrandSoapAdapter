package com.example.grand.soap.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author mlahariya
 * @version 1.0, Dec 2016
 */

public class DateSerializer extends JsonSerializer<Calendar> {

    @Override
    public void serialize(Calendar value, JsonGenerator jgen, SerializerProvider serializers) throws IOException,
            JsonProcessingException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        jgen.writeString(formatter.format(value.getTime()));
    }

}
