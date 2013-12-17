import java.net.*;
import java.util.*;
import java.text.*;
import org.jdom.*;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;
import java.io.IOException;

public class Weather
{
    private String max_temp;

    public String getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp;
    }

    private String min_temp;

    public String getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp;
    }

    private String max_pres;

    public String getMax_pres() {
        return max_pres;
    }

    public void setMax_pres(String max_pres) {
        this.max_pres = max_pres;
    }

    private String min_pres;

    public String getMin_pres() {
        return min_pres;
    }

    public void setMin_pres(String min_pres) {
        this.min_pres = min_pres;
    }

    private String max_wind;

    public String getMax_wind() {
        return max_wind;
    }

    public void setMax_wind(String max_wind) {
        this.max_wind = max_wind;
    }

    private String min_wind;

    public String getMin_wind() {
        return min_wind;
    }

    public void setMin_wind(String min_wind) {
        this.min_wind = min_wind;
    }

    private String max_hmid;

    public String getMax_hmid() {
        return max_hmid;
    }

    public void setMax_hmid(String max_hmid) {
        this.max_hmid = max_hmid;
    }

    private String min_hmid;

    public String getMin_hmid() {
        return min_hmid;
    }

    public void setMin_hmid(String min_hmid) {
        this.min_hmid = min_hmid;
    }

    void GetWeather()
    {
        String usrCity = "Воронеж";
        SAXBuilder builder = new SAXBuilder();
        Document xmlDoc;
        try
        {
            xmlDoc = builder.build(new URL("http://xml.weather.co.ua/1.2/city/?country=643"));
            List elements = xmlDoc.getRootElement().getContent(new ElementFilter("city"));
            Iterator iterator = elements.iterator();
            while(iterator.hasNext())
            {
                Element city = (Element)iterator.next();
                String id = city.getAttributeValue("id");
                String name = city.getChildText("name");
                if(name.toUpperCase().indexOf(usrCity.toUpperCase()) == 0)
                {
                    //System.out.println("Найден город: " + name + "\nID: " + id);
                    //System.out.println("Идем за погодой на http://xml.weather.co.ua/1.2/forecast/" + id);
                    xmlDoc = builder.build(new URL("http://xml.weather.co.ua/1.2/forecast/" + id));
                    elements = xmlDoc.getRootElement().getContent(new ElementFilter("forecast"));
                    iterator = elements.iterator();
                    while(iterator.hasNext())
                    {
                        Element forecast = (Element)iterator.next();
                        List mixedCo = forecast.getChildren("day");
                        Iterator itr = mixedCo.iterator();
//                        while (itr.hasNext())
//                        {
                        Element day = (Element)itr.next();
                        String StrDate = day.getAttributeValue("date");
                        String hour = day.getAttributeValue("hour");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = formatter.parse(StrDate);
                        //SimpleDateFormat formatter2 = new SimpleDateFormat("E dd.MM.yyyy");
                        //String dt2 = formatter2.format(date);
                        //System.out.println("Прогноз на: " + dt2 + ", " + hour + " часов");
                        Element temperature = day.getChild("t");
                        max_temp = temperature.getChildText("max");
                        min_temp = temperature.getChildText("min");
                        //System.out.println("Температура: \nМаксимум: " + max_temp + "\nМинимум: " + min_temp);
                        Element pressure = day.getChild("p");
                        max_pres = pressure.getChildText("max");
                        min_pres = pressure.getChildText("min");
                        //System.out.println("Давление: \nМаксимум: " + max_pres + "\nМинимум: " + min_pres);
                        Element wind = day.getChild("wind");
                        max_wind = wind.getChildText("max");
                        min_wind = wind.getChildText("min");
                        //System.out.println("Ветер: \nМаксимум: " + max_wind + "\nМинимум: " + min_wind);
                        Element hmid = day.getChild("hmid");
                        max_hmid = hmid.getChildText("max");
                        min_hmid = hmid.getChildText("min");
                        //System.out.println("Влажность: \nМаксимум: " + max_hmid + "\nМинимум: " + min_hmid);
//                        }
                    }
                    //System.out.println("Сходили!");
                }
            }
        }
        catch (MalformedURLException e)
        {
        }
        catch (IOException e)
        {
        }
        catch (JDOMException e)
        {
        }
        catch (ParseException e)
        {
        }
    }
}
