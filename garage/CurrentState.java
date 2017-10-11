package garage;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.beans.EventHandler;
import java.text.ParseException;

/**
 * Created by kenbutler on 10/11/17.
 */
public class CurrentState extends State {

    Label lbl = new Label();
    Button btnUpdate = new Button("Update");

    public CurrentState() throws ParseException {
        lbl.setText("Current mileage: " + this.getMileageString() + " miles, as of " + this.getDate());

        btnUpdate.setOnAction((ActionEvent event) -> {
            updatePanel();
        });
    }

    // GridPane package, mainly for
    public Label getStateLabel() {
        Label lbl = new Label();
        lbl.setText("Current state: " + this.getMileageString() + " miles as of " + this.getDate());
        lbl.setPadding(new Insets(20, 20, 20, 20));
        lbl.setAlignment(Pos.BOTTOM_CENTER);
        lbl.setMinWidth(800);
        lbl.setId("stateLbl");
        return lbl;
    }

    public AnchorPane getGridPane() {
        // Anchor Pane
        AnchorPane tmpPane = new AnchorPane();
        tmpPane.setMinWidth(800);
        // Grid Pane
        GridPane tmpGrid = new GridPane();
        tmpGrid.setAlignment(Pos.CENTER);
        tmpGrid.add(this.getStateLabel(), 0, 0);
        tmpGrid.add(btnUpdate, 1, 0);
        // Add grid pane to anchor pane
        tmpPane.getChildren().add(tmpGrid);
        AnchorPane.setLeftAnchor(tmpGrid, 0.0);
        AnchorPane.setRightAnchor(tmpGrid, 0.0);

        return tmpPane;
    }

    public void updatePanel() {
        System.out.println("testing update button!");
    }
}
