import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddEvent extends JFrame
{
    AddEvent()
    {
        super("Add evetn");
        AddComponentsOnForm();
    }

    void AddComponentsOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(300, 600);
        jpanel.setLayout(null);

        JLabel jLabelEventName = new JLabel("Name:");
        jLabelEventName.setSize(100, 20);
        jLabelEventName.setLocation(20, 50);
        jpanel.add(jLabelEventName);

        final JTextField jTextFieldEventName = new JTextField();
        jTextFieldEventName.setSize(100, 20);
        jTextFieldEventName.setLocation(150, 50);
        jpanel.add(jTextFieldEventName);

        JLabel jLabelEventPlace = new JLabel("Place:");
        jLabelEventPlace.setSize(100, 20);
        jLabelEventPlace.setLocation(20, 90);
        jpanel.add(jLabelEventPlace);

        final JTextField jTextFieldEventPlace = new JTextField();
        jTextFieldEventPlace.setSize(100, 20);
        jTextFieldEventPlace.setLocation(150, 90);
        jpanel.add(jTextFieldEventPlace);

        JLabel jLabelEventDate = new JLabel("Date:");
        jLabelEventDate.setSize(100, 20);
        jLabelEventDate.setLocation(20, 130);
        jpanel.add(jLabelEventDate);

        final JTextField jTextFieldEventDate = new JTextField();
        jTextFieldEventDate.setSize(100, 20);
        jTextFieldEventDate.setLocation(150, 130);
        jpanel.add(jTextFieldEventDate);

        JLabel jLabelEventTime = new JLabel("Time:");
        jLabelEventTime.setSize(100, 20);
        jLabelEventTime.setLocation(20, 170);
        jpanel.add(jLabelEventTime);

        final JTextField jTextFieldEventTime = new JTextField();
        jTextFieldEventTime.setSize(100, 20);
        jTextFieldEventTime.setLocation(150, 170);
        jpanel.add(jTextFieldEventTime);

        JButton jButtonAdd = new JButton("Add");
        jButtonAdd.setSize(100, 20);
        jButtonAdd.setLocation(100, 250);
        jpanel.add(jButtonAdd);

        jButtonAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                AddEventOnBD(jTextFieldEventName.getText(), jTextFieldEventPlace.getText(), jTextFieldEventDate.getText(), jTextFieldEventTime.getText());
                dispose();
            }
        });

        setContentPane(jpanel);
        setSize(300, 600);
    }

    void AddEventOnBD(String name, String place, String date, String time)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into events (ev_name, ev_place, ev_date, ev_time) values ('" + name + "', '" + place + "', '" + date + "', '" + time + "')");
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void DeleteOldEventFromBD()
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from events where id_event < 10000 and ev_date < (SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d'));");
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
