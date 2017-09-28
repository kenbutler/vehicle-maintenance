package garage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Category {

    private String category;
    private String title;
    private int limitMiles;
    private int limitMonths;
    private Date date;
    private int mileage;

    public Category(String[] lineArray) throws ParseException {
        category = lineArray[0];
        title = lineArray[1];
        limitMiles = Integer.parseInt(lineArray[2]);
        limitMonths = Integer.parseInt(lineArray[3]);
        // Defaults
        date = new SimpleDateFormat("MM/dd/yyyy").parse("01/01/1900");
        mileage = 0;
    }

    public String getCategory() {
        return this.category;
    }
    public Boolean setCategory(String str) {
        this.category = str;
        return true;
    }

    public String getTitle() {
        return this.title;
    }
    public Boolean setTitle(String str) {
        this.title = str;
        return true;
    }

    public Integer getLimitMiles() {
        return this.limitMiles;
    }
    public Boolean setLimitMiles(Integer miles) {
        this.limitMiles = miles;
        return true;
    }

    public Integer getLimitMonths() {
        return this.limitMonths;
    }
    public Boolean setLimitMonths(Integer months) {
        this.limitMonths = months;
        return true;
    }

    public Date getDate() { return this.date; }
    public String getDateString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(this.date);
    }
    public Boolean setDate(Date date) {
        this.date = date;
        return true;
    }
}
