package com.dkit.gd2.johnloane;

public class Car
{
    private String colour;
    private String type;

    public Car(String colour, String type)
    {
        this.colour = colour;
        this.type = type;
    }
    //essential for creating a car from a JSON String
    public Car()
    {
    }

    public String getColour()
    {
        return colour;
    }

    public String getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return "Car{" +
                "colour='" + colour + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }
}
