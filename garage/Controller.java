package garage;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {

    Map<String, Integer> map = new HashMap<>();

    public Label inspect;
    public Label replaceWipers;
    public GridPane myGrid;

    @FXML
    private GridPane tireRotation;

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void initMap() {
        // General
        map.put("Inspect and Lubricate", 0);
        map.put("Replace Windshield Wipers", 1);
        // Fluids
        map.put("Oil and Filter Change", 2);
        map.put("Replace CVT Fluid", 4);
        // Tires and Brakes
        map.put("Tire Rotation", 7);
        map.put("New Tires", 6);
        map.put("Brake Fluid Change", 3);
        map.put("New Brakes", 5);
    }

    /*
    public void initGrid() {
        System.out.format("map size=%d\n", map.size());
        for (int i=2; i < 2 + map.size(); i++) { // rows
            for (int j = 1; j < 1 + 4; j++) { // columns
                System.out.print("AYYYYYYYYY\n");
                Label label = new Label();
                label.setId("data");
                myGrid.add(label, i, j);
            }
        }
    }*/

    public Controller() {
        initMap();
        System.out.print("**** INITIALIZING CONTROLLER ****\n");
        //initGrid();
    }

    public void handleClick(MouseEvent event) {
        Label label = (Label) event.getSource();
        GridPane grid = (GridPane) event.getSource();
        //System.out.format("Grid row clicked was %d", grid.getChildren().)
        int row = map.get(label.getText());
        System.out.format("Label clicked was (%d) %s\n", label.getText(), row);
        //grid.getChildren().

    }
}
