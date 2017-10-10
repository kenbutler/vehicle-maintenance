package garage;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kenbutler on 10/8/17.
 */
public class State {

    private LocalDate date = null;
    private Integer mileage;

    public State() throws ParseException {
        // Initialize member variables
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        /*
           Locale specifies human language for translating, and cultural norms
           for lowercase/uppercase and abbreviations and such.
           Example: Locale.US
         */
        formatter = formatter.withLocale( Locale.US );
        date = LocalDate.parse("01/01/1900", formatter);
        mileage = 0;
    }

    // Date
    public LocalDate getDateNumeric() {
        return this.date;
    }
    public String getDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.date);
    }
    public void setDate(LocalDate date) { this.date = date; }

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
