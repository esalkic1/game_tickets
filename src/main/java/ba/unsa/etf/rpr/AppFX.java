package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AppFX extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(AppFX.class.getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Fk Željezničar ulaznice");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

