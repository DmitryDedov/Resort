import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddOrder extends JFrame
{
    AddOrder()
    {
        super("Add order");
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

        JLabel jLabelEventPlace = new JLabel("Type:");
        jLabelEventPlace.setSize(100, 20);
        jLabelEventPlace.setLocation(20, 90);
        jpanel.add(jLabelEventPlace);

        final JComboBox jComboBoxOrderType = new JComboBox();
        jComboBoxOrderType.setSize(100, 20);
        jComboBoxOrderType.setLocation(150, 90);
        jComboBoxOrderType.addItem("Food");
        jComboBoxOrderType.addItem("Drink");
        jpanel.add(jComboBoxOrderType);

        JButton jButtonAdd = new JButton("Add");
        jButtonAdd.setSize(100, 20);
        jButtonAdd.setLocation(100, 250);
        jpanel.add(jButtonAdd);

        jButtonAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                AddOrderOnBD(jTextFieldEventName.getText(), jComboBoxOrderType.getSelectedItem().toString());
                dispose();
            }
        });

        setContentPane(jpanel);
        setSize(300, 600);
    }

    void AddOrderOnBD(String name, String type)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into orders (ord_name, ord_type) values ('" + name + "', '" + type + "')");
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
