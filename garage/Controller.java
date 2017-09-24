package garage;

import javafx.scene.control.Label;

public class Controller {

    public Label inspect;
    public Label inspectDate;
    public Label inspectMiles;
    public Label inspectLimitMonths;
    public Label inspectLimitMiles;

    public Label replaceWipers;
    public Label replaceWipersDate;
    public Label replaceWipersMiles;
    public Label replaceWipersLimitMonths;
    public Label replaceWipersLimitMiles;

    public void handleClick() {
        inspectDate.setText("abc");
        inspectMiles.setText("123");
    }
}
