package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
//        stage.setTitle("main.Main Screen");
//        stage.setScene(new Scene(root, 900, 500));
//        stage.show();
        loadScene(stage, "Main Screen", "MainScreen", "490x940");


        //Test code for main.DataModel.
        ObservableList<DataModel.Part> parts = DataModel.Inventory.getAllParts();
        DataModel.generateParts(parts, 12);
//        for(DataModel.Part p : DataModel.Inventory.getAllParts()) {
//            System.out.println(p.getName());
//        }
        System.out.println(DataModel.Inventory.lookupPart(2));
    }

    private void loadScene(Stage stage, String title, String view, String resolution) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/" + view + ".fxml"));
        String[] dimensions = resolution.split("x");
        Scene scene = new Scene(root, Integer.parseInt(dimensions[1]), Integer.parseInt(dimensions[0]));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
