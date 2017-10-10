package garage;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Category {

    private String category = null;
    private String title = null;
    private LocalDate date = null;
    private int mileage = 0;
    private int limitMiles = 0;
    private int limitMonths = 0;
    private boolean needService = false;

    private GridPane categoryGrid = new GridPane();
    private Label titleLbl = new Label();
    private Label dateLbl = new Label();
    private Label mileageLbl = new Label();
    private Label limitMilesLbl = new Label();
    private Label limitMonthsLbl = new Label();

    public Category(String[] lineArray) throws ParseException {
        // Set label CSS features
        titleLbl.setId("title");
        dateLbl.setId("detail");
        mileageLbl.setId("detail");
        limitMonthsLbl.setId("detail");
        limitMilesLbl.setId("detail");
        //
        setCategory(lineArray[0]);
        setTitle(lineArray[1]);
        setLimitMiles(Integer.parseInt(lineArray[2]));
        setLimitMonths(Integer.parseInt(lineArray[3]));
        // Defaults
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        /*
           Locale specifies human language for translating, and cultural norms
           for lowercase/uppercase and abbreviations and such.
           Example: Locale.US
         */
        formatter = formatter.withLocale( Locale.US );
        setDate(LocalDate.parse("01/01/1900", formatter));
        setMileage(0);
        setCategoryGrid();
    }

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String str) {
        this.category = str;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String str) {
        this.title = str;
        this.titleLbl.setText(str);
    }

    public Integer getLimitMiles() {
        return this.limitMiles;
    }
    public void setLimitMiles(Integer miles) {
        this.limitMiles = miles;
        this.limitMilesLbl.setText(miles.toString());
    }

    public Integer getLimitMonths() {
        return this.limitMonths;
    }
    public void setLimitMonths(Integer months) {
        this.limitMonths = months;
        this.limitMonthsLbl.setText(months.toString());
    }

    public LocalDate getDate() { return this.date; }
    public String getDateString() {
        return this.date.toString();
    }
    public void setDate(LocalDate date) {
        this.date = date;
        if (date.getYear() == 1900) {
            this.dateLbl.setText("---");
        } else {
            this.dateLbl.setText(getDateString());
        }
    }

    public Integer getMileage() { return this.mileage; }
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
        this.mileageLbl.setText(mileage.toString());
    }

    public Boolean getServiceNeeds() { return this.needService; }
    public void setServiceNeeds(LocalDate recentDate, Integer recentMileage) {

        // In need of service - due to miles
        Boolean miles = false;
        if (this.limitMiles > 0) {
            miles = ((recentMileage - this.mileage) > this.limitMiles);
        }

        // In need of service - due to time/months
        double daysBetween = Duration.between(recentDate.atStartOfDay(), this.date.atStartOfDay()).toDays();
        final double avgDaysInMonth = 30.42;
        Boolean months = false;
        if (this.limitMonths > 0) {
            months = ((daysBetween / avgDaysInMonth) >= this.limitMonths); // TODO
        }

        if (miles || months) {
            this.needService = true;
            if (miles) {
                this.mileageLbl.setStyle("-fx-background-color: red;");
                this.limitMilesLbl.setStyle("-fx-background-color: red;");
            } else {
                this.mileageLbl.setId("detail");
                this.limitMilesLbl.setId("detail");
            }
            if (months) {
                this.dateLbl.setStyle("-fx-background-color: red;");
                this.limitMonthsLbl.setStyle("-fx-background-color: red;");
            } else {
                this.dateLbl.setId("detail");
                this.limitMonthsLbl.setId("detail");
            }
        } else {
            this.needService = false;
        }
    }

    public GridPane getCategoryGrid() {
        return this.categoryGrid;
    }
    private void setCategoryGrid() {
        categoryGrid.setGridLinesVisible(true);
        categoryGrid.add(titleLbl, 0, 0);
        categoryGrid.add(dateLbl, 1, 0);
        categoryGrid.add(mileageLbl, 2, 0);
        categoryGrid.add(limitMonthsLbl, 3, 0);
        categoryGrid.add(limitMilesLbl, 4, 0);
    }
}
