import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainModerator extends JFrame
{
    Weather weather = new Weather();
    MainModerator()
    {
        super("Moderator");
        weather.GetWeather();
        AddComponentOnForm();
    }

    void AddComponentOnForm()
    {
        setLayout(null);
        JPanel jPanelMain = new JPanel();
        jPanelMain.setSize(660, 550);
        jPanelMain.setLayout(null);
        jPanelMain.setBackground(Color.ORANGE);
        JLabel jLabelClient = new JLabel("Clients");
        jLabelClient.setSize(100, 20);
        jLabelClient.setLocation(100, 10);
        jPanelMain.add(jLabelClient);

        JPanel jPanelClient = new JPanel();
        jPanelClient.setBackground(Color.CYAN);
        jPanelClient.setSize(200, 200);
        jPanelClient.setLocation(10, 30);
        jPanelMain.add(jPanelClient);
        jPanelClient.setLayout(null);
        jPanelClient.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton jButtonAllClient = new JButton("All clients");
        jButtonAllClient.setSize(100, 20);
        jButtonAllClient.setLocation(10, 20);
        jPanelClient.add(jButtonAllClient);

        JButton jButtonAddClient = new JButton("Add clients");
        jButtonAddClient.setSize(100, 20);
        jButtonAddClient.setLocation(10, 50);
        jPanelClient.add(jButtonAddClient);

        JLabel jLabelRoom = new JLabel("Rooms");
        jLabelRoom.setSize(100, 20);
        jLabelRoom.setLocation(310, 10);
        jPanelMain.add(jLabelRoom);

        JPanel jPanelRoom = new JPanel();
        jPanelRoom.setSize(200, 200);
        jPanelRoom.setLocation(220, 30);
        jPanelMain.add(jPanelRoom);

        JButton jButtonAllEmptyRoom = new JButton("All empty room");
        jButtonAllEmptyRoom.setSize(100, 20);
        jButtonAllEmptyRoom.setLocation(220, 70);
        jPanelRoom.add(jButtonAllEmptyRoom);
        jPanelRoom.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

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

        JLabel jLabelEvent = new JLabel("Events");
        jLabelEvent.setSize(100, 20);
        jLabelEvent.setLocation(510, 240);
        jPanelMain.add(jLabelEvent);

        JPanel jPanelEvent = new JPanel();
        jPanelEvent.setSize(200, 200);
        jPanelEvent.setLocation(430, 260);
        jPanelMain.add(jPanelEvent);

        JButton jButtonAddEvent = new JButton("Add event");
        jButtonAddEvent.setSize(100, 20);
        jButtonAddEvent.setLocation(430, 300);
        jPanelEvent.add(jButtonAddEvent);

        JButton jButtonDeleteOldEvent = new JButton("delete old");
        jButtonDeleteOldEvent.setSize(100, 20);
        jButtonDeleteOldEvent.setLocation(430, 240);
        jPanelEvent.add(jButtonDeleteOldEvent);

        JButton jButtonChangeEvent = new JButton("change old");
        jButtonChangeEvent.setSize(100, 20);
        jButtonChangeEvent.setLocation(430, 280);
        jPanelEvent.add(jButtonChangeEvent);

        jPanelEvent.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton jButtonExit = new JButton("Exit");
        jButtonExit.setSize(100, 20);
        jButtonExit.setLocation(340, 475);
        jPanelMain.add(jButtonExit);

        JButton jButtonRegistrationModerator = new JButton("Registration moderator");
        jButtonRegistrationModerator.setSize(200, 20);
        jButtonRegistrationModerator.setLocation(20, 475);
        jPanelMain.add(jButtonRegistrationModerator);

        setContentPane(jPanelMain);
        setSize(660, 550);


        jButtonRegistrationModerator.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowRegistration = new Registration();
                windowRegistration.setVisible(true);
            }
        });

        jButtonExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Program.jFrameAuthentication.setVisible(true);
                dispose();
            }
        });

        jButtonAddClient.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowRegistrationClients = new RegistrationClients();
                windowRegistrationClients.setVisible(true);
            }
        });

        jButtonAllClient.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowViewAllClients = new AllClientsView();
                windowViewAllClients.setVisible(true);
            }
        });

        jButtonAllEmptyRoom.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowViewAllEmptyRooms = new AllEmptyRooms();
                windowViewAllEmptyRooms.setVisible(true);
            }
        });

        jButtonAddEvent.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowAddEvent = new AddEvent();
                windowAddEvent.setVisible(true);
            }
        });

        jButtonDeleteOldEvent.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                AddEvent ae = new AddEvent();
                ae.DeleteOldEventFromBD();
            }
        });

        jButtonChangeEvent.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowChangeEvent = new ChangeEvent();
                windowChangeEvent.setVisible(true);
            }
        });

        jButtonAddOrder.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowAddOrder = new AddOrder();
                windowAddOrder.setVisible(true);
            }
        });

        jButtonChangeOrder.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame windowChangeOrder = new ChangeOrder();
                windowChangeOrder.setVisible(true);
            }
        });
    }
}
