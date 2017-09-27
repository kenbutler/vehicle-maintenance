package garage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Item {

    private String title;
    private Date date;
    private Integer mileage;

    public Item(String[] lineArray) throws ParseException {
        date = new SimpleDateFormat("dd/MM/yyyy").parse(lineArray[0]);
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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
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
