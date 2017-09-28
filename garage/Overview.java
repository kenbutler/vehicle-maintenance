package garage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by kenbutler on 6/20/17.
 */
public class Overview extends ArrayList<Object> {

    private boolean DEBUG = true;
    private String baseLocation = "/Users/kenbutler/IdeaProjects/Vehicle/src/garage/";

    public Overview(String file) throws IOException {
        readData(file);
        loadData();
    }

    public void readData (String csvFile) throws IOException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(baseLocation + csvFile));

            while ((line = br.readLine()) != null) {

                // Split the line by way of commas
                String[] lineArray = line.split(cvsSplitBy);
                this.add(new Category(lineArray));

            } // End while loop of CSV read

            if (DEBUG) {
                System.out.format("***** DEBUG - Overview data read from %s *****\n", csvFile);
                System.out.format("data size is %d\n", this.size());
                for (int i = 0; i < this.size(); i++) {
                    Category temp = (Category) this.get(i);
                    System.out.format("(%s) %s --> Limits - Miles: %d, Months: %d\n",
                            temp.getCategory(),
                            temp.getTitle(),
                            temp.getLimitMiles(),
                            temp.getLimitMonths());
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

    public void analyzeLog(Log log) {
        for (int i=0; i < log.size(); i++) {
            Item item = (Item) log.get(i);
            for (int j=0; j < this.size(); j++) {
                Category cat = (Category) this.get(j);
                if (Objects.equals(cat.getTitle(), item.getTitle())) {
                    // Compare dates
                    if (item.getDate().after(cat.getDate())) {
                        // Update
                        ((Category) this.get(j)).setDate(item.getDate());
                        System.out.println("found match!");
                    }
                }
            }
        }

        if (DEBUG) {
            for (int i=0; i < this.size(); i++) {
                Category cat = (Category) this.get(i);
                System.out.format("%s last changed %s\n", cat.getTitle(), cat.getDateString());
            }
        }
    }

    public void loadData() {

        // Loop through data and load to
        for (int i=0; i < this.size(); i++) {
            //Controller.setData(data[i]);
        }
    }

}
