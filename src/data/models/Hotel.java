package data.models;

import org.codehaus.jackson.map.ObjectMapper;
import data.JSONMarshallObject;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 1:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Hotel implements JSONMarshallObject {

    private int id;
    private String name;
    private int room;
    private int floor;
    private int numberOfPlaces;

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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
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

    public static Hotel FromJSON(String jsonString) {
        Hotel hotel = new Hotel();
        ObjectMapper mapper = new ObjectMapper();
        try {
            hotel = mapper.readValue(jsonString, Hotel.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return hotel;
    }
}
