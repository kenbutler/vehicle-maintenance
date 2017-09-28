package garage;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class OverviewController {

    Map<String, Integer> map = new HashMap<>();

    @FXML
    public GridPane tireRotation;

    public OverviewController() {
        System.out.print("**** Initializing Overview Controller ****\n");
    }

    @FXML
    public void handleClick(MouseEvent event) {
        Label label = (Label) event.getSource();
        int row = map.get(label.getText());
        System.out.format("Label clicked was (%d) %s\n", label.getText(), row);

    }
}
