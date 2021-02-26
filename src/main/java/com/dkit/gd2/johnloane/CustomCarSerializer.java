package com.dkit.gd2.johnloane;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomCarSerializer extends StdSerializer<Car>
{
    public CustomCarSerializer()
    {
        this(null);
    }

    public CustomCarSerializer(Class<Car> t)
    {
        super(t);
    }

    @Override
    public void serialize(Car car, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        try
        {
            jsonGenerator.writeStartObject(); //{
            jsonGenerator.writeStringField("car_brand", car.getType()); //"car_brand" : "BMW"
            jsonGenerator.writeEndObject(); //}
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
    }
}
