package garage;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kenbutler on 10/8/17.
 */
public class State {

    private Date date = new Date();
    private Integer mileage;

    public State() throws ParseException {
        // Initialize member variables
        date = new SimpleDateFormat("MM/dd/yyyy").parse("1/1/1900");
        mileage = 0;
    }

    // Date
    public Date getDateNumeric() {
        return this.date;
    }
    public String getDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.date);
    }
    public void setDate(Date date) { this.date = date; }

    // Mileage
    public Integer getMileage() {
        return this.mileage;
    }
    public String getMileageString() {
        return NumberFormat.getNumberInstance(Locale.US).format(this.mileage);
    }
    public void setMileage(Integer miles) {
        this.mileage = miles;
    }
}
