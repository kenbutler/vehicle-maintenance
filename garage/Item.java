package garage;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Created by kenbutler on 9/26/17.
 */
public class Item extends GridPane {

    public Item(String title) {
        this.addColumn(0, new CustomLabel(title));
        this.addColumn(1, new CustomLabel("a"));
        this.addColumn(2, new CustomLabel("b"));
        this.addColumn(3, new CustomLabel("c"));
    }

    public class CustomLabel extends Label {
        public CustomLabel(String title) {
            this.setText(title);
            this.setId("item");
        }
    }
}
