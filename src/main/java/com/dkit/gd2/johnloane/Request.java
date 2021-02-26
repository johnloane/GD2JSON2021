package com.dkit.gd2.johnloane;

import java.util.Date;

public class Request
{
    private Car car;
    private Date dateOrdered;

    public Request(Car car)
    {
        this.car = car;
        this.dateOrdered = new Date();
    }

    public Car getCar()
    {
        return car;
    }

    public Date getDateOrdered()
    {
        return dateOrdered;
    }

    @Override
    public String toString()
    {
        return "Request{" +
                "car=" + car +
                ", dateOrdered=" + dateOrdered +
                '}';
    }
}
