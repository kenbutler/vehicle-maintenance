package garage;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class Main extends Application {

    public Overview overview = new Overview();
    public Data data = new Data();

    @Override
    public void start(Stage primaryStage) throws Exception{

        initialize();

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        //StackPane root = new StackPane();
        //addElements(root);
        primaryStage.setTitle("Vehicle Garage");
        primaryStage.setScene(new Scene(root, 1200, 500));
        primaryStage.show();
    }

    private void initialize() throws IOException, ParseException {
        initializeOverview();
        initializeLogs(); // TODO
    }

    private void initializeOverview() throws IOException, ParseException {
        // Add overview grid
        //overview = new Overview();
    }

    private void initializeLogs() throws IOException {
        // Add log table of maintenance history for vehicle
        // TODO
    }

    /*
    private void addElements(Parent root) {
        root.getChildren().add(overview);
    }
    */

    public Main() throws IOException {
    }


    public static void main(String[] args) {
        launch(args);
    }
}
