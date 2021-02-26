package com.dkit.gd2.johnloane;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.List;
import java.util.Map;

/**
 * Demo JSON
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "JSON Demo" );
        ObjectMapper objectMapper = new ObjectMapper();

        Car volvo = new Car("Red", "Volvo");

        //writeCarJSONToFile(objectMapper, volvo);
        writeCarJSONToString(objectMapper, volvo);
        createCarFromJSONString(objectMapper);
        createCarFromFile(objectMapper);

        final String ISS_NOW_URI = "http://api.open-notify.org/iss-now.json";
        getISSPosition(objectMapper, ISS_NOW_URI);

        testJSONNode(objectMapper);
        createListFromJSONArrayString(objectMapper);
        createMapFromJSONArrayString(objectMapper);
        deserializeJSONStringExtraFields(objectMapper);
        useCustomSerializer(objectMapper);
    }

    private static void useCustomSerializer(ObjectMapper objectMapper)
    {
        SimpleModule module = new SimpleModule("CustomCarSerializer", new Version(1,0,0,null, null, null));
        module.addSerializer(Car.class, new CustomCarSerializer());
        objectMapper.registerModule(module);

        Car car = new Car("Black", "Porsche");
        try
        {
            String carJson = objectMapper.writeValueAsString(car);
            System.out.println("Custom version: " + carJson);
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }


    }

    private static void deserializeJSONStringExtraFields(ObjectMapper objectMapper)
    {
        String jsonString = "{\"colour\":\"Black\", \"type\":\"Ford\", \"year\":\"2020\"}";
        try
        {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Car car = objectMapper.readValue(jsonString, Car.class);
            System.out.println("Deserialized car: " + car);
        }
        catch(JsonMappingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void createMapFromJSONArrayString(ObjectMapper objectMapper)
    {
        String jsonCarMap = "{\"colour\":\"White\", \"type\":\"BMW\"}";

        try
        {
            Map<String, Object> carMap = objectMapper.readValue(jsonCarMap, new TypeReference<Map<String, Object>>() {});
            printMap(carMap);
        }
        catch(JsonMappingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void printMap(Map<String, Object> carMap)
    {
        for(Map.Entry<String, Object> entry : carMap.entrySet())
        {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static void createListFromJSONArrayString(ObjectMapper objectMapper)
    {
        String jsonCarArray = "[{\"colour\":\"White\", \"type\":\"BMW\"},{\"colour\":\"Blue\", \"type\":\"Skoda\"},{\"colour\":\"Grey\", \"type\":\"Delorian\"}]";

        try
        {
            List<Car> carList = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>() {});
            printList(carList);
        }
        catch(JsonMappingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void printList(List<Car> carList)
    {
        for(Car car : carList)
        {
            System.out.println("Reading JSON array: " + car);
        }
    }

    private static void testJSONNode(ObjectMapper objectMapper)
    {
        String jsonString = "{\"colour\":\"Faded Red\", \"type\":\"FIAT\"}";
        try
        {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String colour = jsonNode.get("colour").asText();
            System.out.println("The colour of the JsonNode object is " + colour);
        }
        catch(JsonMappingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void getISSPosition(ObjectMapper objectMapper, String iss_now_uri)
    {
        try
        {
            SpaceStationLocation currentLocation = objectMapper.readValue(new URL(iss_now_uri), SpaceStationLocation.class);
            System.out.println(currentLocation);
        }
        catch(JsonMappingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void createCarFromFile(ObjectMapper objectMapper)
    {
        Car car = null;

        try
        {
            car = objectMapper.readValue(new File("volvo.json"), Car.class);
            System.out.println("Creating car from json file " + car);
        }
        catch(JsonMappingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void createCarFromJSONString(ObjectMapper objectMapper)
    {
        Car car = null;

        String jsonString = "{\"colour\":\"Black\", \"type\":\"Ford\", \"year\":\"2020\"}";
        try
        {

            car = objectMapper.readValue(jsonString, Car.class);
            System.out.println("Creating car from String");
            System.out.println("Read car from JSON String: " + car);
        }
        catch(JsonMappingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void writeCarJSONToString(ObjectMapper objectMapper, Car volvo)
    {
        try
        {
            String carAsString = objectMapper.writeValueAsString(volvo);
            System.out.println(carAsString);
        }
        catch(JsonProcessingException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void writeCarJSONToFile(ObjectMapper objectMapper, Car volvo)
    {
        try
        {
            objectMapper.writeValue(new File("volvo.json"), volvo);
        }
        catch(JsonGenerationException e)
        {
            System.out.println(e.getMessage());
        }
        catch(JsonMappingException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
