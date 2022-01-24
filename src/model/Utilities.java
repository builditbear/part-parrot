package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

public class Utilities {
    // Part IDs are even, and Product IDs are odd.
    private static int partCounter = 0;
    private static int productCounter = 1;

    public static Comparator<Part> getPartComparator() {
        return Comparator.comparingInt(Part::getId);
    }

    public static Comparator<Part> getPartNameComparator() {
        return Comparator.comparing(Part::getName);
    }

    public static Comparator<Product> getProductComparator() {
        return Comparator.comparingInt(Product::getId);
    }

    public static Comparator<Product> getProductNameComparator() {
        return Comparator.comparing(Product::getName);
    }


    // Generates new parts with pseudorandom values and inserts them into the given list. It assumes that the given
    // list is empty. Half of the parts generated are InHouse, and half are Outsourced. Written for testing purposes.
    public static void generateParts(ObservableList<Part> partList, int qty) {
        String[] names = {"Jammenwerfer", "Schnozblonger", "Cortalorto", "Glorpius", "Shapram", "Koalong", "Muffintuppin"
                , "Buggaluggajoozjooz", "Evil Monkey Wrench", "Diamondium Hammerus", "Magic Wand", "Muggle Wand", "Glip-glop"};
        if(qty <= names.length) {
            for(int i = 0; i < qty / 2; i++) {
                partList.add(new InHouse(generateId(0), names[i], randomInt(100), randomInt(10), 1, 100, randomInt(10000)));
            }
            for(int i = qty / 2; i < qty; i++){
                partList.add(new Outsourced(generateId(0), names[i], randomInt(100), randomInt(10), 1, 100, names[i] + " Inc."));
            }
        }
    }

    public static void generateProducts() {
        Inventory.addProduct(new Product(FXCollections.observableArrayList(), generateId(1), "Alpha",30.50, 10, 1, 100));
        Inventory.addProduct(new Product(FXCollections.observableArrayList(), generateId(1), "Beta",30.50, 10, 1, 100));
        Inventory.addProduct(new Product(FXCollections.observableArrayList(), generateId(1),  "Gamma",10.75, 50, 1, 100));
        Inventory.addProduct(new Product(FXCollections.observableArrayList(), generateId(1), "Omega",100.54, 5, 1, 100));
    }

    public static int randomInt(int max) {
        return (int) (Math.floor(Math.random() * max + 1));
    }

    // Returns partId if idType == 0 and productId if idType == 1
    public static int generateId(int idType) {
        int id;
        if(idType == 0) {
            id = partCounter;
            partCounter += 2;
            return id;
        } else if(idType == 1) {
            id = productCounter;
            productCounter += 2;
            return id;
        } else {
            // If this branch is reached, and invalid type was passed in.
            return -1;
        }
    }
}
