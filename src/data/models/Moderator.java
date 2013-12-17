package data.models;

import org.codehaus.jackson.map.ObjectMapper;
import data.JSONMarshallObject;

import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 1:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Moderator implements JSONMarshallObject {

    private int id;
    private String name;
    private String surname;
    private String middlename;
    private String login;
    private String password;
    private Date birthday;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public static Moderator FromJSON(String jsonString) {
        Moderator moderator = new Moderator();
        ObjectMapper mapper = new ObjectMapper();
        try {
            moderator = mapper.readValue(jsonString, Moderator.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return moderator;
    }
}
