// Bugs/Problems encountered (and their resolutions):
// 1. inventorySearch not accepting ObservableList<Part> for ObservableList<InventoryItem> parameter - had to use <? extends InventoryItem> to make it work.
// 2. (lesson) Learned that I can replace very verbose functions with lambda expressions - anonymous methods with implied types, in many case. Particularly, for comparator definitions.
// 3. Was unable to instantiate ObservableList because it is abstract. Used factory method per stack overflow.
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;

public class DataModel {
    public class Inventory {
        private static ObservableList<Part> allParts;
        private static ObservableList<Product> allProducts;

        // User-friendly way to search by ID: Allows users to only provide two parameters instead of manually entering the bounds of the list.
        public static InventoryItem inventorySearch(ObservableList<? extends InventoryItem> list, int itemId){
            return inventorySearch(list, 0, list.size() - 1, itemId);
        }

        // Binary search algorithm for use with InventoryItem lists sorted by ID.
        private static InventoryItem inventorySearch(ObservableList<? extends InventoryItem> list, int l, int r, int itemId) {
            if(r >= l) {
                int midpoint = (r - l) / 2;
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

        public void addPart(Part newPart) {
            allParts.add(newPart);
        }

        public void addProduct(Product newProduct) {
            allProducts.add(newProduct);
        }

        // Inventory Item lookups will return null if the item in question is not found.
        public Part lookupPart(int partId) {
            allParts.sort(InventoryItem.getIdComparator());
            return (Part) inventorySearch(getAllParts(), partId);
        }

        public Product lookupProduct(int productId) {
            allProducts.sort(InventoryItem.getIdComparator());
            return (Product) inventorySearch(getAllProducts(), productId);
        }

        public ObservableList<Part> lookupPart(String partName) {
            ObservableList<Part> matches = FXCollections.observableArrayList();
            for(part : getAllParts()) {

            }
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
            return allParts;
        }

        public ObservableList<Product> getAllProducts() {
            return allProducts;
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
            return Comparator.comparingInt(item -> item.id);
        }

        public static Comparator<InventoryItem> getNameComparator() {
            return Comparator.comparing(a -> a.name);
        }
    }

    public abstract class Part extends InventoryItem{
        public Part(int id, String name, double price, int stock, int min, int max) {
            super(id, name, price, stock, min, max);
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
