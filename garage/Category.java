package garage;

import java.text.ParseException;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Category {

    private String category;
    private String title;
    private int limitMiles;
    private int limitMonths;

    public Category(String[] lineArray) throws ParseException {
        category = lineArray[0];
        title = lineArray[1];
        limitMiles = Integer.parseInt(lineArray[2]);
        limitMonths = Integer.parseInt(lineArray[3]);
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
}
