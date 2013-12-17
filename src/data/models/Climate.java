package data.models;

import org.codehaus.jackson.map.ObjectMapper;
import data.JSONMarshallObject;

import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 12:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class Climate implements JSONMarshallObject {

    private int id;
    private String name;
    private Date date;
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public static Climate FromJSON(String jsonString) {
        Climate climate = new Climate();
        ObjectMapper mapper = new ObjectMapper();
        try {
            climate = mapper.readValue(jsonString, Climate.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return climate;
    }
}
