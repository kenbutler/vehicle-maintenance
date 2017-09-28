package garage;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LogController {

    public LogController() {
        System.out.println("**** Initializing Log Controller ****");
    }

    public void handleClick(MouseEvent event) {
        System.out.println("Log controller encountered click.");
    }
}
