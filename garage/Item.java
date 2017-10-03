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

    private Date date;
    private Integer mileage;
    private String title;
    private String company = "???";
    private Double price = 0.0;
    private String notes = "";

    public Item(String[] lineArray) throws ParseException {
        date = new SimpleDateFormat("MM/dd/yyyy").parse(lineArray[0]);
        mileage = Integer.parseInt(lineArray[1]);
        title = lineArray[2];
        company = lineArray[3];
        price = Double.parseDouble(lineArray[4].replaceAll("[^\\d.]+", "")); // TODO: Make money value
        if (lineArray.length >= 6) {
            notes = lineArray[5];
        }
    }

    public String getTitle() {
        return this.title;
    }
    public Boolean setTitle(String str) {
        this.title = str;
        return true;
    }

    public Date getDateFull() {
        return this.date;
    }
    public String getDate() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(this.date);
    }
    public Boolean setDate(Date date) {
        this.date = date;
        return true;
    }

    public Integer getMileageNumeric() {
        return this.mileage;
    }
    public String getMileage() {
        return NumberFormat.getNumberInstance(Locale.US).format(this.mileage);
    }
    public Boolean setMileage(Integer miles) {
        this.mileage = miles;
        return true;
    }

    public String getCompany() { return this.company; }
    public Boolean setCompany(String company) {
        this.company = company;
        return true;
    }

    public String getPrice() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setCurrency(Currency.getInstance("USD"));
        return nf.format(new BigDecimal(this.price));
    }
    public Boolean setPrice(Double price) {
        this.price = price;
        return true;
    }

    public String getNotes() { return this.notes; }
    public Boolean setNotes(String notes) {
        this.notes = notes;
        return true;
    }
}
