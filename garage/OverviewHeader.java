package garage;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Created by kenbutler on 10/10/17.
 */
public class OverviewHeader {

    private GridPane hdrGrid = new GridPane();
    private Label hdrTitle = new Label("Title");
    private Label hdrStatus = new Label("Status");
    private Label hdrDate = new Label("Date");
    private Label hdrMileage = new Label("Mileage");
    private Label hdrLimits = new Label("Limits");
    private Label hdrLimitsMonths = new Label("Months");
    private Label hdrLimitsMiles = new Label("Miles");

    public OverviewHeader() {

        hdrTitle.setId("category");
        hdrTitle.setStyle("-fx-min-width: 300px;"); // Important width alteration for formatting
        hdrStatus.setId("header");
        hdrDate.setId("category");
        hdrMileage.setId("category");
        hdrLimits.setId("header");
        hdrLimitsMonths.setId("category");
        hdrLimitsMiles.setId("category");
        // Add them all together
        addTogether();
    }

    private void addTogether() { // Important width alteration for formatting
        hdrGrid.add(hdrTitle, 0, 1);
        hdrGrid.add(hdrStatus, 1, 0, 2, 1);
        hdrGrid.add(hdrDate, 1, 1);
        hdrGrid.add(hdrMileage, 2, 1);
        hdrGrid.add(hdrLimits, 3, 0, 2, 1);
        hdrGrid.add(hdrLimitsMonths, 3, 1);
        hdrGrid.add(hdrLimitsMiles, 4, 1);
    }

    public GridPane getHdrGrid() {
        return this.hdrGrid;
    }
}
