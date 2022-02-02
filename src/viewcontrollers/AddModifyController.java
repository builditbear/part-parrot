package viewcontrollers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Part;
import model.Product;

/** Describes the base properties of any controller class for views which involve adding or modifying items. */
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


    /** Used to pass Part data from one controller to another via a static field.
     *
     * @param part The Part to be made available for use in another controller.
     */
    public static void setSelectedPart(Part part) {
        selectedPart = part;
    }

    /** Retrieves the shared Part.
     *
     * @return The shared Part.
     */
    public static Part getSelectedPart() {
        return selectedPart;
    }

    /** Used to pass in Product data from one controller to another via a static field.
     *
     * @param product The Product to be made available for use in another controller.
     */
    public static void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

    /** Retrieves the shared Product.
     *
     * @return The shared Product.
     */
    public static Product getSelectedProduct() {
        return selectedProduct;
    }
}
