import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AllClientsView extends JFrame
{
    int id_client = 0;
    final String[] surname = new String[1];
    final String[] name = new String[1];
    final String[] middlename = new String[1];
    final String[] birthday = new String[1];
    final int[] passport = new int[1];
    final String[] login = new String[1];
    final String[] password = new String[1];
    final String[] email = new String[1];
    final String[] date_in = new String[1];
    final String[] date_out = new String[1];
    final int[] numberRoom = new int[1];
    final String[] name_hotel = new String[1];

    String[] arraySelectDateAboutClients = new String[500];
    AllClientsView()
    {
        super("View all clients");
        AddComponentOnForm();
    }
    void AddComponentOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(1200, 300);
        jpanel.setLayout(null);

        final DefaultTableModel model = new DefaultTableModel();
        final JTable jTable = new JTable(model);
        jTable.setLocation(20, 40);

        model.addColumn("surname");
        model.addColumn("name");
        model.addColumn("middlename");
        model.addColumn("birthday");
        model.addColumn("passport");
        model.addColumn("login");
        model.addColumn("password");
        model.addColumn("Email");
        model.addColumn("Date_in");
        model.addColumn("Date_out");
        model.addColumn("Number room");
        model.addColumn("Name hotel");
        model.addRow(new String[]{"Surname", "Name", "Middlename", "Birthday", "Passport", "Login", "Password", "Email", "Date_in", "Date_out", "Number room", "Name_hotel"});

        SelectAllClient();
        FillTableOnForm(model);

        jTable.setSize(980, jTable.getRowCount() * jTable.getRowHeight());
        jpanel.add(jTable);

        ListSelectionModel selectionModel = jTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
                GetValues(jTable);
            }
        });

//        jTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int col = jTable.columnAtPoint(e.getPoint());
//                int row = jTable.rowAtPoint(e.getPoint());
//                }
//            }
//        });

        TableModel tm = jTable.getModel();
        tm.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE)
                {
                    //GetValues(jTable);
                    String updateValue = jTable.getValueAt(jTable.getSelectedRow(), e.getColumn()).toString();
                    String nameColumn = jTable.getColumnName(e.getColumn());
                    if (e.getColumn() < 8)
                    {
                        UpdateClients(nameColumn, updateValue);
                    }
                    if ((e.getColumn() == 8) || (e.getColumn() == 9))
                    {
                        UpdateDateIn_Out(nameColumn, updateValue);
                    }
                }
            }
        });

        JButton jButtonDelete = new JButton("Delete");
        jButtonDelete.setLocation(1050, 20);
        jButtonDelete.setSize(100, 30);
        jpanel.add(jButtonDelete);

        jButtonDelete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Delete(surname[0], name[0], middlename[0], birthday[0], passport[0], login[0], password[0], email[0], date_in[0], date_out[0], numberRoom[0], name_hotel[0]);
                SelectAllClient();
                FillTableOnForm(model);
            }
        });

        setContentPane(jpanel);
        setSize(1200, 300);
    }

    void GetValues(JTable jTable)
    {
        jTable.setColumnSelectionInterval(0, 11);
        int selectedRow = jTable.getSelectedRow();
        TableModel model = jTable.getModel();
        surname[0] = model.getValueAt(selectedRow, 0).toString();
        name[0] = model.getValueAt(selectedRow, 1).toString();
        middlename[0] = model.getValueAt(selectedRow, 2).toString();
        birthday[0] = model.getValueAt(selectedRow, 3).toString();
        passport[0] = Integer.parseInt(model.getValueAt(selectedRow, 4).toString());
        login[0] = model.getValueAt(selectedRow, 5).toString();
        password[0] = model.getValueAt(selectedRow, 6).toString();
        email[0] = model.getValueAt(selectedRow, 7).toString();
        date_in[0] = model.getValueAt(selectedRow, 8).toString();
        date_out[0] = model.getValueAt(selectedRow, 9).toString();
        numberRoom[0] = Integer.parseInt(model.getValueAt(selectedRow, 10).toString());
        name_hotel[0] = model.getValueAt(selectedRow, 11).toString();
        GetIdClient(surname[0], name[0], middlename[0], birthday[0], passport[0], login[0], password[0], email[0], date_in[0], date_out[0], numberRoom[0], name_hotel[0]);
    }

    void UpdateClients(String nameColumn, String updateValue)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            statement.executeUpdate("update clients set cl_" + nameColumn + " ='" + updateValue + "' where id_client = " + id_client);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void UpdateDateIn_Out(String nameColumn, String updateValue)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            statement.executeUpdate("update DateIn_Out set " + nameColumn + " ='" + updateValue + "' where id_client = " + id_client);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void SelectAllClient()
    {
        int index = 0;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select cl_surname, cl_name, cl_middlename, cl_birthday, cl_passport, " +
                    "cl_login, cl_password, cl_Email, Date_in, Date_Out, num_of_room, name_hotel " +
                    "from clients c, datein_out d, rooms r, hotels h where c.id_client = d.id_client and r.id_room = d.id_room and h.id_hotel = r.id_hotel;");
            while (resultSet.next())
            {
                arraySelectDateAboutClients[index] = resultSet.getString("cl_surname");
                arraySelectDateAboutClients[index + 1] = resultSet.getString("cl_name");
                arraySelectDateAboutClients[index + 2] = resultSet.getString("cl_middlename");
                arraySelectDateAboutClients[index + 3] = resultSet.getString("cl_birthday");
                arraySelectDateAboutClients[index + 4] = resultSet.getString("cl_passport");
                arraySelectDateAboutClients[index + 5] = resultSet.getString("cl_login");
                arraySelectDateAboutClients[index + 6] = resultSet.getString("cl_password");
                arraySelectDateAboutClients[index + 7] = resultSet.getString("cl_Email");
                arraySelectDateAboutClients[index + 8] = resultSet.getString("Date_in");
                arraySelectDateAboutClients[index + 9] = resultSet.getString("Date_out");
                arraySelectDateAboutClients[index + 10] = resultSet.getString("num_of_room");
                arraySelectDateAboutClients[index + 11] = resultSet.getString("name_hotel");
                index += 12;
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Failed to register!");
        }
    }

    void  FillTableOnForm(DefaultTableModel model)
    {
//        while (model.getRowCount() > 1)
//        {
//            model.removeRow(1);
//        }

        int index = 0, row = 0, column = 0;
        while (arraySelectDateAboutClients[index] != null)
        {
            if (index % 12 == 0)
            {
                model.addRow(new String[12]);
                row++;
                column = 0;
            }
            model.setValueAt(arraySelectDateAboutClients[index], row, column);
            index++;
            column++;
        }
    }

    void Delete(String surname, String name, String middlename, String birthday, int passport, String login, String password, String email,
                String dateIn, String dateOut, int numberRoom, String nameHotel)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            statement.executeUpdate("delete from DateIn_Out where id_client = " + id_client);
            statement.executeUpdate("delete from clients where id_client = " + id_client);
            statement.executeUpdate("UPDATE rooms SET is_empty = 0 where num_of_room = " + numberRoom + " and id_hotel in (select id_hotel from hotels where name_hotel = '" +
                    nameHotel + "')");
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void GetIdClient(String surname, String name, String middlename, String birthday, int passport, String login, String password, String email,
                     String dateIn, String dateOut, int numberRoom, String nameHotel)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_client from clients where cl_surname = '" + surname + "' and cl_name = '" + name + "'and cl_middlename = '" + middlename +
                    "' and cl_birthday = '" + birthday + "' and cl_passport = " + passport + " and cl_login = '" + login + "'");
            if (resultSet.next())
            {
                id_client = resultSet.getInt("id_client");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
