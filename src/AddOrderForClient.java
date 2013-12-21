import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddOrderForClient extends JFrame
{
    String dateOrder = null;
    int id_order = 0;
    int id_client = 0;
    String[] arrayOrderName = new String[20];
    AddOrderForClient(int id_client_allien)
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

        JLabel jLabelOrderType = new JLabel("Type:");
        jLabelOrderType.setSize(100, 20);
        jLabelOrderType.setLocation(20, 50);
        jpanel.add(jLabelOrderType);

        final JComboBox jComboBoxOrderType = new JComboBox();
        jComboBoxOrderType.setSize(100, 20);
        jComboBoxOrderType.setLocation(150, 50);
        jComboBoxOrderType.addItem("Food");
        jComboBoxOrderType.addItem("Drink");
        jpanel.add(jComboBoxOrderType);

        JLabel jLabelOrderName = new JLabel("Name:");
        jLabelOrderName.setSize(100, 20);
        jLabelOrderName.setLocation(20, 90);
        jpanel.add(jLabelOrderName);

        final JComboBox jComboBoxOrderName = new JComboBox();
        jComboBoxOrderName.setSize(100, 20);
        jComboBoxOrderName.setLocation(150, 90);
        jpanel.add(jComboBoxOrderName);

        JLabel jLabelOrderDate = new JLabel("Date:");
        jLabelOrderDate.setSize(100, 20);
        jLabelOrderDate.setLocation(20, 130);
        jpanel.add(jLabelOrderDate);

        final JComboBox jComboBoxDate = new JComboBox();
        jComboBoxDate.setSize(100, 20);
        jComboBoxDate.setLocation(150, 130);
        jComboBoxDate.addItem("today");
        jComboBoxDate.addItem("tomorrow");
        jpanel.add(jComboBoxDate);

        JLabel jLabelOrderTime = new JLabel("Time:");
        jLabelOrderTime.setSize(100, 20);
        jLabelOrderTime.setLocation(20, 170);
        jpanel.add(jLabelOrderTime);

        java.util.Date date = new java.util.Date();
        final JSpinner jSpinner = new JSpinner();
        SpinnerDateModel spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner.setModel(spinnerDateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(jSpinner, "HH:mm");
        jSpinner.setEditor(dateEditor);
        jSpinner.setLocation(150, 170);
        jSpinner.setSize(100, 20);
        jpanel.add(jSpinner);

        JLabel jLabelOrderCount = new JLabel("Count:");
        jLabelOrderCount.setSize(100, 20);
        jLabelOrderCount.setLocation(20, 210);
        jpanel.add(jLabelOrderCount);

        final JComboBox jComboBoxOrderCount = new JComboBox();
        jComboBoxOrderCount.setSize(100, 20);
        jComboBoxOrderCount.setLocation(150, 210);
        jComboBoxOrderCount.addItem(1);
        jComboBoxOrderCount.addItem(2);
        jComboBoxOrderCount.addItem(3);
        jpanel.add(jComboBoxOrderCount);

        JButton jButtonAdd = new JButton("Add");
        jButtonAdd.setSize(100, 20);
        jButtonAdd.setLocation(20, 250);
        jpanel.add(jButtonAdd);

        jComboBoxOrderType.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JComboBox box = (JComboBox)e.getSource();
                String type = (String)box.getSelectedItem();
                SelectOrderFromTable(type);
                jComboBoxOrderName.removeAllItems();
                FillOrderName(jComboBoxOrderName);
            }
        });

        jComboBoxDate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JComboBox box = (JComboBox)e.getSource();
                String date = (String)box.getSelectedItem();
                if (date.equals("tomorrow"))
                {
                    java.util.Date time = null;
                    try
                    {
                        time = new SimpleDateFormat("HH:mm").parse("00:01");
                    }
                    catch (ParseException e1)
                    {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    SpinnerDateModel spinnerDateModel = new SpinnerDateModel(time, null, null, Calendar.HOUR_OF_DAY);
                    jSpinner.setModel(spinnerDateModel);
                    JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(jSpinner, "HH:mm");
                    jSpinner.setEditor(dateEditor);

                    SimpleDateFormat FormattedDATE  = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, 1);
                    dateOrder = (String)(FormattedDATE.format(c.getTime()));
                }
                else
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateOrder = simpleDateFormat.format(new java.util.Date());
                }
            }
        });

        jButtonAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
                String time = simpleTimeFormat.format(jSpinner.getValue());

                GetIdOrder(jComboBoxOrderType.getSelectedItem().toString(), jComboBoxOrderName.getSelectedItem().toString());
                AddOrderOnBD(dateOrder, time, jComboBoxOrderCount.getSelectedItem().toString());
                dispose();
            }
        });

        setContentPane(jpanel);
        setSize(300, 600);
    }

    void SelectOrderFromTable(String type)
    {
        try
        {
            for (int i = 0; i < arrayOrderName.length; i++)
            {
                arrayOrderName[i] = null;
            }
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select ord_name from orders where ord_type = '" + type + "'");
            int index = 0;
            while (resultSet.next())
            {
                arrayOrderName[index] = resultSet.getString("ord_name");
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
        while(arrayOrderName[index] != null)
        {
            jComboBox.addItem(arrayOrderName[index]);
            index++;
        }
    }

    void GetIdOrder(String type, String name)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select id_order from orders where ord_type = '" + type + "' and ord_name = '" + name + "'");
            if (resultSet.next())
            {
                id_order = resultSet.getInt("id_order");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void AddOrderOnBD(String date, String time, String count)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into client_order (id_order, id_client, date, time, count, status) values (" + id_order + ", " +
                    id_client + ",'" + date + "', '" + time + "', '" + count + "', 0)");
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
