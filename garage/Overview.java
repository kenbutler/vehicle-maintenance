package garage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kenbutler on 6/20/17.
 */
public class Overview extends JPanel {
    private boolean DEBUG = true;
    private String baseLocation = "/Users/kenbutler/IdeaProjects/VehicleMaintenance/src/";
    private JTable table;
    private JPanel[][] mainPanel;
    String[] columnNames = new String[5];
    Object[][] data = null;

    public Overview() throws IOException {

        columnNames[0] = "ITEM";
        columnNames[1] = "DATE\nLAST CHANGED";
        columnNames[2] = "MILES\nLAST CHANGED";
        columnNames[3] = "LIMIT\n(MILES)";
        columnNames[4] = "LIMIT\n(MONTHS)";

        int rows = 20;
        int cols = 5;
        mainPanel = new JPanel[rows][cols];
        setLayout(new GridLayout(rows, cols));

        // Initialize main panel
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                switch (j) {
                    case 0:
                        // Item
                        mainPanel[i][j] = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        mainPanel[i][j].setPreferredSize(new Dimension(120,30));
                        break;
                    case 1:
                        // Date last changed
                    case 2:
                        // Miles last changed
                    case 3:
                        // Miles Limit
                    case 4:
                        // Time limit
                        mainPanel[i][j] = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        mainPanel[i][j].setPreferredSize(new Dimension(50,30));
                        break;
                    default:
                        break;
                }
                add(mainPanel[i][j]);
            }
        }
        // Initialize column headers
        for (int i=0; i<cols; i++) {
            mainPanel[0][i].add(makeCategoryLabel(columnNames[i]));
        }


        table = new JTable(new NonEditableModel(data, columnNames));
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        ReadData("overview.txt");

        //this.setLayout(new BorderLayout());
        //this.add(table, BorderLayout.CENTER);
    }

    JLabel makeCategoryLabel(String name) {
        JLabel out = new JLabel(name);
        //out.setPreferredSize(new Dimension(90,30));
        out.setHorizontalAlignment(SwingConstants.LEFT);
        return out;
    }

    JLabel makeItemLabel(String name) {
        JLabel out = new JLabel(name);
        //out.setPreferredSize(new Dimension(90,30));
        out.setHorizontalAlignment(SwingConstants.LEFT);
        return out;
    }

    public void ReadData (String csvFile) throws IOException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(baseLocation + csvFile));
            int row = 1; // Rows
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] lineArray = line.split(cvsSplitBy);
                if (DEBUG) {System.out.format("Line array length: %d\n", lineArray.length);}
                if (lineArray.length > 1) {
                    for (int i = 0; i < lineArray.length; i++) {
                        if ((i >= 3) && (Integer.parseInt(lineArray[i]) == 0)) {
                            if (DEBUG) {
                                System.out.format("N/A %s\t", lineArray[i]);
                            }
                            mainPanel[row][i].add(makeItemLabel("N/A"));
                        } else if (lineArray[i].length() > 0) {
                            if (DEBUG) {System.out.format("%s\t", lineArray[i]);}
                            mainPanel[row][i].add(makeItemLabel(lineArray[i]));
                        } else {
                            if (DEBUG) {System.out.print("N/A\t");}
                            mainPanel[row][i].add(makeItemLabel("N/A"));
                        }

                    }
                    if (DEBUG) {System.out.format("\n");}
                    //addEntry(lineArray);
                } else if (lineArray.length == 1) {
                    if (DEBUG) {System.out.format("CATEGORY - %s\n", lineArray[0]);}
                    mainPanel[row][0].add(makeCategoryLabel(lineArray[0]));
                }
                row++;
            } // End while loop of CSV read

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } // End try to populate / exception handler

    } // End ReadData()

    public Object[][] getTableData (JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++) {
            for (int j = 0 ; j < nCol ; j++) {
                tableData[i][j] = dtm.getValueAt(i,j);
            }
            //totalValue += (Double) tableData[i][3];
        }

        return tableData;
    }

    private void addEntry(Object[] newEntry) {
        // Get data from table
        Object[][] oldData = getTableData(table);
        Object[][] newData = new Object[oldData.length+1][columnNames.length];
        if (DEBUG)
            System.out.format("oldData.length = %d, columnNames.length = %d \n", oldData.length, columnNames.length);
        // Populate newData with original data
        for (int i=0; i<oldData.length; i++) {
            for (int j=0; j<columnNames.length; j++) {
                newData[i][j] = oldData[i][j];
            }
        }

        // Item
        newData[oldData.length][0] = newEntry[0];
        // Date last updated
        String dateString = (String) newEntry[1];
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date myDate;
        if (dateString.length() > 0) {
            try {
                myDate = df.parse(dateString);
                String newDateString = df.format(myDate);
                newData[oldData.length][1] = newDateString;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // Miles since last update
        if (newEntry[2].toString().length() > 0) {
            newData[oldData.length][2] = Integer.parseInt((String) newEntry[2]); // Amount
        }
        // Limit (miles)
        if (newEntry[3].toString().length() > 0) {
            newData[oldData.length][3] = newEntry[3];
        }
        // Limit (months)
        if (newEntry[4].toString().length() > 0) {
            newData[oldData.length][4] = newEntry[4];
        }
        data = newData; // Save new data to global data store

        // Set new table data
        table.setModel(new NonEditableModel(newData, columnNames));
    }

}
