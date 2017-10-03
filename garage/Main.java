package garage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        logTable.setMinHeight(450);
        logTable.setMinWidth(800);
        logTable.setPadding(new Insets(0, 0, 5, 0));
        logTable.setStyle("-fx-alignment: CENTER;");
        /*
            Format and add columns to table
         */
        // Date
        TableColumn colDate = new TableColumn<Item, String>("Date");
        colDate.setCellValueFactory(new PropertyValueFactory<Item, String>("date"));
        colDate.setMinWidth(50);
        colDate.setStyle("-fx-alignment: CENTER;");
        logTable.getColumns().add(0, colDate);
        // Mileage
        TableColumn colMileage = new TableColumn<Item, String>("Mileage");
        colMileage.setCellValueFactory(new PropertyValueFactory<Item, String>("mileage"));
        colMileage.setMinWidth(30);
        colMileage.setStyle("-fx-alignment: CENTER;");
        logTable.getColumns().add(1, colMileage);
        // Item
        TableColumn colItem = new TableColumn<Item, String>("Item");
        colItem.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        colItem.setMinWidth(150.0);
        logTable.getColumns().add(2, colItem);
        // Company
        TableColumn colCompany = new TableColumn<Item, String>("Company");
        colCompany.setCellValueFactory(new PropertyValueFactory<Item, String>("company"));
        colCompany.setMinWidth(100);
        logTable.getColumns().add(3, colCompany);
        // Price
        TableColumn colPrice = new TableColumn<Item, String>("Price ($)");
        colPrice.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
        colPrice.setStyle("-fx-alignment: CENTER;");
        logTable.getColumns().add(4, colPrice);
        // Notes
        TableColumn colNotes = new TableColumn<Item, String>("Notes");
        colNotes.setCellValueFactory(new PropertyValueFactory<Item, String>("notes"));
        colNotes.setMinWidth(100);
        logTable.getColumns().add(5, colNotes);
        ObservableList<Item> fxlist = log.getData();
        logTable.getItems().addAll(fxlist);
        /*
            Format logAnchorPane and add logTable
         */
        logAnchorPane.setPadding(new Insets(40, 40, 40, 40));
        logAnchorPane.setStyle("-fx-alignment: CENTER;");
        logAnchorPane.getChildren().add(logTable);
        AnchorPane.setLeftAnchor(logTable, 0.0);
        AnchorPane.setRightAnchor(logTable, 0.0);
        logTab.setContent(logAnchorPane);
    }

    public Main() throws IOException {
    }


    public static void main(String[] args) {
        launch(args);
    }
}
