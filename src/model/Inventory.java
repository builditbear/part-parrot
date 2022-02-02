package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static model.Utilities.getPartComparator;
import static model.Utilities.getProductComparator;

/** Describes both the Parts and Product that exist within Inventory, as well as methods to interact with their data. */
public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** User-friendly way to search for a Part by ID: Allows users to only provide two parameters instead of manually entering the bounds of the list.
     *
     * @param list A list of Parts to be searched.
     * @param itemId The Item ID of the Part being searched for.
     * @return If found, the Part we were looking for, and null otherwise.
     */
    public static Part partSearch(ObservableList<Part> list, int itemId){
        return partSearch(list, 0, list.size() - 1, itemId);
    }

    /** A binary search algorithm for use with Part lists sorted by ID.
     *
     * @param list A list of Parts to be searched.
     * @param l The leftmost index of the given Parts list.
     * @param r The rightmost index of the given Parts list.
     * @param itemId The Item ID of the Part being searched for.
     * @return If found, the Part we were looking for, and null otherwise.
     */
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

    /** User-friendly way to search for a Product by ID: Allows users to only provide two parameters instead of manually entering the bounds of the list.
     *
     * @param list A list of Products to be searched.
     * @param itemId The Item ID of the Product being searched for.
     * @return If found, the product we were looking for, and null otherwise.
     */
    public static Product productSearch(ObservableList<Product> list, int itemId){
        return productSearch(list, 0, list.size() - 1, itemId);
    }

    /** Binary search algorithm for use with Product lists sorted by ID.
     *
     * @param list A list of Products to be searched.
     * @param l The leftmost index of the given Products list.
     * @param r The rightmost index of the given Products list.
     * @param itemId The Item ID of the Product being searched for.
     * @return If found, the product we were looking for, and null otherwise.
     */
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

    /** Adds a new Part to Inventory.
     *
     * @param newPart The Part to be added to Inventory.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** Adds a new Product to Inventory.
     *
     * @param newProduct The Product to be added to Inventory.
     */
    public static void addProduct(Product newProduct) {
        getAllProducts().add(newProduct);
    }

    /** Sorts all Parts in Inventory by ascending partId, then searches for the Part specified by given partId.
     *
     * @param partId The Part ID of the Part being searched for.
     * @return If found, the Part we were looking for, and null otherwise.
     */
    public static Part lookupPart(int partId) {
        allParts.sort(getPartComparator());
        return partSearch(getAllParts(), partId);
    }
    /** Sorts all Products in Inventory by ascending productId, then searches for the Product specified by given productId.
     *
     * @param productId The Product ID of the Product being searched for.
     * @return If found, the Product that we were looking for, and null otherwise.
     */
    public static Product lookupProduct(int productId) {
        allProducts.sort(getProductComparator());
        return productSearch(getAllProducts(), productId);
    }

    /** Searches Inventory for all Parts which include the given partName as a substring.
     *
     * @param partName The whole or partial name of the Product we are searching for.
     * @return A list containing all Products that met the search criterion.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matches = FXCollections.observableArrayList();
        for(Part part : getAllParts()) {
            if(part.getName().toLowerCase().contains(partName)) {
                matches.add(part);
            }
        }
        return matches;
    }

    /** Searches Inventory for all Products which include the given productName as a substring.
     *
     * @param productName The whole or partial name of the Product we are searching for.
     * @return
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> matches = FXCollections.observableArrayList();
        for(Product product : getAllProducts()) {
            if(product.getName().toLowerCase().contains(productName)) {
                matches.add(product);
            }
        }
        return matches;
    }

    /** Replaces the Part existing at the given index with the given Part.
     *
     * @param index The index of the existing Part.
     * @param selectedPart The Part we wish to replace the existing Part with.
     */
    public static void updatePart(int index, Part selectedPart) {
        getAllParts().set(index, selectedPart);
    }

    /** Replaces the Product existing at the given index with the given Product.
     *
     * @param index The index of the existing Product.
     * @param newProduct The Product we wish to replace the existing Product with.
     */
    public static void updateProduct(int index, Product newProduct){
        getAllProducts().set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        return getAllParts().remove(selectedPart);
    }

    /** Remove the given Part from Inventory.
     *
     * @param selectedPart The Part to be removed.
     * @return True if the Product was successfully removed, and False otherwise.
     */
    public static boolean deleteProduct(Product selectedPart) {
        return getAllProducts().remove(selectedPart);
    }

    /** Retrieve the list of all Parts currently in Inventory.
     *
     * @return The list of all Parts currently in Inventory.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** Retrieve the list of all Products currently in Inventory.
     *
     * @return The list of all Products currently in Inventory.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}