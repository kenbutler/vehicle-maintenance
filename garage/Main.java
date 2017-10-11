package garage;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class Main extends Application {

    public Overview overview = new Overview("overview.csv", "current.csv");
    public Log log = new Log();

    private TabPane tabPane = new TabPane();
    private Tab overviewTab = new Tab("Overview");
    private AnchorPane overviewPane = new AnchorPane();
    private GridPane overviewGrid = new GridPane();
    private Tab logTab = new Tab("Log");
    private AnchorPane logAnchorPane = new AnchorPane();
    private TableView<Item> logTable = new TableView<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        StackPane root = new StackPane();
        addElements(root);
        //
        initialize();
        //
        primaryStage.setTitle("Vehicle Garage");
        Scene scene = new Scene(root, 1200, 500);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initialize() throws IOException, ParseException {
        overview.analyzeLog(log);
    }

    private void addElements(StackPane root) {
        addOverviewElements();
        addLogElements();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().add(overviewTab);
        tabPane.getTabs().add(logTab);
        root.getChildren().add(tabPane);
    }

    private void addOverviewElements() {

        // Overview GridPane
        overviewGrid.setMinHeight(450);
        overviewGrid.setMinWidth(800);
        overviewGrid.setPadding(new Insets(0, 0, 5, 0));
        overviewGrid.setGridLinesVisible(false);
        overviewGrid.setStyle("-fx-alignment: TOP-CENTER;");

        // Add current state pane
        overviewGrid.add(overview.getCurrentDisplay(), 0, 0);

        // Add Overview header GridPane
        OverviewHeader overviewHeader = new OverviewHeader();
        GridPane hdrGrid = overviewHeader.getHdrGrid();
        overviewGrid.add(hdrGrid, 0, 1);

        for (int i=0; i < overview.size(); i++) {
            Category tempObj = (Category) overview.get(i);
            GridPane tempGrid = tempObj.getCategoryGrid();
            overviewGrid.add(tempGrid, 0, 2 + i, 5, 1);
        }
        // Add Overview GridPane to Overview Pane
        overviewPane.getChildren().addAll(overviewGrid);
        AnchorPane.setLeftAnchor(overviewGrid, 0.0);
        AnchorPane.setRightAnchor(overviewGrid, 0.0);
        overviewTab.setContent(overviewPane);
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

    public Main() throws IOException, ParseException {
    }


    public static void main(String[] args) {
        launch(args);
    }
}
