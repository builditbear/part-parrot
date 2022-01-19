package viewcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{

    // Resolution must be passed in with format: int x int;
    public void loadScene(ActionEvent event, String title, String view, String resolution) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/" + view + ".fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String[] dimensions = resolution.split("x");
        Scene scene = new Scene(root, Integer.parseInt(dimensions[1]), Integer.parseInt(dimensions[0]));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
