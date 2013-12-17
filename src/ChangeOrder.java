import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

public class ChangeOrder extends JFrame
{
    int id_order = 0;
    final String[] name = new String[1];
    final String[] type = new String[1];

    String[] arrayRooms = new String[100];
    ChangeOrder()
    {
        super("Change order");
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
        model.addRow(new String[] {"name", "type"});

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
                    if (e.getColumn() < 4)
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
        GetIdEvent(name[0], type[0]);
    }

    void GetIdEvent(String name, String type)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_order from orders where ord_name = '" + name + "' and type = '" + type + "'");
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

            statement.executeUpdate("update orders set ord_" + nameColumn + " ='" + updateValue + "' where id_event = " + id_order);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void SelectAllOrders()
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
            ResultSet resultSet = statement.executeQuery("select ord_name, type from orders");
            while (resultSet.next())
            {
                arrayRooms[index] = resultSet.getString("ord_name");
                arrayRooms[index + 1] = resultSet.getString("type");
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
        while (arrayRooms[index] != null)
        {
            if (index % 2 == 0)
            {
                model.addRow(new String[2]);
                row++;
                column = 0;
            }

            model.setValueAt(arrayRooms[index], row, column);
            index++;
            column++;
        }
    }
}
