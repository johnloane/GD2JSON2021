package com.dkit.gd2.johnloane;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomCarDeserializer extends StdDeserializer<Car>
{
    public CustomCarDeserializer()
    {
        this(null);
    }

    public CustomCarDeserializer(Class<Car> c)
    {
        super(c);
    }

    @Override
    public Car deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        Car car = new Car();
        ObjectCodec codec = jsonParser.getCodec();

        try
        {
            JsonNode node = codec.readTree(jsonParser);
            JsonNode colourNode = node.get("colour");
            String colour = colourNode.asText();
            car.setColour(colour);
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
        return car;
    }
}
