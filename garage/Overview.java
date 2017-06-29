package garage;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by kenbutler on 6/20/17.
 */
public class Overview extends GridPane {
    private boolean DEBUG = true;
    private String baseLocation = "/Users/kenbutler/IdeaProjects/VehicleMaintenance/src/";
    String[] columnNames = new String[5];
    Object[][] data = null;
    int totalRows, totalCols;

    public Overview() throws IOException {
        initializeHeaders();
        ReadData("overview.txt");
        //SetGrid();
    }

    private void initializeHeaders() {
        columnNames[0] = "ITEM";
        columnNames[1] = "DATE\nLAST CHANGED";
        columnNames[2] = "MILES\nLAST CHANGED";
        columnNames[3] = "LIMIT\n(MILES)";
        columnNames[4] = "LIMIT\n(MONTHS)";
    }

    private Label makeCategoryLabel(String name) {
        Label lbl = new Label(name);
        lbl.setId("categoryLabel"); // TODO
        return lbl;
    }

    private Label makeItemLabel(String name) {
        Label lbl = new Label(name);
        lbl.setId("itemLabel"); // TODO
        return lbl;
    }

    public void ReadData (String csvFile) throws IOException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(baseLocation + csvFile));

            // Initialize row count
            int totalRows = 0;

            while ((line = br.readLine()) != null) {

                // Split the line by way of commas
                String[] lineArray = line.split(cvsSplitBy);

                if (DEBUG) {System.out.format("Line array length: %d\n", lineArray.length);}

                if (lineArray.length > 1) { // Item line

                    for (int i = 0; i < lineArray.length; i++) { // Parse items

                        if ((i >= 3) && (Integer.parseInt(lineArray[i]) == 0)) {

                            // Limit column without limit
                            if (DEBUG) {System.out.format("N/A %s\t", lineArray[i]);}
                            this.add(makeItemLabel("N/A"),i+1,totalRows+1);

                        } else if (lineArray[i].length() > 0) {

                            // Normal item value
                            if (DEBUG) {System.out.format("%s\t", lineArray[i]);}
                            this.add(makeItemLabel(lineArray[i]),i+1,totalRows+1);

                        } else {

                            // Empty item value
                            if (DEBUG) {System.out.print("N/A\t");}
                            this.add(makeItemLabel("N/A"),i+1,totalRows+1);

                        }

                    }
                    if (DEBUG) {System.out.format("\n");}
                    //addEntry(lineArray);

                } else if (lineArray.length == 1) { // Category

                    if (DEBUG) {System.out.format("CATEGORY - %s\n", lineArray[0]);}
                    //mainPanel[row][0].add(makeCategoryLabel(lineArray[0]));
                    this.add(makeCategoryLabel(lineArray[0]),0,totalRows+1);

                }
                totalRows++; // Increment

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

}
