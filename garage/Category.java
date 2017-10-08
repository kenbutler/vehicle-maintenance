package garage;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Category {

    private String category = null;
    private String title = null;
    private Date date = null;
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
        setDate(new SimpleDateFormat("MM/dd/yyyy").parse("01/01/1900"));
        setMileage(0);
        setNeedService(false);
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

    public Date getDate() { return this.date; }
    public String getDateString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(this.date);
    }
    public void setDate(Date date) {
        this.date = date;
        this.dateLbl.setText(getDateString());
    }

    public Integer getMileage() { return this.mileage; }
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
        this.mileageLbl.setText(mileage.toString());
    }

    public Boolean getNeedService() { return this.needService; }
    public void setNeedService(Boolean needService) {
        this.needService = needService;
        if (this.needService) {
            this.titleLbl.setStyle("-fx-text-fill: red;");
        } else {
            this.titleLbl.setId("title");
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
        categoryGrid.add(limitMilesLbl, 3, 0);
        categoryGrid.add(limitMonthsLbl, 4, 0);
    }
}
