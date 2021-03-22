package com.larztalk.backend.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by raghvendra.mishra on 23/08/20.
 */
public class CalendarSerializer extends JsonSerializer<Calendar> {

    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public void serialize(Calendar calendar, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {

        String dateAsString = formatter.format(calendar.getTime());
        jsonGenerator.writeString(dateAsString);

    }
}
