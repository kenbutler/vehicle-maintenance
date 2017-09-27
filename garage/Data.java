package garage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Data extends ArrayList<Object>{

    private boolean DEBUG = true;
    private String baseLocation = "/Users/kenbutler/IdeaProjects/Vehicle/src/garage/";

    // Variables here

    public Data() throws IOException {
        // Init here
        ReadData("log.csv");
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
                this.add(new Item(lineArray));

            } // End while loop of CSV read

            if (DEBUG) {
                System.out.format("***** DEBUG - Data read from %s *****\n", csvFile);
                System.out.format("data size is %d\n", this.size());
                for (int i = 0; i < this.size(); i++) {
                    Item temp = (Item) this.get(i);
                    System.out.format("%s, %s, %d\n",
                            temp.getTitle(),
                            temp.getDateString(), temp.getMileage());
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
}