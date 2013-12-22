import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ChangeServiceForClient extends JFrame
{
    int id_client = 0;
    int id_clServ = 0;
    final String[] name = new String[1];
    final String[] date = new String[1];
    final String[] time = new String[1];
    final String[] status = new String[1];

    String[] arrayServices = new String[100];
    ChangeServiceForClient(int id_client_allien)
    {
        super("Change order");
        id_client = id_client_allien;
        AddComponentsOnForm();
    }

    void AddComponentsOnForm()
    {
        JPanel jpanel = new JPanel();
        jpanel.setSize(900, 300);
        jpanel.setLayout(null);

        final DefaultTableModel model = new DefaultTableModel();
        final JTable jTable = new JTable(model);
        jTable.setLocation(30, 30);
        jTable.setSize(600, 200);
        model.addColumn("name");
        model.addColumn("date");
        model.addColumn("time");
        model.addColumn("status");
        model.addRow(new String[] {"name", "date", "time", "status"});

        SelectAllOrders();
        FillTableOnForm(model);

        jpanel.add(jTable);

        ListSelectionModel selectionModel = jTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
                GetValues(jTable);
            }
        });

        TableModel tm = jTable.getModel();
        tm.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE)
                {
                    GetValues(jTable);
                    String updateValue = jTable.getValueAt(jTable.getSelectedRow(), e.getColumn()).toString();
                    String nameColumn = jTable.getColumnName(e.getColumn());
                    if ((e.getColumn() == 1) || (e.getColumn() == 2))
                    {
                        UpdateEvent(nameColumn, updateValue);
                    }
                }
            }
        });

        JButton jButtonDelete = new JButton("Delete");
        jButtonDelete.setLocation(700, 20);
        jButtonDelete.setSize(100, 30);
        jpanel.add(jButtonDelete);

        jButtonDelete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Delete();
                SelectAllOrders();
                FillTableOnForm(model);
            }
        });

        setContentPane(jpanel);
        setSize(900, 300);
    }

    void Delete()
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            statement.executeUpdate("delete from client_service where id_cs = " + id_clServ);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void GetValues(JTable jTable)
    {
        jTable.setColumnSelectionInterval(0, 1);
        int selectedRow = jTable.getSelectedRow();
        TableModel model = jTable.getModel();
        name[0] = model.getValueAt(selectedRow, 0).toString();
        date[0] = model.getValueAt(selectedRow, 1).toString();
        time[0] = model.getValueAt(selectedRow, 2).toString();
        status[0] = model.getValueAt(selectedRow, 3).toString();
        GetIdService(name[0], date[0], time[0]);
    }

    void GetIdService(String name, String date, String time)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_cs from client_service where id_serv in (select id_serv from services where serv_name = '"
                    + name + "') and id_client = " + id_client + " and date = '" + date + "' and time = '" + time + "'");
            if (resultSet.next())
            {
                id_clServ = resultSet.getInt("id_cs");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void UpdateEvent(String nameColumn, String updateValue)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();

            statement.executeUpdate("update client_service set " + nameColumn + " ='" + updateValue + "' where id_cs = " + id_clServ);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void SelectAllOrders()
    {
        for (int i = 0; i < arrayServices.length; i++)
        {
            arrayServices[i] = null;
        }
        int index = 0;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select serv_name, date, time, status from services s, client_service cs where s.id_serv = cs.id_serv and cs.id_client = " + id_client);
            while (resultSet.next())
            {
                arrayServices[index] = resultSet.getString("serv_name");
                arrayServices[index + 1] = resultSet.getString("date");
                arrayServices[index + 2] = resultSet.getString("time");
                arrayServices[index + 3] = resultSet.getString("status");
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
        while (arrayServices[index] != null)
        {
            if (index % 4 == 0)
            {
                model.addRow(new String[4]);
                row++;
                column = 0;
            }

            model.setValueAt(arrayServices[index], row, column);
            index++;
            column++;
        }
    }


}
