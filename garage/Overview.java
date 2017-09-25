package garage;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sun.java2d.pipe.SpanShapeRenderer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kenbutler on 6/20/17.
 */
public class Overview {

    public class Item {
        String category;
        String title;
        Date lastChangeDate;
        int lastChangeMiles;
        int limitMiles;
        int limitMonths;

        public Item(String[] lineArray) throws ParseException {
            category = lineArray[0];
            title = lineArray[1];
            if (lineArray[2].length() > 0) {
                lastChangeDate = new SimpleDateFormat("dd/MM/yyyy").parse(lineArray[2]);
            } else {
                lastChangeDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900");
            }
            if (lineArray[3].length() > 0) {
                lastChangeMiles = Integer.parseInt(lineArray[3]);
            } else {
                lastChangeMiles = -1;
            }
            limitMiles = Integer.parseInt(lineArray[4]);
            limitMonths = Integer.parseInt(lineArray[5]);
        }

        public String simpleDate() {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(this.lastChangeDate);
        }
    }

    private boolean DEBUG = true;
    private String baseLocation = "/Users/kenbutler/IdeaProjects/Vehicle/src/garage/";
    //Object[] data = null;
    ArrayList<Object> data = new ArrayList<>();
    int totalRows, totalCols;

    public Overview() throws IOException {
        ReadData("overview.txt");
        LoadData();
    }

    public void ReadData (String csvFile) throws IOException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(baseLocation + csvFile));

            while ((line = br.readLine()) != null) {

                // Split the line by way of commas
                String[] lineArray = line.split(cvsSplitBy);
                data.add(new Item(lineArray));

            } // End while loop of CSV read

            if (DEBUG) {
                System.out.print("***** DEBUG - Data read from overview.txt *****\n");
                System.out.format("data size is %d\n", data.size());
                for (int i = 0; i < data.size(); i++) {
                    Item temp = (Item) data.get(i);
                    System.out.format("%s, %s, %s, %d, %d, %d\n",
                            temp.category, temp.title,
                            temp.simpleDate(), temp.lastChangeMiles,
                            temp.limitMiles, temp.limitMonths);
                }
                System.out.print("***** END DEBUG *****\n\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

    public void LoadData() {

        // Loop through data and load to
        for (int i=0; i < data.size(); i++) {
            //Controller.setData(data[i]);
        }
    }

}
