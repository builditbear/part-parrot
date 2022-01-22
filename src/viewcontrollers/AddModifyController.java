package viewcontrollers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddModifyController extends Controller{

    private static DataModel.Part selectedPart;
    private static DataModel.Product selectedProduct;

    public TextField idField;
    public TextField nameField;
    public TextField invField;
    public TextField priceField;
    public TextField maxField;
    public TextField minField;
    public Button save;
    public Button cancel;


    // Used by both the product and part screen controllers to validate and extract values from the current view
    // that both classes have in common. These values are passed back in an InventoryItem whose values can then
    // be used to create a part or product.
    public DataModel.InventoryItem getCommonFields (){
        String name = nameField.getText();
        int inv = 0;
        if(validateIntInput(invField.getText())) {
            inv = Integer.parseInt(invField.getText());
        }
        double price = 0;
        if(validateDoubleInput(priceField.getText())) {
            price = Double.parseDouble(priceField.getText());
        }
        int max = 1;
        if(validateIntInput(maxField.getText())) {
            max = Integer.parseInt(maxField.getText());
        }
        int min = 0;
        if(validateIntInput(minField.getText()) && min < max) {
            min = Integer.parseInt(minField.getText());
        }

        return new DataModel.InventoryItem(0, name, price, inv, min, max);
    }

    // Used to pass in InventoryItem data from the main screen.
    public static void passSelectedPart(DataModel.Part part) {
        selectedPart = part;
    }

    public static DataModel.Part getSelectedPart() {
        return selectedPart;
    }

    public static void passSelectedProduct(DataModel.Product product) {
        selectedProduct = product;
    }

    public static DataModel.Product getSelectedProduct() {
        return selectedProduct;
    }
}
