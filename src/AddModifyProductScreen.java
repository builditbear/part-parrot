import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import viewcontrollers.AddModifyController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.Inventory.*;
import static model.Utilities.generateId;
import static model.Utilities.populatePartsTable;

public class AddModifyProductScreen extends AddModifyController implements Initializable {

    public TableView<Part> partsTable;
    public TableColumn<Part, Integer> partId;
    public TableColumn<Part, String> partName;
    public TableColumn<Part, Integer> partStock;
    public TableColumn<Part, Double> partPrice;

    public TableView<Part> associatedPartsTable;
    public TableColumn<Part, Integer> assocPartId;
    public TableColumn<Part, String> assocPartName;
    public TableColumn<Part, Integer> assocPartStock;
    public TableColumn<Part, Double> assocPartPrice;

    private ObservableList<Part> associatedPartsBuffer = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Render the part to be modified if one was passed in.
        Product selectedProduct = getSelectedProduct();
        if(selectedProduct != null) {
            title.setText("Modify Product");
            idField.setText(Integer.toString(selectedProduct.getId()));
            nameField.setText(selectedProduct.getName());
            invField.setText(Integer.toString(selectedProduct.getStock()));
            priceField.setText(Double.toString(selectedProduct.getPrice()));
            maxField.setText(Integer.toString(selectedProduct.getMax()));
            minField.setText(Integer.toString(selectedProduct.getMin()));

            associatedPartsBuffer = FXCollections.observableArrayList(selectedProduct.getAllAssociatedParts());
        }
        populatePartsTable(getAllParts(), partsTable, partId, partName, partStock, partPrice);
        populatePartsTable(associatedPartsBuffer, associatedPartsTable, assocPartId, assocPartName,
                    assocPartStock, assocPartPrice);
    }

    public void onSave(ActionEvent actionEvent) throws IOException{
        Product newProduct;
        newProduct = getProductFromFields();
        if(title.getText().equals("Add Product")) {
            addProduct(newProduct);
        } else if(title.getText().equals("Modify Product")) {
            Product product = getSelectedProduct();
            try {
                assert newProduct != null;
            } catch(NullPointerException e) {
                System.out.println("the newProduct object you are trying to save is null.");
                loadMainScene((Stage) title.getScene().getWindow());
            }
            updateProduct(product, newProduct);
        }
        loadMainScene((Stage) title.getScene().getWindow());
    }

    public void updateProduct(Product product, Product newProduct) {
        int indexOfProduct = Inventory.getAllProducts().indexOf(product);
        Inventory.updateProduct(indexOfProduct, newProduct);
        setSelectedProduct(null);
    }

    public void onCancel(ActionEvent actionEvent) throws IOException {
        loadMainScene((Stage) title.getScene().getWindow());
    }

    public void onAdd(ActionEvent actionEvent) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        try {
            associatedPartsBuffer.add(selectedPart);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void onRemove(ActionEvent actionEvent) {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        try {
            associatedPartsBuffer.remove(selectedPart);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void onSearchKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            TextField field = (TextField) keyEvent.getSource();
            String searchTerm = field.getText();
            ObservableList<Part> parts = FXCollections.observableArrayList();
            try {
                int id = Integer.parseInt(searchTerm);
                parts.add(lookupPart(id));
            } catch(NumberFormatException e) {
                parts = Inventory.lookupPart(searchTerm.toLowerCase());
            }
            populatePartsTable(parts, partsTable, partId, partName, partStock, partPrice);
        }
    }

    // Currently has bug where editing a part or product causes the edited object (which is, in fact, a new object)
    // to be assigned the next ID in the appropriate object category. Will return to fix this during debugging.
    public Product getProductFromFields() {
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
        ObservableList<Part> associatedParts = FXCollections.observableArrayList(associatedPartsBuffer);
        return new Product(associatedParts, generateId(1), name, price, inv, min, max);
    }
}
