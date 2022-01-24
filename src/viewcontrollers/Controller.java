package viewcontrollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Utilities;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    // Resolution must be passed in with format: int x int;
    public void loadScene(ActionEvent event, String title, String view, String resolution) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/" + view + ".fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        redirectExitToMainScreen(stage);
        String[] dimensions = resolution.split("x");
        Scene scene = new Scene(root, Integer.parseInt(dimensions[1]), Integer.parseInt(dimensions[0]));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void loadMainScene(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Utilities.class.getResource("/views/" + "MainScreen.fxml")));
        restoreExitBehavior(stage);
        Scene scene = new Scene(root, 940, 490);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    // Returns true if and only if the input string can be converted to a positive integer.
    public boolean validateIntInput(String s) {
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Field text isn't a valid integer.");
            return false;
        }
        if (i < 0) {
            System.out.println("Negative value are not allowed.");
            return false;
        } else {
            return true;
        }
    }

    public boolean validateDoubleInput(String s) {
        double i;
        try {
            i = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("Field text isn't a valid double.");
            return false;
        }
        if (i < 0) {
            System.out.println("Negative values are not allowed.");
            return false;
        } else {
            return true;
        }
    }

    public void redirectExitToMainScreen(Stage stage) {
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                loadMainScene(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void restoreExitBehavior(Stage stage) {
        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
        });
    }
}
