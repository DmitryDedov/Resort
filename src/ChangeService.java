import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

public class ChangeService extends JFrame
{
    int id_service = 0;
    final String[] name = new String[1];
    final String[] max_number = new String[1];

    String[] arrayServices = new String[100];
    ChangeService()
    {
        super("Change service");
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
        model.addColumn("max_number");
        model.addRow(new String[] {"name", "max_number"});

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
                    if (e.getColumn() < 2)
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
        max_number[0] = model.getValueAt(selectedRow, 1).toString();
        GetIdOrder(name[0], max_number[0]);
    }

    void GetIdOrder(String name, String max_number)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_serv from services where serv_name = '" + name + "' and ord_type = " + max_number);
            if (resultSet.next())
            {
                id_service = resultSet.getInt("id_serv");
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

            statement.executeUpdate("update services set serv_" + nameColumn + " ='" + updateValue + "' where id_serv = " + id_service);
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
            ResultSet resultSet = statement.executeQuery("select serv_name, serv_max_number from services");
            while (resultSet.next())
            {
                arrayServices[index] = resultSet.getString("serv_name");
                arrayServices[index + 1] = resultSet.getString("serv_max_number");
                index += 2;
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
            if (index % 2 == 0)
            {
                model.addRow(new String[2]);
                row++;
                column = 0;
            }

            model.setValueAt(arrayServices[index], row, column);
            index++;
            column++;
        }
    }

}
