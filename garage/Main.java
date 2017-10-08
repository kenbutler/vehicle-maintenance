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

    public Overview overview = new Overview("overview.csv");
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

        //initialize();

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

    public void handleClick() {
        //log.loadData();
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
        overviewGrid.setMinHeight(450);
        overviewGrid.setMinWidth(800);
        overviewGrid.setPadding(new Insets(0, 0, 5, 0));
        overviewGrid.setGridLinesVisible(false);
        overviewGrid.setStyle("-fx-alignment: TOP-CENTER;");
        // Add column headers

        Label hdrTitle = new Label("Title");
        hdrTitle.setId("category");
        hdrTitle.setStyle("-fx-min-width: 300px;"); // Important width alteration for formatting
        overviewGrid.add(hdrTitle, 0, 1);
        Label hdrStatus = new Label("Status");
        hdrStatus.setId("header");
        overviewGrid.add(hdrStatus, 1, 0, 2, 1);
        Label hdrDate = new Label("Date");
        hdrDate.setId("category");
        overviewGrid.add(hdrDate, 1, 1);
        Label hdrMileage = new Label("Mileage");
        hdrMileage.setId("category");
        overviewGrid.add(hdrMileage, 2, 1);
        Label hdrLimits = new Label("Limits");
        hdrLimits.setId("header");
        overviewGrid.add(hdrLimits, 3, 0, 2, 1);
        Label hdrLimitsMonths = new Label("Months");
        hdrLimitsMonths.setId("category");
        overviewGrid.add(hdrLimitsMonths, 3, 1);
        Label hdrLimitsMiles = new Label("Miles");
        hdrLimitsMiles.setId("category");
        overviewGrid.add(hdrLimitsMiles, 4, 1);

        for (int i=0; i < overview.size(); i++) {
            Category tempObj = (Category) overview.get(i);
            GridPane tempGrid = tempObj.getCategoryGrid();
            overviewGrid.add(tempGrid, 0, 2 + i, 5, 1);
        }
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

    public Main() throws IOException {
    }


    public static void main(String[] args) {
        launch(args);
    }
}
