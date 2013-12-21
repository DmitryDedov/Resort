import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddService extends JFrame
{
    AddService()
    {
        super("Add service");
        AddComponentsOnForm();
    }

    void AddComponentsOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(300, 600);
        jpanel.setLayout(null);

        JLabel jLabelServiceName = new JLabel("Name:");
        jLabelServiceName.setSize(100, 20);
        jLabelServiceName.setLocation(20, 50);
        jpanel.add(jLabelServiceName);

        final JTextField jTextFieldServiceName = new JTextField();
        jTextFieldServiceName.setSize(100, 20);
        jTextFieldServiceName.setLocation(150, 50);
        jpanel.add(jTextFieldServiceName);

        JLabel jLabelServiceMaxNumber = new JLabel("Max number:");
        jLabelServiceMaxNumber.setSize(100, 20);
        jLabelServiceMaxNumber.setLocation(20, 90);
        jpanel.add(jLabelServiceMaxNumber);

        final JTextField jTextFieldServiceMaxNumber = new JTextField();
        jTextFieldServiceMaxNumber.setSize(100, 20);
        jTextFieldServiceMaxNumber.setLocation(150, 90);
        jpanel.add(jTextFieldServiceMaxNumber);

        JButton jButtonAdd = new JButton("Add");
        jButtonAdd.setSize(100, 20);
        jButtonAdd.setLocation(100, 250);
        jpanel.add(jButtonAdd);

        jButtonAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                AddOrderOnBD(jTextFieldServiceName.getText(), jTextFieldServiceMaxNumber.getText());
                dispose();
            }
        });

        setContentPane(jpanel);
        setSize(300, 600);
    }

    void AddOrderOnBD(String name, String max_number)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into services (serv_name, serv_max_number, serv_curr_number) values ('" + name + "'," + max_number + "," + 0 + ")");
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
