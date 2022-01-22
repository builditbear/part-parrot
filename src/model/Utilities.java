package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class Utilities {
    public static Comparator<DataModel.InventoryItem> getIdComparator() {
        return Comparator.comparingInt(item -> item.id);
    }

    public static Comparator<DataModel.InventoryItem> getNameComparator() {
        return Comparator.comparing(a -> a.name);
    }

    // Generates new parts with pseudorandom values and inserts them into the given list. It assumes that the given
    // list is empty. Half of the parts generated are InHouse, and half are Outsourced. Written for testing purposes.
    public static void generateParts(ObservableList<Part> partList, int qty) {
        String[] names = {"Jammenwerfer", "Schnozblonger", "Cortalorto", "Glorpius", "Shapram", "Koalong", "Muffintuppin"
                , "Buggaluggajoozjooz", "Evil Monkey Wrench", "Diamondium Hammerus", "Magic Wand", "Muggle Wand", "Glip-glop"};
        if(qty <= names.length) {
            for(int i = 0; i < qty / 2; i++) {
                partList.add(new DataModel.InHouse(0, names[i], randomInt(100), randomInt(10), 1, 100, randomInt(10000)));
            }
            for(int i = qty / 2; i < qty; i++){
                partList.add(new DataModel.Outsourced(0, names[i], randomInt(100), randomInt(10), 1, 100, names[i] + " Inc."));
            }
        }
    }

    public static void generateProducts() {
        DataModel.Inventory.addProduct(new DataModel.Product(1, "Alpha",30.50, 10, 1, 100,
                FXCollections.observableArrayList()));
        DataModel.Inventory.addProduct(new DataModel.Product(1, "Beta",30.50, 10, 1, 100,
                FXCollections.observableArrayList()));
        DataModel.Inventory.addProduct(new DataModel.Product(1, "Gamma",10.75, 50, 1, 100,
                FXCollections.observableArrayList()));
        DataModel.Inventory.addProduct(new DataModel.Product(1, "Omega",100.54, 5, 1, 100,
                FXCollections.observableArrayList()));
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
