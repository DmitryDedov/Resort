import data.SendGET;
import data.models.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Program
{
    static int id_client = 0;
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

        final JTextField jTextFieldLogin = new JTextField("q");
        jTextFieldLogin.setSize(100, 20);
        jTextFieldLogin.setLocation(150, 60);
        jpanel.add(jTextFieldLogin);

        JLabel jLabelPassword = new JLabel("Input password: ");
        jLabelPassword.setSize(100, 20);
        jLabelPassword.setLocation(30, 100);
        jpanel.add(jLabelPassword);

        final JPasswordField jPasswordFieldPassword = new JPasswordField("qqq");
        jPasswordFieldPassword.setSize(100, 20);
        jPasswordFieldPassword.setLocation(150, 100);
        jpanel.add(jPasswordFieldPassword);

        JButton jButtonEnter = new JButton("Enter");
        jButtonEnter.setSize(100, 20);
        jButtonEnter.setLocation(30, 140);
        jpanel.add(jButtonEnter);

        jFrameAuthentication.setContentPane(jpanel);

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
                    String typeUser = Authentication(jTextFieldLogin.getText(), jPasswordFieldPassword.getText());
                    if (typeUser.equals("moderator"))
                    {
                        jFrameAuthentication.setVisible(false);
                        JFrame windowMainModerator = new MainModerator();
                        windowMainModerator.setVisible(true);
                    }
                    if (typeUser.equals("client"))
                    {
                        jFrameAuthentication.setVisible(false);

                        if (jPasswordFieldPassword.getText().equals("newpass"))
                        {
                            JFrame windowUpdateLoginPassword = new UpdateLoginPassword(id_client, jTextFieldLogin.getText());
                            windowUpdateLoginPassword.setVisible(true);
                        }
                        else
                        {
                            JFrame windowMainClient = new MainClient(id_client);
                            windowMainClient.setVisible(true);
                        }
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
                String json = sendGET.sendGet("http://localhost:9998/api/clients/byloginpass?login=" + login + "&pass=" + password);
                Client client = Client.FromJSON(json);
                id_client = client.getId();

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
