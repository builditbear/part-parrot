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

/** Describes methods common to all view controllers */
public class Controller {

    /** Loads and renders a Scene based on the given View and applies the given metadata.
     *
     * @param event An event from which the Stage can be derived. Any will do, but this is expected to be the event
     *              passed into the event handler which calls this method.
     * @param title The desired Scene title to be displayed in the title bar of the resulting window.
     * @param view The name of the fxml View file we wish to render as a Scene.
     * @param resolution A String describing the resolution to render the scene at, of the format "[int] x [int]".
     */
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

    /** Loads and renders the main Scene.
     *
     * @param stage The Stage we wish to render the main Scene upon.
     */
    public static void loadMainScene(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Utilities.class.getResource("/views/" + "MainScreen.fxml")));
        restoreExitBehavior(stage);
        Scene scene = new Scene(root, 940, 490);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    /** Validates whether an int can be parsed from the given String.
     *
     * @param s The String to be validated.
     * @return True if an int can be parsed from s, false otherwise.
     */
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

    /** Validates whether a double can be parsed from the given String.
     *
     * @param s The String to be validated.
     * @return True is a double can be parsed from s, false otherwise.
     */
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

    /** Alters the Stage's Close button (the X at the top right on Windows or top left on Mac) such that it brings
     *  the user back to the main Scene instead of shutting down the application.
     * @param stage The Stage whose Close button behavior is to be altered.
     */
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

    /** Restores the Stage's exit behavior to its default state, such that it once again close the application when
     *  clicked.
     *
     * @param stage The Stage whose Close button behavior is to be restored.
     */
    public static void restoreExitBehavior(Stage stage) {
        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
        });
    }
}
