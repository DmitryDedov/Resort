import data.SendGET;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Program
{
    static JFrame jFrameAuthentication;
    public static void main(String[] args)
    {
        AddComponentOnForm();
    }

    public static void AddComponentOnForm()
    {
        jFrameAuthentication = new JFrame("Resort");
        jFrameAuthentication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameAuthentication.setSize(300, 300);
        jFrameAuthentication.setVisible(true);

        JPanel jpanel = new JPanel();
        jpanel.setSize(300, 300);
        jpanel.setLayout(null);

        JLabel jLabelWelcome = new JLabel("Welcome to Resort!");
        jLabelWelcome.setSize(150, 20);
        jLabelWelcome.setLocation(90, 10);
        jpanel.add(jLabelWelcome);

        JLabel jLabelLogin = new JLabel("Input login: ");
        jLabelLogin.setSize(100, 20);
        jLabelLogin.setLocation(30, 60);
        jpanel.add(jLabelLogin);

        final JTextField jTextFieldLogin = new JTextField("12");
        jTextFieldLogin.setSize(100, 20);
        jTextFieldLogin.setLocation(150, 60);
        jpanel.add(jTextFieldLogin);

        JLabel jLabelPassword = new JLabel("Input password: ");
        jLabelPassword.setSize(100, 20);
        jLabelPassword.setLocation(30, 100);
        jpanel.add(jLabelPassword);

        final JPasswordField jPasswordFieldPassword = new JPasswordField("newpass");
        jPasswordFieldPassword.setSize(100, 20);
        jPasswordFieldPassword.setLocation(150, 100);
        jpanel.add(jPasswordFieldPassword);

        JButton jButtonEnter = new JButton("Enter");
        jButtonEnter.setSize(100, 20);
        jButtonEnter.setLocation(30, 140);
        jpanel.add(jButtonEnter);

//        JButton jButtonRegistration = new JButton("Registration");
//        jButtonRegistration.setSize(100, 20);
//        jButtonRegistration.setLocation(150, 140);
//        jpanel.add(jButtonRegistration);

        jFrameAuthentication.setContentPane(jpanel);

//        jButtonRegistration.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent e)
//            {
//
//            }
//        });

        jButtonEnter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if ((jTextFieldLogin.getText().equals("")) || (jPasswordFieldPassword.getText().equals("")))
                {
                    JOptionPane.showMessageDialog(null, "Input data correct!");
                }
                else
                {
                    if (Authentication(jTextFieldLogin.getText(), jPasswordFieldPassword.getText()).equals("moderator"))
                    {
                        jFrameAuthentication.setVisible(false);
                        JFrame windowMainModerator = new MainModerator();
                        windowMainModerator.setVisible(true);
                    }
                    if (Authentication(jTextFieldLogin.getText(), jPasswordFieldPassword.getText()).equals("client"))
                    {
                        jFrameAuthentication.setVisible(false);
                        JFrame windowMainClient = new MainClient();
                        windowMainClient.setVisible(true);
                    }
                }
            }
        });

    }
    static String Authentication(String login, String password)
    {
        SendGET sendGET = new SendGET();
        try
        {
            String status = sendGET.sendGet("http://localhost:9998/api/moderators/auth?login=" + login + "&pass=" + password);
            if (status.equals("YES"))
            {
                return "moderator";
            }
            status = sendGET.sendGet("http://localhost:9998/api/clients/auth?login=" + login + "&pass=" + password);
            if (status.equals("YES"))
            {
                return "client";
            }
            else
                JOptionPane.showMessageDialog(null, "Not exists!");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error in authentication!");
            return "nobody";
        }
        catch (Exception e)
        {

        }
        return "nobody";
    }
}
