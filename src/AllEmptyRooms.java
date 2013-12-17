import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AllEmptyRooms extends JFrame
{
    String[] arrayRooms = new String[100];
    AllEmptyRooms()
    {
        super("All empty rooms");
        AddComponentsOnForm();
    }

    void AddComponentsOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(900, 300);
        jpanel.setLayout(null);

        final DefaultTableModel model = new DefaultTableModel();
        JTable jTable = new JTable(model);
        jTable.setLocation(30, 30);
        jTable.setSize(600, 200);
        model.addColumn("Number room");
        model.addColumn("Count place");
        model.addColumn("is empty");
        model.addColumn("Name hotel");
        model.addRow(new String[] {"Number room", "Count place", "is empty", "Name hotel"});

        jpanel.add(jTable);

        JButton jButtonAll = new JButton("all");
        jButtonAll.setSize(100, 20);
        jButtonAll.setLocation(700, 30);
        jpanel.add(jButtonAll);

        JButton jButtonAllEmpty = new JButton("all empty");
        jButtonAllEmpty.setSize(100, 20);
        jButtonAllEmpty.setLocation(700, 70);
        jpanel.add(jButtonAllEmpty);

        JButton jButtonAllNotEmpty = new JButton("all not empty");
        jButtonAllNotEmpty.setSize(100, 20);
        jButtonAllNotEmpty.setLocation(700, 110);
        jpanel.add(jButtonAllNotEmpty);

        JButton jButtonAllNotEmptyNotFull = new JButton("all not empty not full");
        jButtonAllNotEmptyNotFull.setSize(100, 20);
        jButtonAllNotEmptyNotFull.setLocation(700, 150);
        jpanel.add(jButtonAllNotEmptyNotFull);

        jButtonAll.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SelectAllRooms();
                FillTableOnForm(model);
            }
        });

        jButtonAllEmpty.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SelectAllEmptyRooms();
                FillTableOnForm(model);
            }
        });

        jButtonAllNotEmpty.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SelectAllNotEmpty();
                FillTableOnForm(model);
            }
        });

        jButtonAllNotEmptyNotFull.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SelectNotEmptyNotFull();
                FillTableOnForm(model);
            }
        });

        setContentPane(jpanel);
        setSize(900, 300);
    }

    void SelectAllRooms()
    {
        for (int i = 0; i < arrayRooms.length; i++)
        {
            arrayRooms[i] = null;
        }
        int index = 0;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select num_of_room, count_of_place, is_empty, name_hotel from rooms r, hotels h where r.id_hotel = h.id_hotel");
            while (resultSet.next())
            {
                arrayRooms[index] = resultSet.getString("num_of_room");
                arrayRooms[index + 1] = resultSet.getString("count_of_place");
                arrayRooms[index + 2] = resultSet.getString("is_empty");
                arrayRooms[index + 3] = resultSet.getString("name_hotel");
                index += 4;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void SelectAllEmptyRooms()
    {
        for (int i = 0; i < arrayRooms.length; i++)
        {
            arrayRooms[i] = null;
        }
        int index = 0;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select num_of_room, count_of_place, is_empty, name_hotel from rooms r, hotels h where r.id_hotel = h.id_hotel and r.is_empty = 0");
            while (resultSet.next())
            {
                arrayRooms[index] = resultSet.getString("num_of_room");
                arrayRooms[index + 1] = resultSet.getString("count_of_place");
                arrayRooms[index + 2] = resultSet.getString("is_empty");
                arrayRooms[index + 3] = resultSet.getString("name_hotel");
                index += 4;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void SelectAllNotEmpty()
    {
        for (int i = 0; i < arrayRooms.length; i++)
        {
            arrayRooms[i] = null;
        }
        int index = 0;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select num_of_room, count_of_place, is_empty, name_hotel from rooms r, hotels h where r.id_hotel = h.id_hotel and r.is_empty = 1");
            while (resultSet.next())
            {
                arrayRooms[index] = resultSet.getString("num_of_room");
                arrayRooms[index + 1] = resultSet.getString("count_of_place");
                arrayRooms[index + 2] = resultSet.getString("is_empty");
                arrayRooms[index + 3] = resultSet.getString("name_hotel");
                index += 4;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void SelectNotEmptyNotFull()
    {
        for (int i = 0; i < arrayRooms.length; i++)
        {
            arrayRooms[i] = null;
        }
        int index = 0;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select num_of_room, count_of_place, is_empty, name_hotel from rooms r, hotels h where r.id_hotel = h.id_hotel and r.count_of_place > (select count(id_room) from DateIn_Out d where r.id_room = d.id_room and (SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d')) < d.date_out and (SELECT DATE_FORMAT(sysdate(), '%Y-%m-%d')) > d.date_in);");

            while (resultSet.next())
            {
                arrayRooms[index] = resultSet.getString("num_of_room");
                arrayRooms[index + 1] = resultSet.getString("count_of_place");
                arrayRooms[index + 2] = resultSet.getString("is_empty");
                arrayRooms[index + 3] = resultSet.getString("name_hotel");
                index += 4;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    void  FillTableOnForm(DefaultTableModel model)
    {
        int index = 0, row = 0, column = 0;
        while (arrayRooms[index] != null)
        {
            if (index % 4 == 0)
            {
                model.addRow(new String[4]);
                row++;
                column = 0;
            }

            model.setValueAt(arrayRooms[index], row, column);
            index++;
            column++;
        }
    }
}
