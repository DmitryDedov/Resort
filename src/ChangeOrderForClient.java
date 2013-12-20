import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

public class ChangeOrderForClient extends JFrame
{
    int id_client = 0;
    int id_order = 0;
    final String[] name = new String[1];
    final String[] type = new String[1];
    final String[] date = new String[1];
    final String[] time = new String[1];
    final String[] count = new String[1];
    final String[] status = new String[1];

    String[] arrayOrders = new String[100];
    ChangeOrderForClient(int id_client_allien)
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
        model.addColumn("type");
        model.addColumn("date");
        model.addColumn("time");
        model.addColumn("count");
        model.addColumn("status");
        model.addRow(new String[] {"name", "type", "date", "time", "count", "status"});

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
                    if ((e.getColumn() == 2) || (e.getColumn() == 3) || (e.getColumn() == 4))
                    {
                        UpdateEvent(nameColumn, updateValue);
                    }
                }
            }
        });

        setContentPane(jpanel);
        setSize(900, 300);
    }

    void GetValues(JTable jTable)
    {
        jTable.setColumnSelectionInterval(0, 1);
        int selectedRow = jTable.getSelectedRow();
        TableModel model = jTable.getModel();
        name[0] = model.getValueAt(selectedRow, 0).toString();
        type[0] = model.getValueAt(selectedRow, 1).toString();
        date[0] = model.getValueAt(selectedRow, 1).toString();
        time[0] = model.getValueAt(selectedRow, 1).toString();
        count[0] = model.getValueAt(selectedRow, 1).toString();
        status[0] = model.getValueAt(selectedRow, 1).toString();
        GetIdEvent(name[0], type[0]);
    }

    void GetIdEvent(String name, String type)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_order from orders where ord_name = '" + name + "' and ord_type = '" + type + "'");
            if (resultSet.next())
            {
                id_order = resultSet.getInt("id_order");
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

            statement.executeUpdate("update client_order set " + nameColumn + " ='" + updateValue + "' where id_event = " + id_order + " and id_client =" + id_client);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void SelectAllOrders()
    {
        for (int i = 0; i < arrayOrders.length; i++)
        {
            arrayOrders[i] = null;
        }
        int index = 0;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select ord_name, ord_type, date, time, count, status from orders o, client_order co where o.id_order = co.id_order and co.id_client = " + id_client);
            while (resultSet.next())
            {
                arrayOrders[index] = resultSet.getString("ord_name");
                arrayOrders[index + 1] = resultSet.getString("ord_type");
                arrayOrders[index + 2] = resultSet.getString("date");
                arrayOrders[index + 3] = resultSet.getString("time");
                arrayOrders[index + 4] = resultSet.getString("count");
                arrayOrders[index + 5] = resultSet.getString("status");
                index += 6;
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
        while (arrayOrders[index] != null)
        {
            if (index % 6 == 0)
            {
                model.addRow(new String[6]);
                row++;
                column = 0;
            }

            model.setValueAt(arrayOrders[index], row, column);
            index++;
            column++;
        }
    }

}