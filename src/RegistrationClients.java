import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class RegistrationClients extends JFrame
{
    String[] arrayNumberRoom = new String[100];

    RegistrationClients()
    {
        super("Registration");

        AddComponentOnForm();
    }

    void AddComponentOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(300, 700);
        jpanel.setLayout(null);

        JLabel jLabelRegistration = new JLabel("Registration client:");
        jLabelRegistration.setSize(150, 20);
        jLabelRegistration.setLocation(100, 10);
        jpanel.add(jLabelRegistration);

        JLabel jLabelSurname = new JLabel("Input surname: ");
        jLabelSurname.setSize(150, 20);
        jLabelSurname.setLocation(30, 50);
        jpanel.add(jLabelSurname);

        final JTextField jTextFieldSurname = new JTextField();
        jTextFieldSurname.setSize(100, 20);
        jTextFieldSurname.setLocation(150, 50);
        jpanel.add(jTextFieldSurname);

        JLabel jLabelName = new JLabel("Input name: ");
        jLabelName.setSize(150, 20);
        jLabelName.setLocation(30, 90);
        jpanel.add(jLabelName);

        final JTextField jTextFieldName = new JTextField();
        jTextFieldName.setSize(100, 20);
        jTextFieldName.setLocation(150, 90);
        jpanel.add(jTextFieldName);

        JLabel jLabelMiddlename = new JLabel("Input middlename: ");
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

        final JDateChooser jDateChooserBirthday = new JDateChooser();
        jDateChooserBirthday.setSize(100, 20);
        jDateChooserBirthday.setLocation(150, 170);
        jDateChooserBirthday.setDateFormatString("yyyy-MM-dd");
        jpanel.add(jDateChooserBirthday);

        JLabel jLabelPassport = new JLabel("Input passport: ");
        jLabelPassport.setSize(150, 20);
        jLabelPassport.setLocation(30, 210);
        jpanel.add(jLabelPassport);

        final JTextField jTextFieldPassport = new JTextField();
        jTextFieldPassport.setSize(100, 20);
        jTextFieldPassport.setLocation(150, 210);
        jpanel.add(jTextFieldPassport);

        JLabel jLabelEmail = new JLabel("Input E-mail: ");
        jLabelEmail.setSize(150, 20);
        jLabelEmail.setLocation(30, 250);
        jpanel.add(jLabelEmail);

        final JTextField jTextFieldEmail = new JTextField();
        jTextFieldEmail.setSize(100, 20);
        jTextFieldEmail.setLocation(150, 250);
        jpanel.add(jTextFieldEmail);

        JLabel jLabelNameHotel = new JLabel("Input name hotel: ");
        jLabelNameHotel.setSize(150, 20);
        jLabelNameHotel.setLocation(30, 290);
        jpanel.add(jLabelNameHotel);

        final JComboBox jComboBoxNameHotel = new JComboBox();
        jComboBoxNameHotel.setSize(100, 20);
        jComboBoxNameHotel.setLocation(150, 290);
        jComboBoxNameHotel.addItem("Pektoral");
        jpanel.add(jComboBoxNameHotel);

        JLabel jLabelCountPlace = new JLabel("Input count place: ");
        jLabelCountPlace.setSize(150, 20);
        jLabelCountPlace.setLocation(30, 330);
        jpanel.add(jLabelCountPlace);

        final JComboBox jComboBoxCountPlace = new JComboBox();
        jComboBoxCountPlace.setSize(100, 20);
        jComboBoxCountPlace.setLocation(150, 330);
        jComboBoxCountPlace.addItem(1);
        jComboBoxCountPlace.addItem(2);
        jComboBoxCountPlace.addItem(3);
        jComboBoxCountPlace.addItem(4);
        jComboBoxCountPlace.addItem(5);
        jpanel.add(jComboBoxCountPlace);

        JLabel jLabelNumberRoom = new JLabel("Input number room: ");
        jLabelNumberRoom.setSize(150, 20);
        jLabelNumberRoom.setLocation(30, 370);
        jpanel.add(jLabelNumberRoom);

        final JComboBox jComboBoxNumberRoom = new JComboBox();
        jComboBoxNumberRoom.setSize(100, 20);
        jComboBoxNumberRoom.setLocation(150, 370);
        jComboBoxNumberRoom.setEditable(true);
        jpanel.add(jComboBoxNumberRoom);

        JLabel jLabelDateIn = new JLabel("Input date in: ");
        jLabelDateIn.setSize(150, 20);
        jLabelDateIn.setLocation(30, 410);
        jpanel.add(jLabelDateIn);

        final JDateChooser jDateChooserDateIn = new JDateChooser();
        jDateChooserDateIn.setSize(100, 20);
        jDateChooserDateIn.setLocation(150, 410);
        jDateChooserDateIn.setDateFormatString("yyyy-MM-dd");
        jDateChooserDateIn.setDate(new Date());
        jpanel.add(jDateChooserDateIn);

        JLabel jLabelDateOut = new JLabel("Input date out: ");
        jLabelDateOut.setSize(150, 20);
        jLabelDateOut.setLocation(30, 450);
        jpanel.add(jLabelDateOut);

        final JDateChooser jDateChooserDateOut = new JDateChooser();
        jDateChooserDateOut.setSize(100, 20);
        jDateChooserDateOut.setLocation(150, 450);
        jDateChooserDateOut.setDateFormatString("yyyy-MM-dd");
        jpanel.add(jDateChooserDateOut);

        JButton jButtonRegistration = new JButton("Registration");
        jButtonRegistration.setSize(100, 20);
        jButtonRegistration.setLocation(100, 490);
        jpanel.add(jButtonRegistration);

        setContentPane(jpanel);
        setSize(300, 700);

        jButtonRegistration.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if ((jTextFieldSurname.getText().equals("")) || (jTextFieldName.getText().equals("")) || (jTextFieldMiddlename.getText().equals(""))
                        || (jDateChooserBirthday.getDate().equals("")) || (jTextFieldPassport.getText().equals("")) ||
                        (jTextFieldEmail.getText().equals("")) || (jComboBoxNameHotel.getSelectedItem().equals("")) ||
                        (jComboBoxNumberRoom.getSelectedItem().equals("")) || (jDateChooserDateIn.getDate().equals("")) ||
                        (jDateChooserDateOut.getDate().equals("")))
                {
                    JOptionPane.showMessageDialog(null, "Input all values!");
                }
                else
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateBirthday = simpleDateFormat.format(jDateChooserBirthday.getDate());
                    String dateIn = simpleDateFormat.format(jDateChooserDateIn.getDate());
                    String dateOut = simpleDateFormat.format(jDateChooserDateOut.getDate());

                    if (RegistrationClient(jTextFieldSurname.getText(), jTextFieldName.getText(), jTextFieldMiddlename.getText(), dateBirthday,
                            jTextFieldPassport.getText(), jTextFieldEmail.getText(), jComboBoxNameHotel.getSelectedItem().toString(),
                            jComboBoxNumberRoom.getSelectedItem().toString(), dateIn, dateOut))
                    {
                        JOptionPane.showMessageDialog(null, "You have successfully registered!");
                        dispose();
                    }
                }
            }
        });

        jComboBoxCountPlace.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JComboBox box = (JComboBox)e.getSource();
                int countPlace = Integer.parseInt(box.getSelectedItem().toString());
                String nameHotel = jComboBoxNameHotel.getSelectedItem().toString();
                SelectNumberRoomFromTable(nameHotel, countPlace);
                jComboBoxNumberRoom.removeAllItems();
                FillNumberRoom(jComboBoxNumberRoom);
            }
        });

        jComboBoxNumberRoom.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                jDateChooserDateOut.setEnabled(true);
                jDateChooserDateOut.setDate(null);
                JComboBox box = (JComboBox)e.getSource();
                int numberRoom = Integer.parseInt(box.getSelectedItem().toString());
                String nameHotel = jComboBoxNameHotel.getSelectedItem().toString();

                String dateIn = CheckDateInOut(nameHotel, numberRoom);

                if (dateIn == null)
                {

                }
                else
                {
                    java.util.Date date = null;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    try
                    {
                        date = format.parse(dateIn);
                    }
                    catch (ParseException e1)
                    {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    jDateChooserDateOut.setDate(date);
                    jDateChooserDateOut.setEnabled(false);
                }
            }
        });
    }

    String CheckDateInOut(String nameHotel, int numberRoom)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select min(Date_in) from datein_out where " +
                    "(SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d')) < date_out and " +
                    "(SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d')) < date_in and id_room in " +
                    "(select id_room from rooms where num_of_room = " + numberRoom + " and id_hotel in " +
                    "(select id_hotel from hotels where name_hotel = '" + nameHotel + "'))");
            if (resultSet.next())
            {
                return resultSet.getString("min(Date_in)");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    void SelectNumberRoomFromTable(String nameHotel, int countPlace)
    {
        try
        {
            for (int i = 0; i < arrayNumberRoom.length; i++)
            {
                arrayNumberRoom[i] = null;
            }
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select num_of_room from rooms r where id_hotel in (select id_hotel from hotels where name_hotel = '" +
                    nameHotel + "') and count_of_place = " + countPlace + " and 0 in " +
                    "(select count(id_room) from DateIn_Out d where r.id_room = d.id_room and " +
                    "(SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d')) < d.date_out and " +
                    "(SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d')) > d.date_in)");
            int index = 0;
            while (resultSet.next())
            {
                arrayNumberRoom[index] = resultSet.getString("num_of_room");
                index++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void FillNumberRoom(JComboBox jComboBox)
    {
        int index = 0;
        while(arrayNumberRoom[index] != null)
        {
            jComboBox.addItem(arrayNumberRoom[index]);
            index++;
        }
    }

    boolean RegistrationClient(String surname, String name, String middlename, String birthday, String passport, String email,
                               String nameHotel, String numberRoom, String dateIn, String dateOut)
    {
        int lastIDClient = 0, id_room = 0;
        boolean numIsEmpty = true;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate("Insert into Clients(cl_surname, cl_name, cl_middlename, cl_birthday, cl_passport, cl_login, cl_password, cl_email) " +
                    "values ('" + surname + "','" + name + "','" + middlename + "','" + birthday + "'," + passport + ",'" +
                    passport + "','newpass','" + email + "')");

            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if (resultSet.next())
            {
                lastIDClient = resultSet.getInt("LAST_INSERT_ID()");
            }

            resultSet = statement.executeQuery("SELECT id_room from Rooms where id_hotel in (select id_hotel from Hotels where name_hotel = '" +
                    nameHotel + "') and num_of_room = " + numberRoom);
            if (resultSet.next())
            {
                id_room = resultSet.getInt("id_room");
            }

            statement.executeUpdate("insert into DateIn_Out (id_room, id_client, Date_in, Date_out) values (" + id_room + "," + lastIDClient +
                    ",'" + dateIn + "','" + dateOut + "')");

            resultSet = statement.executeQuery("select num_of_room from Rooms r where id_room = " + id_room + " and count_of_place in " +
                    "(select count(id_room) from DateIn_Out d where r.id_room = d.id_room " +
                    "and (SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d')) < d.date_out " +
                    "and (SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d')) >= d.date_in);");
            if (resultSet.next())
            {
                numIsEmpty = false;
            }
            if (!numIsEmpty)
            {
                statement.executeUpdate("UPDATE rooms SET is_empty = 1 where id_room = " + id_room);
            }

            connection.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Failed to register!");
            return false;
        }
        return true;
    }
}
