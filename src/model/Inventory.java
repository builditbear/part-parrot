package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static model.Utilities.getIdComparator;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // User-friendly way to search by ID: Allows users to only provide two parameters instead of manually entering the bounds of the list.
    public static Part partSearch(ObservableList<Part> list, int itemId){
        return partSearch(list, 0, list.size() - 1, itemId);
    }

    // Binary search algorithm for use with Part lists sorted by ID.
    private static Part partSearch(ObservableList<Part> list, int l, int r, int itemId) {
        if(r >= l) {
            int midpoint = l + (r - l) / 2;
            Part middlePart = list.get(midpoint);

            if(middlePart.getId() == itemId) {
                return middlePart;
            }
            if(middlePart.getId() > itemId) {
                // Search lower half.
                return partSearch(list, l, midpoint - 1, itemId);
            }
            return partSearch(list, midpoint + 1, r, itemId);
        }
        return null;
    }

    public static Product productSearch(ObservableList<Product> list, int itemId){
        return productSearch(list, 0, list.size() - 1, itemId);
    }

    // Binary search algorithm for use with Part lists sorted by ID.
    private static Product productSearch(ObservableList<Product> list, int l, int r, int itemId) {
        if(r >= l) {
            int midpoint = l + (r - l) / 2;
            Product middleProduct = list.get(midpoint);

            if(middleProduct.getId() == itemId) {
                return middleProduct;
            }
            if(middleProduct.getId() > itemId) {
                // Search lower half.
                return productSearch(list, l, midpoint - 1, itemId);
            }
            return productSearch(list, midpoint + 1, r, itemId);
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
        allParts.sort(getIdComparator());
        return partSearch(getAllParts(), partId);
    }

    public static Product lookupProduct(int productId) {
        allProducts.sort(getIdComparator());
        return (Product) partSearch(getAllProducts(), productId);
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