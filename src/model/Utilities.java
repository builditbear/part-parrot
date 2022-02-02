package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Comparator;

/** Hosts miscellaneous methods for generating test data, comparing Parts and Products, and passing data between views. */
public class Utilities {
    // Part IDs are even, and Product IDs are odd.
    private static int partCounter = 0;
    private static int productCounter = 1;
    private static int machineIdCounter = 100000;

    /** Creates a comparator capable of comparing Parts on the basis of their Part IDs.
     *
     * @return A comparator based on Part ID values.
     */
    public static Comparator<Part> getPartComparator() {
        return Comparator.comparingInt(Part::getId);
    }

    /** Creates a comparator capable of comparing Parts on the basis of their names.
     *
     * @return A comparator based on Part names.
     */
    public static Comparator<Part> getPartNameComparator() {
        return Comparator.comparing(Part::getName);
    }

    /** Creates a comparator capable of comparing Products on the basis of their Product IDs.
     *
     * @return A comparator based on Product IDs.
     */
    public static Comparator<Product> getProductComparator() {
        return Comparator.comparingInt(Product::getId);
    }

    /** Creates a comparator capable of comparing Prodcuts on the basis of their names.
     *
     * @return A comparator based on Product names.
     */
    public static Comparator<Product> getProductNameComparator() {
        return Comparator.comparing(Product::getName);
    }


    /** Generates new parts with pseudorandom values and inserts them into the given list.
     * It assumes that the given list is empty. Half of the parts generated are InHouse,
     * and half are Outsourced. Written for testing purposes.
     *
     * @param partList An empty list of Parts.
     * @param qty The number of Parts desired to be generated.
     */
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

    /** Generates new Products with manually chosen characteristics. Written for testing. */
    public static void generateProducts() {
        Inventory.addProduct(new Product(FXCollections.observableArrayList(), generateId(1), "Alpha",30.50, 10, 1, 100));
        Inventory.addProduct(new Product(FXCollections.observableArrayList(), generateId(1), "Beta",30.50, 10, 1, 100));
        Inventory.addProduct(new Product(FXCollections.observableArrayList(), generateId(1),  "Gamma",10.75, 50, 1, 100));
        Inventory.addProduct(new Product(FXCollections.observableArrayList(), generateId(1), "Omega",100.54, 5, 1, 100));
    }

    /** Generates a random int.
     *
     * @param max The maximum allowed value of the random int.
     * @return The generated random int.
     */
    public static int randomInt(int max) {
        return (int) (Math.floor(Math.random() * max + 1));
    }

    /** Returns partId if idType == 0, productId if idType == 1, and machineId if idType == 2;
     *
     * @param idType An integer corresponding to the desired type of ID as listed above.
     * @return The generated ID.
     */
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
        } else if(idType == 2) {
            id = machineIdCounter++;
            return id;
        }
        else {
            // If this branch is reached, and invalid type was passed in.
            return -1;
        }
    }

    /** Populates the given TableView with the given Part property columns. */
    public static void populatePartsTable(ObservableList<Part> list,  TableView<Part> table,
                                   TableColumn<Part, Integer> id, TableColumn<Part, String> name,
                                   TableColumn<Part, Integer> stock, TableColumn<Part, Double> price) {
        table.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** Populates the given TableView with the given Product property columns. */
    public static void populateProductTable(ObservableList<Product> list, TableView<Product> table,
                                     TableColumn<Product, Integer> id, TableColumn<Product, String> name,
                                     TableColumn<Product, Integer> stock, TableColumn<Product, Double> price) {
        table.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
