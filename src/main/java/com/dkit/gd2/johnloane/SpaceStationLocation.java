package com.dkit.gd2.johnloane;

import java.util.Map;

public class SpaceStationLocation
{
    private String message;
    private String timestamp;
    private Map<String, Object> iss_position;

    public SpaceStationLocation(String message, String timestamp, Map<String, Object> iss_position)
    {
        this.message = message;
        this.timestamp = timestamp;
        this.iss_position = iss_position;
    }

    public SpaceStationLocation()
    {

    }

    public String getMessage()
    {
        return message;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public Map<String, Object> getIss_position()
    {
        return iss_position;
    }

    @Override
    public String toString()
    {
        return "SpaceStationLocation{" +
                "message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", iss_position=" + iss_position +
                '}';
    }
}
