// 1. inventorySearch not accepting ObservableList<Part> for ObservableList<InventoryItem> parameter - had to use <? extends InventoryItem> to make it work.
// 2. (lesson) Learned that I can replace very verbose functions with lambda expressions - anonymous methods with implied types, in many case. Particularly, for comparator definitions.
// 3. Was unable to instantiate ObservableList because it is abstract. Used factory method per stack overflow.
package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import java.io.IOException;

import static model.Utilities.*;
import static viewcontrollers.Controller.loadMainScene;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        loadMainScene(stage);
        //Test code for model.DataModel.
        ObservableList<Part> parts = Inventory.getAllParts();
        generateParts(parts, 12);
        // Dummy product data for productTable testing.
        generateProducts();
    }
}
