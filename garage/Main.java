package garage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public GridPane overview = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception{

        initialize();

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Vehicle Garage");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
    }

    public void initialize() {
        initializeOverview();
    }

    public void initializeOverview() {
        // Add overview grid
        Label label1 = new Label("ABC");
        overview.add(label1,0,0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
