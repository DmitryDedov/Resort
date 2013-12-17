package data.models;

import org.codehaus.jackson.map.ObjectMapper;
import data.JSONMarshallObject;

import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 1:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class Service implements JSONMarshallObject {

    private int id;
    private String name;
    private Date date;
    private int maxNumber;
    private int currentNumber;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
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

    public static Service FromJSON(String jsonString) {
        Service service = new Service();
        ObjectMapper mapper = new ObjectMapper();
        try {
            service = mapper.readValue(jsonString, Service.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return service;
    }
}
