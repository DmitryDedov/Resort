import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddOrderForClient extends JFrame
{
    int id_order = 0;
    int id_client = 0;
    String[] arrayOrderType = new String[20];
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

        final JTextField jTextFieldOrderDate = new JTextField();
        jTextFieldOrderDate.setSize(100, 20);
        jTextFieldOrderDate.setLocation(150, 130);
        jpanel.add(jTextFieldOrderDate);

        JLabel jLabelOrderTime = new JLabel("Time:");
        jLabelOrderTime.setSize(100, 20);
        jLabelOrderTime.setLocation(20, 170);
        jpanel.add(jLabelOrderTime);

        final JTextField jTextFieldOrderTime = new JTextField();
        jTextFieldOrderTime.setSize(100, 20);
        jTextFieldOrderTime.setLocation(150, 170);
        jpanel.add(jTextFieldOrderTime);

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

        ActionListener actionListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JComboBox box = (JComboBox)e.getSource();
                String type = (String)box.getSelectedItem();
                SelectOrderFromTable(type);
                jComboBoxOrderName.removeAllItems();
                FillOrderName(jComboBoxOrderName);
            }
        };
        jComboBoxOrderType.addActionListener(actionListener);

        jButtonAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GetIdOrder(jComboBoxOrderType.getSelectedItem().toString(), jComboBoxOrderName.getSelectedItem().toString());
                AddOrderOnBD(jTextFieldOrderDate.getText(), jTextFieldOrderTime.getText(), jComboBoxOrderCount.getSelectedItem().toString());
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
            for (int i = 0; i < arrayOrderType.length; i++)
            {
                arrayOrderType[i] = null;
            }
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select ord_name from orders where type = '" + type + "'");
            int index = 0;
            while (resultSet.next())
            {
                arrayOrderType[index] = resultSet.getString("ord_name");
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
        while(arrayOrderType[index] != null)
        {
            jComboBox.addItem(arrayOrderType[index]);
            index++;
        }
    }

    void GetIdOrder(String type, String name)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select id_order from orders where type = '" + type + "' and ord_name = '" + name + "'");
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
