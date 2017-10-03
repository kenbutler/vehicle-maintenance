package garage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public Overview overview = new Overview("overview.csv");
    public Log log = new Log();

    private TabPane tabPane = new TabPane();
    private Tab overviewTab = new Tab("Overview");
    private Tab logTab = new Tab("Log");
    private AnchorPane logAnchorPane = new AnchorPane();
    private TableView<Item> logTable = new TableView<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        initialize();

        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        StackPane root = new StackPane();
        addElements(root);
        primaryStage.setTitle("Vehicle Garage");
        primaryStage.setScene(new Scene(root, 1200, 500));
        primaryStage.show();
    }

    private void initialize() throws IOException, ParseException {
        overview.analyzeLog(log);
        System.out.println("End of main initialize");
    }

    public void handleClick() {
        //log.loadData();
    }

    private void addElements(StackPane root) {
        addLogElements();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().add(overviewTab);
        tabPane.getTabs().add(logTab);
        root.getChildren().add(tabPane);
    }

    private void addLogElements () {
        logTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        logTable.setPrefHeight(175);
        logTable.setPrefHeight(200);
        logTable.getColumns().add(0, new TableColumn<Item, String>(""));
        logTable.getColumns().add(1, new TableColumn<Item, String>("Date"));
        logTable.getColumns().add(2, new TableColumn<Item, String>("Mileage"));
        logTable.getColumns().add(3, new TableColumn<Item, String>("Item"));
        logTable.getColumns().add(4, new TableColumn<Item, String>("Company"));
        logTable.getColumns().add(5, new TableColumn<Item, String>("Price"));
        logTable.getColumns().add(6, new TableColumn<Item, String>("Notes"));
        ObservableList<Item> fxlist = log.getData();
        logTable.getItems().addAll(fxlist);
        logAnchorPane.setPadding(new Insets(40, 40, 40, 40));
        logAnchorPane.getChildren().add(logTable);
        logTab.setContent(logAnchorPane);
    }

    public Main() throws IOException {
    }


    public static void main(String[] args) {
        launch(args);
    }
}
