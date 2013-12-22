import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class AddServiceForClient extends JFrame
{
    int id_service = 0;
    int id_client = 0;
    String[] arrayServiceName = new String[20];
    AddServiceForClient(int id_client_allien)
    {
        super("Add order");
        id_client = id_client_allien;
        AddComponentsOnForm();
    }

    void AddComponentsOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(300, 600);
        jpanel.setLayout(null);

        JLabel jLabelNameService = new JLabel("Name:");
        jLabelNameService.setSize(100, 20);
        jLabelNameService.setLocation(20, 50);
        jpanel.add(jLabelNameService);

        final JComboBox jComboBoxNameService = new JComboBox();
        jComboBoxNameService.setSize(100, 20);
        jComboBoxNameService.setLocation(150, 50);
        jpanel.add(jComboBoxNameService);

        JLabel jLabelDate = new JLabel("Date:");
        jLabelDate.setSize(100, 20);
        jLabelDate.setLocation(20, 90);
        jpanel.add(jLabelDate);

        final JDateChooser jDateChooserDate = new JDateChooser();
        jDateChooserDate.setSize(100, 20);
        jDateChooserDate.setLocation(150, 90);
        jDateChooserDate.setDateFormatString("yyyy-MM-dd");
        jDateChooserDate.setDate(new Date());
        jpanel.add(jDateChooserDate);

        JLabel jLabelOrderTime = new JLabel("Time:");
        jLabelOrderTime.setSize(100, 20);
        jLabelOrderTime.setLocation(20, 130);
        jpanel.add(jLabelOrderTime);

        java.util.Date date = new java.util.Date();
        final JSpinner jSpinner = new JSpinner();
        SpinnerDateModel spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner.setModel(spinnerDateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(jSpinner, "HH:mm");
        jSpinner.setEditor(dateEditor);
        jSpinner.setLocation(150, 130);
        jSpinner.setSize(100, 20);
        jpanel.add(jSpinner);

        JButton jButtonAdd = new JButton("Add");
        jButtonAdd.setSize(100, 20);
        jButtonAdd.setLocation(20, 250);
        jpanel.add(jButtonAdd);

        SelectServiceFromTable();
        FillOrderName(jComboBoxNameService);

        jButtonAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
                String time = simpleTimeFormat.format(jSpinner.getValue());

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat1.format(jDateChooserDate.getDate());

                GetIdService(jComboBoxNameService.getSelectedItem().toString());
                AddOrderOnBD(date, time);
                dispose();
            }
        });

        setContentPane(jpanel);
        setSize(300, 600);
    }

    void SelectServiceFromTable()
    {
        try
        {
            for (int i = 0; i < arrayServiceName.length; i++)
            {
                arrayServiceName[i] = null;
            }
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select serv_name from services where serv_max_number > serv_curr_number");
            int index = 0;
            while (resultSet.next())
            {
                arrayServiceName[index] = resultSet.getString("serv_name");
                index++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void FillOrderName(JComboBox jComboBox)
    {
        int index = 0;
        while(arrayServiceName[index] != null)
        {
            jComboBox.addItem(arrayServiceName[index]);
            index++;
        }
    }

    void GetIdService(String name)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select id_serv from services where serv_name = '" + name + "'");
            if (resultSet.next())
            {
                id_service = resultSet.getInt("id_serv");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void AddOrderOnBD(String date, String time)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into client_service (id_client, id_serv, date, time, status) values (" + id_client + ", " +
                    id_service + ",'" + date + "', '" + time + "', 0)");
            statement.executeUpdate("update services set serv_curr_number = serv_curr_number + 1 where id_serv = " + id_service);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
