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

/** Creates a JavaFX driven GUI app that manages Parts and Products associated with those Parts.
 * JAVADOC FOLDER LOCATION: part-parrot/javadoc
 * FUTURE ENHANCEMENT: Given how much the Part and Product classes have in commons (nearly all of their class members,
 * including fields and methods), a superclass could easily be written for these classes that would greatly reduce
 * duplicate code and make it much easier to extend Part-Parrot to support a wide variety of item types other than
 * generic Parts and Products.
 * FUTURE ENHANCEMENT: Support for multiple Inventories (i.e., moving to an instance based Inventory implementation)d
 * and multi-window support which would allow system from multiple organizations or stores to be managed in tandem.
 */
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
