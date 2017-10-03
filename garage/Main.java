package garage;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class Main extends Application {

    public Overview overview = new Overview("overview.csv");
    public Log log = new Log();

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane p = fxmlLoader.load(getClass().getResource("log.fxml").openStream());
        LogController logCtrl = (LogController) fxmlLoader.getController();
        logCtrl.loadData(log);

        initialize();

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        //StackPane root = new StackPane();
        //addElements(root);
        primaryStage.setTitle("Vehicle Garage");
        primaryStage.setScene(new Scene(root, 1200, 500));
        primaryStage.show();
    }

    private void initialize() throws IOException, ParseException {
        overview.analyzeLog(log);
        System.out.println("End of main initialize");
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
