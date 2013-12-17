import data.SendGET;
import net.sourceforge.jdatepicker.JDatePicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import javax.swing.*;

class Registration extends JFrame
{
    Registration()
    {
        super("Registration");
        AddComponentOnForm();
    }

    void AddComponentOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(300, 400);
        jpanel.setLayout(null);

        JLabel jLabelRegistration = new JLabel("Registration");
        jLabelRegistration.setSize(150, 20);
        jLabelRegistration.setLocation(100, 10);
        jpanel.add(jLabelRegistration);

        JLabel jLabelSurname = new JLabel("*Input surname: ");
        jLabelSurname.setSize(150, 20);
        jLabelSurname.setLocation(30, 50);
        jpanel.add(jLabelSurname);

        final JTextField jTextFieldSurname = new JTextField();
        jTextFieldSurname.setSize(100, 20);
        jTextFieldSurname.setLocation(150, 50);
        jpanel.add(jTextFieldSurname);

        JLabel jLabelName = new JLabel("*Input name: ");
        jLabelName.setSize(150, 20);
        jLabelName.setLocation(30, 90);
        jpanel.add(jLabelName);

        final JTextField jTextFieldName = new JTextField();
        jTextFieldName.setSize(100, 20);
        jTextFieldName.setLocation(150, 90);
        jpanel.add(jTextFieldName);

        JLabel jLabelMiddlename = new JLabel("*Input middlename: ");
        jLabelMiddlename.setSize(150, 20);
        jLabelMiddlename.setLocation(30, 130);
        jpanel.add(jLabelMiddlename);

        final JTextField jTextFieldMiddlename = new JTextField();
        jTextFieldMiddlename.setSize(100, 20);
        jTextFieldMiddlename.setLocation(150, 130);
        jpanel.add(jTextFieldMiddlename);

        final JLabel jLabelBirthday = new JLabel("Input birthday: ");
        jLabelBirthday.setSize(150, 20);
        jLabelBirthday.setLocation(30, 170);
        jpanel.add(jLabelBirthday);

        final JTextField jTextFieldBirthday = new JTextField();
        jTextFieldBirthday.setSize(100, 20);
        jTextFieldBirthday.setLocation(150, 170);
        jpanel.add(jTextFieldBirthday);

        JLabel jLabelLogin = new JLabel("*Input login: ");
        jLabelLogin.setSize(150, 20);
        jLabelLogin.setLocation(30, 210);
        jpanel.add(jLabelLogin);

        final JTextField jTextFieldLogin = new JTextField();
        jTextFieldLogin.setSize(100, 20);
        jTextFieldLogin.setLocation(150, 210);
        jpanel.add(jTextFieldLogin);

        JLabel jLabelPassword = new JLabel("*Input password: ");
        jLabelPassword.setSize(150, 20);
        jLabelPassword.setLocation(30, 250);
        jpanel.add(jLabelPassword);

        final JPasswordField jPasswordFieldPassword = new JPasswordField();
        jPasswordFieldPassword.setSize(100, 20);
        jPasswordFieldPassword.setLocation(150, 250);
        jpanel.add(jPasswordFieldPassword);

        JButton jButtonRegistration = new JButton("Registration");
        jButtonRegistration.setSize(100, 20);
        jButtonRegistration.setLocation(100, 290);
        jpanel.add(jButtonRegistration);

        setContentPane(jpanel);
        setSize(300, 400);

        jButtonRegistration.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if ((jTextFieldSurname.getText().equals("")) || (jTextFieldName.getText().equals("")) || (jTextFieldMiddlename.getText().equals(""))
                        || (jTextFieldLogin.getText().equals("")) || (jPasswordFieldPassword.getText().equals("")))
                {
                    JOptionPane.showMessageDialog(null, "Input data correct!");
                }
                else
                {
                    if (RegistrationModerator(jTextFieldSurname.getText(), jTextFieldName.getText(), jTextFieldMiddlename.getText(), jTextFieldBirthday.getText(),
                            jTextFieldLogin.getText(), jPasswordFieldPassword.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "You have successfully registered!");
                        dispose();
                    }
                }
            }
        });
    }

    boolean RegistrationModerator(String surname, String name, String middlename, String birthday, String login, String password)
    {
        SendGET sendGET = new SendGET();
        try
        {
            sendGET.sendGet("http://localhost:9998/api/moderators/register?login=" + login + "&pass=" + password + "&surname=" + surname + "&name=" + name + "&middlename=" + middlename + "&birthday=" + birthday);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Failed to register!");
            return false;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to register!");
            return false;
        }
        return true;
    }
}
