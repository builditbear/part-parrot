package viewcontrollers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Part;
import model.Product;

public class AddModifyController extends Controller{

    private static Part selectedPart;
    private static Product selectedProduct;

    public Label title;
    public TextField idField;
    public TextField nameField;
    public TextField invField;
    public TextField priceField;
    public TextField maxField;
    public TextField minField;
    public Button save;
    public Button cancel;


    // Used to pass in InventoryItem data from the main screen.
    public static void setSelectedPart(Part part) {
        selectedPart = part;
    }

    public static Part getSelectedPart() {
        return selectedPart;
    }

    public static void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }
}
