package garage;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {

    Map<String, Integer> map = new HashMap<>();

    public Label inspect;
    public Label replaceWipers;
    public GridPane grid;

    public void initMap() {
        map.put("Inspect and Lubricate", 3);
        map.put("Replace Windshield Wipers", 4);
    }

    public Controller() {
        initMap();
    }

    public void handleClick(MouseEvent event) {
        Label label = (Label) event.getSource();
        System.out.format("Label clicked was %s, %d\n", label.getText(), label.getLabelFor());
        int row = map.get(label.getText());
        grid.
    }

    public void setData(Overview.Item item) {

    }
}
