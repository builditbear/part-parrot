// Bugs encountered (and their resolutions):
//

import javafx.collections.ObservableList;
import java.util.Comparator;

public class DataModel {
    public class Inventory {
        private static ObservableList<Part> allParts;
        private static ObservableList<Product> allProducts;

        // Noted that the UML spec does not appear to call for a constructor? Will have to confirm whether or not
        // I'm even allowed to add a constructor (instantiating the Inventory makes sense to me).
//        public Inventory(ObservableList<Part> allParts, ObservableList<Product> allProducts) {
//            this.allParts = allParts;
//            this.allProducts = allProducts;
//        }

        public void addPart(Part newPart) {
            allParts.add(newPart);
        }

        public void addProduct(Product newProduct) {
            allProducts.add(newProduct);
        }

        public Part lookupPart(int partId) {

        }

        public Product lookupProduct(int productId) {
        }

        public ObservableList<Part> lookupPart(String partName) {
        }

        public ObservableList<Product> lookupProduct(String productName) {
        }

        public void updatePart(int index, Product newProduct) {
        }

        public boolean deletePart(Part selectedPart) {
        }

        public boolean deleteProduct(Product selectedPart) {
        }

        public ObservableList<Part> getAllParts() {
            return this.allParts;
        }

        public ObservableList<Product> getAllProducts() {
            return this.allProducts;
        }

    }

    public abstract class Part{
        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

        public Part(int id, String name, double price, int stock, int min, int max) {
            this.id = id;
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
    }

    public class InHouse extends Part {

        private int machineId;

        public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
            super(id, name, price, stock, min, max);
            this.machineId = machineId;
        }

        public int getMachineId(){
            return this.machineId;
        }

        public void setMachineId(int machineId){
            this.machineId = machineId;
        }
    }

    public class Outsourced extends Part {
        private String companyName;

        public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
            super(id, name, price, stock, min, max);
            this.companyName = companyName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }

    // Adding this superclass for both Part and Product recognizing that Product's fields are a superset of Part's fields. Additionally, while writing a comparator to sort
    // ObservableLists of both Parts and Products by their id fields, I realized that the comparator was also something both classes had in common. Just trying to keep it DRY!
    public abstract class InventoryItem {
        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

        public InventoryItem(int id, String name, double price, int stock, int min, int max) {
            this.id = id;
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
            return Comparator.comparingInt(partA -> partA.id);
        }
    }

    public class Product extends InventoryItem {
        private final ObservableList<Part> associatedParts;

        public Product(int id, String name, double price, int stock, int min, int max, ObservableList<Part> associatedParts) {
            super(id, name, price, stock, min, max);
            this.associatedParts = associatedParts;
        }

        public void addAssociatedPart(Part part) {
            this.associatedParts.add(part);
        }

        public boolean deletedAssociatedPart(Part selectedAssociatedPart) {
            this.associatedParts.remove(selectedAssociatedPart);
        }

        public ObservableList<Part> getAllAssociatedParts() {
            return this.associatedParts;
        }
    }
}
