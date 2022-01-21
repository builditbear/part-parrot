package main;
// Bugs/Problems encountered (and their resolutions):
// 1. inventorySearch not accepting ObservableList<Part> for ObservableList<InventoryItem> parameter - had to use <? extends InventoryItem> to make it work.
// 2. (lesson) Learned that I can replace very verbose functions with lambda expressions - anonymous methods with implied types, in many case. Particularly, for comparator definitions.
// 3. Was unable to instantiate ObservableList because it is abstract. Used factory method per stack overflow.

// Future Improvement Suggestions:
//
//

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;

public class DataModel {

    // Part ID's are even, and Product ID's are odd.
    private static int partCounter = 0;
    private static int productCounter = 1;

    public static class Inventory {
        private static ObservableList<Part> allParts = FXCollections.observableArrayList();
        private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

        // User-friendly way to search by ID: Allows users to only provide two parameters instead of manually entering the bounds of the list.
        public static InventoryItem inventorySearch(ObservableList<? extends InventoryItem> list, int itemId){
            return inventorySearch(list, 0, list.size() - 1, itemId);
        }

        // Binary search algorithm for use with InventoryItem lists sorted by ID.
        private static InventoryItem inventorySearch(ObservableList<? extends InventoryItem> list, int l, int r, int itemId) {
            if(r >= l) {
                int midpoint = l + (r - l) / 2;
                InventoryItem middleItem = list.get(midpoint);

                if(middleItem.id == itemId) {
                    return middleItem;
                }
                if(middleItem.id > itemId) {
                    // Search lower half.
                    return inventorySearch(list, l, midpoint - 1, itemId);
                }
                return inventorySearch(list, midpoint + 1, r, itemId);
            }
            return null;
        }

        public static void addPart(Part newPart) {
            allParts.add(newPart);
        }

        public static void addProduct(Product newProduct) {
            getAllProducts().add(newProduct);
        }

        // Inventory Item lookups will return null if the item in question is not found.
        public static Part lookupPart(int partId) {
            allParts.sort(InventoryItem.getIdComparator());
            return (Part) inventorySearch(getAllParts(), partId);
        }

        public static Product lookupProduct(int productId) {
            allProducts.sort(InventoryItem.getIdComparator());
            return (Product) inventorySearch(getAllProducts(), productId);
        }

        public static ObservableList<Part> lookupPart(String partName) {
            ObservableList<Part> matches = FXCollections.observableArrayList();
            for(Part part : getAllParts()) {
                if(part.getName().toLowerCase().contains(partName)) {
                    matches.add(part);
                }
            }
            return matches;
        }

        public static ObservableList<Product> lookupProduct(String productName) {
            ObservableList<Product> matches = FXCollections.observableArrayList();
            for(Product product : getAllProducts()) {
                if(product.getName().toLowerCase().contains(productName)) {
                    matches.add(product);
                }
            }
            return matches;
        }

        public static void updatePart(int index, Part selectedPart) {
            getAllParts().set(index, selectedPart);
        }

        public static void updateProduct(int index, Product newProduct){
            getAllProducts().set(index, newProduct);
        }

        public static boolean deletePart(Part selectedPart) {
            return getAllParts().remove(selectedPart);
        }

        public static boolean deleteProduct(Product selectedPart) {
            return getAllProducts().remove(selectedPart);
        }

        public static ObservableList<Part> getAllParts() {
            return allParts;
        }

        public static ObservableList<Product> getAllProducts() {
            return allProducts;
        }

    }

    // Adding this superclass for both Part and Product recognizing that Product's fields are a superset of Part's fields. Additionally, while writing a comparator to sort
    // ObservableLists of both Parts and Products by their id fields, I realized that the comparator was also something both classes had in common. Just trying to keep it DRY!
    public static class InventoryItem {
        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

        public InventoryItem(int idType, String name, double price, int stock, int min, int max) {
            this.id = generateId(idType);
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.min = min;
            this.max = max;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public static Comparator<InventoryItem> getIdComparator() {
            return Comparator.comparingInt(item -> item.id);
        }

        public static Comparator<InventoryItem> getNameComparator() {
            return Comparator.comparing(a -> a.name);
        }
    }

    public static abstract class Part extends InventoryItem{
        public Part(int idType, String name, double price, int stock, int min, int max) {
            super(idType, name, price, stock, min, max);
        }

        public String getTypeSpecificField() {
            return "Generic Part";
        }
    }

    public static class InHouse extends Part {

        private int machineId;

        public InHouse(int idType, String name, double price, int stock, int min, int max, int machineId) {
            super(idType, name, price, stock, min, max);
            this.machineId = machineId;
        }

        public int getMachineId(){
            return this.machineId;
        }

        public void setMachineId(int machineId){
            this.machineId = machineId;
        }

        @Override
        public String getTypeSpecificField() {
            return Integer.toString(this.machineId);
        }
    }

    public static class Outsourced extends Part {
        private String companyName;

        public Outsourced(int idType, String name, double price, int stock, int min, int max, String companyName) {
            super(idType, name, price, stock, min, max);
            this.companyName = companyName;
        }

        public String getCompanyName() {
            return this.companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        @Override
        public String getTypeSpecificField() {
            return this.companyName;
        }
    }

    public static class Product extends InventoryItem {
        private final ObservableList<Part> associatedParts;

        public Product(int idType, String name, double price, int stock, int min, int max, ObservableList<Part> associatedParts) {
            super(idType, name, price, stock, min, max);
            this.associatedParts = associatedParts;
        }

        public void addAssociatedPart(Part part) {
            this.associatedParts.add(part);
        }

        public boolean deletedAssociatedPart(Part selectedAssociatedPart) {
            return this.associatedParts.remove(selectedAssociatedPart);
        }

        public ObservableList<Part> getAllAssociatedParts() {
            return this.associatedParts;
        }

        public void printAssociatedParts() {
            if(!getAllAssociatedParts().isEmpty()) {
                for (Part part : this.getAllAssociatedParts()) {
                    System.out.println(part.getName());
                }
                System.out.println();
            }
        }
    }

    // Generates new parts with pseudorandom values and inserts them into the given list. It assumes that the given
    // list is empty. Half of the parts generated are InHouse, and half are Outsourced. Written for testing purposes.
    public static void generateParts(ObservableList<Part> partList, int qty) {
        String[] names = {"Jammenwerfer", "Schnozblonger", "Cortalorto", "Glorpius", "Shapram", "Koalong", "Muffintuppin"
        , "Buggaluggajoozjooz", "Evil Monkey Wrench", "Diamondium Hammerus", "Magic Wand", "Muggle Wand", "Glip-glop"};
        if(qty <= names.length) {
            for(int i = 0; i < qty / 2; i++) {
                partList.add(new InHouse(0, names[i], randomInt(100), randomInt(10), 1, 100, randomInt(10000)));
            }
            for(int i = qty / 2; i < qty; i++){
                partList.add(new Outsourced(0, names[i], randomInt(100), randomInt(10), 1, 100, names[i] + " Inc."));
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
