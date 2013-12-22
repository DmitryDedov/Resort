import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClient extends JFrame
{
    Weather weather = new Weather();
    int id_client = 0;
    MainClient(int id_client_allien)
    {
        super("Client");
        id_client = id_client_allien;
        //weather.GetWeather();
        AddComponentOnForm();
    }

    void AddComponentOnForm()
    {
        setLayout(null);
        JPanel jPanelMain = new JPanel();
        jPanelMain.setSize(660, 550);
        jPanelMain.setLayout(null);
        jPanelMain.setBackground(Color.ORANGE);

        JPanel jPanelEvent = new JPanel();
        jPanelEvent.setSize(400, 200);
        jPanelEvent.setLocation(10, 30);

        FullEventForClient fullEventForClient = new FullEventForClient();
        fullEventForClient.AddComponentsOnForm(jPanelEvent);
        jPanelMain.add(jPanelEvent);

        jPanelEvent.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel jLabelWeather = new JLabel("Weather");
        jLabelWeather.setSize(100, 20);
        jLabelWeather.setLocation(510, 10);
        jPanelMain.add(jLabelWeather);

        JPanel jPanelWeather = new JPanel();
        jPanelWeather.setSize(200, 200);
        jPanelWeather.setLocation(430, 30);
        jPanelWeather.setLayout(null);
        jPanelMain.add(jPanelWeather);
        jPanelWeather.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel jLabelTemperature = new JLabel();
        jLabelTemperature.setSize(200, 20);
        jLabelTemperature.setLocation(10, 20);
        jLabelTemperature.setText("Temperature:   " + weather.getMin_temp() + " - " + weather.getMax_temp() + " degrees");
        jPanelWeather.add(jLabelTemperature);

        JLabel jLabelPressure = new JLabel();
        jLabelPressure.setSize(200, 20);
        jLabelPressure.setLocation(10, 40);
        jLabelPressure.setText("Pressure:   " + weather.getMin_pres() + " - " + weather.getMax_pres() + " mm merc");
        jPanelWeather.add(jLabelPressure);

        JLabel jLabelWind = new JLabel();
        jLabelWind.setSize(200, 20);
        jLabelWind.setLocation(10, 60);
        jLabelWind.setText("Wind:   " + weather.getMin_wind() + " - " + weather.getMax_wind() + " m/s");
        jPanelWeather.add(jLabelWind);

        JLabel jLabelHumidity = new JLabel();
        jLabelHumidity.setSize(200, 20);
        jLabelHumidity.setLocation(10, 80);
        jLabelHumidity.setText("Himidity:   " + weather.getMin_hmid() + " - " + weather.getMax_hmid() + " %");
        jPanelWeather.add(jLabelHumidity);

        JLabel jLabelService = new JLabel("Services");
        jLabelService.setSize(100, 20);
        jLabelService.setLocation(80, 240);
        jPanelMain.add(jLabelService);

        JPanel jPanelService = new JPanel();
        jPanelService.setSize(200, 200);
        jPanelService.setLocation(10, 260);
        jPanelMain.add(jPanelService);

        JButton jButtonAddService = new JButton("Add service");
        jButtonAddService.setSize(100, 20);
        jButtonAddService.setLocation(10, 280);
        jPanelService.add(jButtonAddService);

        jPanelService.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel jLabelOrder = new JLabel("Orders");
        jLabelOrder.setSize(100, 20);
        jLabelOrder.setLocation(310, 240);
        jPanelMain.add(jLabelOrder);

        JPanel jPanelOrder = new JPanel();
        jPanelOrder.setSize(200, 200);
        jPanelOrder.setLocation(220, 260);
        jPanelMain.add(jPanelOrder);

        JButton jButtonAddOrder = new JButton("Add order");
        jButtonAddOrder.setSize(100, 20);
        jButtonAddOrder.setLocation(310, 280);
        jPanelOrder.add(jButtonAddOrder);

        JButton jButtonChangeOrder = new JButton("change order");
        jButtonChangeOrder.setSize(100, 20);
        jButtonChangeOrder.setLocation(310, 280);
        jPanelOrder.add(jButtonChangeOrder);

        jPanelOrder.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton jButtonExit = new JButton("Exit");
        jButtonExit.setSize(100, 20);
        jButtonExit.setLocation(340, 475);
        jPanelMain.add(jButtonExit);

        setContentPane(jPanelMain);
        setSize(660, 550);

        jButtonExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Program.jFrameAuthentication.setVisible(true);
                dispose();
            }
        });

        jButtonAddOrder.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowAddOrder = new AddOrderForClient(id_client);
                windowAddOrder.setVisible(true);
            }
        });

        jButtonChangeOrder.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowChangeOrder = new ChangeOrderForClient(id_client);
                windowChangeOrder.setVisible(true);
            }
        });

        jButtonAddService.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowAddService = new AddServiceForClient(id_client);
                windowAddService.setVisible(true);
            }
        });
    }

}
