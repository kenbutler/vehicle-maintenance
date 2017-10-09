package garage;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Item {

    private State state = new State();
    private String title;
    private String company = "???";
    private Double price = 0.0;
    private String notes = "";

    public Item(String[] lineArray) throws ParseException {
        state.setDate(new SimpleDateFormat("MM/dd/yyyy").parse(lineArray[0]));
        state.setMileage(Integer.parseInt(lineArray[1]));
        title = lineArray[2];
        company = lineArray[3];
        price = Double.parseDouble(lineArray[4].replaceAll("[^\\d.]+", "")); // TODO: Make money value
        if (lineArray.length >= 6) {
            notes = lineArray[5];
        }
    }

    // Title
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String str) {
        this.title = str;
    }

    // Date
    public Date getDateNumeric() {
        return this.state.getDateNumeric();
    }
    public String getDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.state.getDateNumeric());
    }
    public void setDate(Date date) { this.state.setDate(date); }

    // Mileage
    public Integer getMileage() {
        return this.state.getMileage();
    }
    public String getMileageString() { return NumberFormat.getNumberInstance(Locale.US).format(this.state.getMileage()); }
    public void setMileage(Integer miles) {
        this.state.setMileage(miles);
    }

    // Company
    public String getCompany() { return this.company; }
    public void setCompany(String company) {
        this.company = company;
    }

    // Price
    public BigDecimal getPrice() {
        return new BigDecimal(this.price).setScale(2, BigDecimal.ROUND_DOWN);
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    // Notes
    public String getNotes() { return this.notes; }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
