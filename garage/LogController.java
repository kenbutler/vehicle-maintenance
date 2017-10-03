package garage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogController {

    @FXML
    public TableView<Item> logTable;

    public void handleClick(MouseEvent event) {
        System.out.println("Log controller encountered click.");
    }

    public void loadData(Log log) {
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < log.size(); i++) {
            Item temp = (Item) log.get(i);
            list.add(temp);
        }
        ObservableList<Item> fxlist = FXCollections.observableList(list);
        logTable.getItems().addAll(fxlist);
    }
}
