package garage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Item {

    private Integer index;
    private Date date;
    private Integer mileage;
    private String title;
    private String company = "blah";
    private Integer price = 0;
    private String notes = "NONE";

    public Item(String[] lineArray) throws ParseException {
        date = new SimpleDateFormat("MM/dd/yyyy").parse(lineArray[0]);
        mileage = Integer.parseInt(lineArray[1]);
        title = lineArray[2];
        // Price TODO
        // Notes TODO
    }

    public String getTitle() {
        return this.title;
    }
    public Boolean setTitle(String str) {
        this.title = str;
        return true;
    }

    public Date getDate() {
        return this.date;
    }
    public String getDateString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(this.date);
    }
    public Boolean setDate(Date date) {
        this.date = date;
        return true;
    }

    public Integer getMileage() {
        return this.mileage;
    }
    public Boolean setMileage(Integer miles) {
        this.mileage = miles;
        return true;
    }
}
