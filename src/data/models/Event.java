package data.models;

import org.codehaus.jackson.map.ObjectMapper;
import data.JSONMarshallObject;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 12:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class Event implements JSONMarshallObject {

    private int id;
    private String name;

    private String place;
    private Date date;
    private Time time;

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPlace() {
        return place;
    }


    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String ToJSON() {
        String json = new String();
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return json;
    }

    public static Event FromJSON(String jsonString) {
        Event event = new Event();
        ObjectMapper mapper = new ObjectMapper();
        try {
            event = mapper.readValue(jsonString, Event.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return event;
    }
}
