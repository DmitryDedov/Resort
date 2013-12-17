package data.models;

import org.codehaus.jackson.map.ObjectMapper;
import data.JSONMarshallObject;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 12:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientOrder implements JSONMarshallObject {

    private int clientId;
    private int orderId;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public static ClientOrder FromJSON(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        ClientOrder co = new ClientOrder();
        try {
            co = mapper.readValue(jsonString, ClientOrder.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return co;
    }
}
