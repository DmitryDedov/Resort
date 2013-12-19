import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.*;

public class FullEventForClient extends JFrame
{
    int id_event = 0;
    final String[] name = new String[1];
    final String[] place = new String[1];
    final String[] date = new String[1];
    final String[] time = new String[1];

    String[] arrayRooms = new String[100];

    void AddComponentsOnForm(JPanel jPanel)
    {
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setLocation(10, 30);
        final DefaultTableModel model = new DefaultTableModel();

        final JTable jTable = new JTable(model);
        jTable.setEnabled(false);
        jTable.setShowGrid(false);
        jTable.setPreferredScrollableViewportSize(new Dimension(380, 180));
        jTable.setFillsViewportHeight(true);
        jScrollPane.getViewport().add(jTable);

        model.addColumn("name");
        model.addColumn("place");
        model.addColumn("date");
        model.addColumn("time");
        model.addRow(new String[] {"name", "place", "date", "time"});

        SelectAllEvents();
        FillTableOnForm(model);

        jPanel.add(jScrollPane);

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
    }

    void GetValues(JTable jTable)
    {
        jTable.setColumnSelectionInterval(0, 3);
        int selectedRow = jTable.getSelectedRow();
        TableModel model = jTable.getModel();
        name[0] = model.getValueAt(selectedRow, 0).toString();
        place[0] = model.getValueAt(selectedRow, 1).toString();
        date[0] = model.getValueAt(selectedRow, 2).toString();
        time[0] = model.getValueAt(selectedRow, 3).toString();
        GetIdEvent(name[0], place[0], date[0], time[0]);
    }

    void GetIdEvent(String name, String place, String date, String time)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resort", "root", "12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_event from events where ev_name = '" + name + "' and ev_place = '" + place + "' and ev_date = '" + date + "'");
            if (resultSet.next())
            {
                id_event = resultSet.getInt("id_event");
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

            statement.executeUpdate("update events set ev_" + nameColumn + " ='" + updateValue + "' where id_event = " + id_event);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void SelectAllEvents()
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
            ResultSet resultSet = statement.executeQuery("select ev_name, ev_place, ev_date, ev_time from events");
            while (resultSet.next())
            {
                arrayRooms[index] = resultSet.getString("ev_name");
                arrayRooms[index + 1] = resultSet.getString("ev_place");
                arrayRooms[index + 2] = resultSet.getString("ev_date");
                arrayRooms[index + 3] = resultSet.getString("ev_time");
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
