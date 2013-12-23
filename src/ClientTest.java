import data.models.Moderator;
import junit.framework.Assert;
import org.junit.Test;
import data.models.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 12:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientTest {

    @Test
    public void TestMarshall() {
        Client client = new Client();
        client.setId(0);
        client.setName("Ivan");
        client.setSurname("Ivanov");
        client.setMiddlename("Ivanovich");
        client.setBirthday(new Date(21-05-2001));
        client.setPassport(12345);
        //
        System.out.print(client.ToJSON());

    }
    @Test
    public void TestRegistration() throws Exception {
        sendGet("http://localhost:9998/api/clients/register?name=Vasya&surname=Pupkin&middlename=qwe&passport=777&pass=ewqqew");
    }

    @Test
    public void TestRegistrationModer() throws Exception {
        sendGet("http://localhost:9998/api/moderators/register?name=Vasya&surname=Pupkin&middlename=qwe&passport=777&pass=ewqqew&login=ad&birthday=2013.11.26");
    }

    @Test
    public void TestUnmarshall() throws Exception {
        String json = sendGet("http://localhost:9998/api/clients/by_id/1");
        Client client = Client.FromJSON(json);
        System.out.print(client);
    }

    @Test
    public void TestModer() throws Exception {
        String status = sendGet("http://localhost:9998/api/moderators/auth?login=dds&pass=ds");
        //Moderator moderator = Moderator.FromJSON(json);
        //moderator.getLogin();
        System.out.println("ff");
    }

    public String sendGet(String url) throws Exception {

        //String url = "http://localhost:9998/api/clients/by_id/1";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        //System.out.println(response.toString());
        return response.toString();
    }
    @Test
    public void TestAuth()
    {
        Auth auth = new Auth();
        Assert.assertTrue(auth.Authentication("dds", "dds") == "moderator");
    }


}