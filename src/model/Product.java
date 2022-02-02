package model;

import javafx.collections.ObservableList;

/** Describes a Product in terms of its attributes. Features a list of associated Parts involved in the Product. */
public class Product {
    private final ObservableList<Part> associatedParts;
    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;

    /** Constructs a new Product. */
    public Product(ObservableList<Part> associatedParts, int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = associatedParts;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Sets this Product's ID.
     *
     * @param id The ID to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Sets this Product's name.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Sets this Product's price.
     *
     * @param price The price to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Sets this Product's stock.
     *
     * @param stock The stock to be set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Sets this Product's minimum inventory level.
     *
     * @param min The minimum to be set.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** Sets this Product's maximum inventory level.
     *
     * @param max The maximum to be set.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /** Retrieves this Product's ID.
     *
     * @return The Product's ID.
     */
    public int getId() {
        return id;
    }

    /** Retrieves this Product's name.
     *
     * @return This Product's name.
     */
    public String getName() {
        return name;
    }

    /** Retrieves this Product's price.
     *
     * @return This Product's price.
     */
    public double getPrice() {
        return price;
    }

    /** Retrieves this Product's stock.
     *
     * @return This Product's stock.
     */
    public int getStock() {
        return stock;
    }

    /** Retrieves this Product's minimum stock level.
     *
     * @return This Product's minimum stock level.
     */
    public int getMin() {
        return min;
    }

    /** Retrieves this Product's maximum stock level.
     *
     * @return This Product's maximum stock level.
     */
    public int getMax() {
        return max;
    }

    /** Associates the given Part with this Product.
     *
     * @param part The Part to be associated with this Product.
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /** Disassociates the given Part from this Product.
     *
      * @param selectedAssociatedPart The Part to be disassociated.
     * @return True if the Part was successfully disassociated, and False otherwise.
     */
    public boolean deletedAssociatedPart(Part selectedAssociatedPart) {
        return this.associatedParts.remove(selectedAssociatedPart);
    }

    /** Retrieves the list of all Parts associated with this Product.
     *
     * @return The list of all Parts associated with this Product.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }

    /** Prints out all Parts associated with this Product. */
    public void printAssociatedParts() {
        if(!getAllAssociatedParts().isEmpty()) {
            for (Part part : this.getAllAssociatedParts()) {
                System.out.println(part.getName());
            }
            System.out.println();
        }
    }
}
