package viewcontrollers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.InHouse;
import model.Part;
import model.Product;

import static model.Utilities.randomInt;

public class AddModifyController extends Controller{

    private static Part selectedPart;
    private static Product selectedProduct;

    public TextField idField;
    public TextField nameField;
    public TextField invField;
    public TextField priceField;
    public TextField maxField;
    public TextField minField;
    public Button save;
    public Button cancel;


    // Used to pass in InventoryItem data from the main screen.
    public static void passSelectedPart(Part part) {
        selectedPart = part;
    }

    public static Part getSelectedPart() {
        return selectedPart;
    }

    public static void passSelectedProduct(Product product) {
        selectedProduct = product;
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }
}
