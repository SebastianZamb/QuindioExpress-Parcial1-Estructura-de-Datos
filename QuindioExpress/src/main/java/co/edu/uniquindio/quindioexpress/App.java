package co.edu.uniquindio.quindioexpress;

import co.edu.uniquindio.quindioexpress.Controller.PackageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public PackageController packageController;

        @Override
        public void start(Stage stage) throws Exception {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("startView.fxml")
            );

            Scene scene = new Scene(loader.load());

            stage.setTitle("Quindio Express");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }




