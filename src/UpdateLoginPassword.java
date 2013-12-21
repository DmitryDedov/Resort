import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateLoginPassword extends JFrame
{
    int id_client = 0;
    String login = null;
    UpdateLoginPassword(int id_client_allien, String login_allien)
    {
        super("Add order");
        id_client = id_client_allien;
        login = login_allien;
        AddComponentsOnForm();
    }

    void AddComponentsOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(300, 600);
        jpanel.setLayout(null);

        JLabel jLabelOldLogin = new JLabel("Old login:");
        jLabelOldLogin.setSize(100, 20);
        jLabelOldLogin.setLocation(20, 50);
        jpanel.add(jLabelOldLogin);

        final JTextField jTextFieldOldLogin = new JTextField(login);
        jTextFieldOldLogin.setSize(100, 20);
        jTextFieldOldLogin.setLocation(150, 50);
        jTextFieldOldLogin.setEditable(false);
        jpanel.add(jTextFieldOldLogin);

        JLabel jLabelNewlogin = new JLabel("New login :");
        jLabelNewlogin.setSize(100, 20);
        jLabelNewlogin.setLocation(20, 90);
        jpanel.add(jLabelNewlogin);

        final JTextField jTextFieldNewLogin = new JTextField(login);
        jTextFieldNewLogin.setSize(100, 20);
        jTextFieldNewLogin.setLocation(150, 90);
        jpanel.add(jTextFieldNewLogin);

        JLabel jLabelNewPassword = new JLabel("New password :");
        jLabelNewPassword.setSize(100, 20);
        jLabelNewPassword.setLocation(20, 130);
        jpanel.add(jLabelNewPassword);

        final JPasswordField jPasswordFieldNewPassword = new JPasswordField();
        jPasswordFieldNewPassword.setSize(100, 20);
        jPasswordFieldNewPassword.setLocation(150, 130);
        jpanel.add(jPasswordFieldNewPassword);

        JLabel jLabelRepeatNewPassword = new JLabel("New password :");
        jLabelRepeatNewPassword.setSize(100, 20);
        jLabelRepeatNewPassword.setLocation(20, 170);
        jpanel.add(jLabelRepeatNewPassword);

        final JPasswordField jPasswordFieldRepeatNewPassword = new JPasswordField();
        jPasswordFieldRepeatNewPassword.setSize(100, 20);
        jPasswordFieldRepeatNewPassword.setLocation(150, 170);
        jpanel.add(jPasswordFieldRepeatNewPassword);

        JButton jButtonUpdate = new JButton("Update");
        jButtonUpdate.setSize(100, 20);
        jButtonUpdate.setLocation(100, 210);
        jpanel.add(jButtonUpdate);

        jButtonUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (!(jPasswordFieldNewPassword.getText().equals("")) &&
                        (jPasswordFieldNewPassword.getText().equals(jPasswordFieldRepeatNewPassword.getText())) &&
                        !(jTextFieldNewLogin.getText().equals("")) && !(jPasswordFieldNewPassword.getText().equals("password")))
                {
                    UpdateLoginPassword(jTextFieldNewLogin.getText(), jPasswordFieldNewPassword.getText());

                    JFrame windowMainClient = new MainClient(id_client);
                    windowMainClient.setVisible(true);
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Input data correct!");
                }
            }
        });

        setContentPane(jpanel);
        setSize(300, 600);
    }

    void UpdateLoginPassword(String login, String password)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate("update clients set cl_login = '" + login + "', cl_password = '" + password +
                    "' where id_client = " + id_client);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
