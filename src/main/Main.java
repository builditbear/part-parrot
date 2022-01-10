package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.crypto.Data;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
        stage.setTitle("main.Main Screen");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();

        //Test code for main.DataModel.
//        ObservableList<DataModel.Part> parts = DataModel.Inventory.getAllParts();
//        DataModel.generateParts(parts, 12);
//        for(DataModel.Part p : DataModel.Inventory.getAllParts()) {
//            System.out.println(p.getName());
//        }
    }
}
