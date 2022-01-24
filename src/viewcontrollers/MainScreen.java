package viewcontrollers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// BUG-LOG
// 01/16/22: Delete button not working after the tableview has been altered via the search field.
// FIXED on 01/16/22 - Although the deletion handler was, in fact, deleting the selected inventory item from inventory,
// it did *not* delete that item from the list of search results being depicted by the TableView following a search query.
// The simple solution was to add an extra statement in the deletion handler such that the selected inventory
// item is deleted from both inventory and the result list being depicted at that time.

public class MainScreen extends Controller implements Initializable {

    public TextField partSearchField;
    public TextField productSearchField;

    public TableView<Part> partsTable;
    public TableColumn<Part, Integer> partId;
    public TableColumn<Part, String> partName;
    public TableColumn<Part, Integer> partStock;
    public TableColumn<Part, Double> partPrice;
    public Button partAddButton;
    public Button partModButton;
    public Button partDelButton;

    public TableView<Product> productsTable;
    public TableColumn<Product, Integer> productId;
    public TableColumn<Product, String> productName;
    public TableColumn<Product, Integer> productStock;
    public TableColumn<Product, Double> productPrice;
    public Button productAddButton;
    public Button productModButton;
    public Button productDelButton;
    public Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Associating table columns with appropriate object properties.
        populatePartsTable(Inventory.getAllParts(), partsTable);
        populateProductTable(Inventory.getAllProducts(), productsTable);
    }

    public void onPartAdd(ActionEvent actionEvent) throws IOException{
        loadScene(actionEvent, "Add Part", "AddModifyPart", "550x450");
    }

    public void onPartMod(ActionEvent actionEvent) throws IOException{
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null) {
            AddModifyController.passSelectedPart(selectedPart);
            loadScene(actionEvent, "Modify Part", "AddModifyPart", "550x450");
        }
    }

    public void onPartDel() {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        boolean deletedFromTableView = partsTable.getItems().remove(selectedPart);
        boolean deletedFromInventory = Inventory.deletePart(selectedPart);
        if(!(deletedFromTableView && deletedFromInventory)){
            System.out.println("Error: No part selected.");
        }
    }

    public void onProductAdd(ActionEvent actionEvent) throws IOException {
        loadScene(actionEvent, "Add Product", "AddModifyProduct","500x900");
    }

    public void onProductMod(ActionEvent actionEvent) {
    }

    public void onProductDel() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        boolean deletedFromTableView = productsTable.getItems().remove(selectedProduct);
        boolean deletedFromInventory = Inventory.deleteProduct(selectedProduct);
        if(!(deletedFromTableView && deletedFromInventory)){
            System.out.println("Error: No product selected.");
        }
    }

    public void onSearchKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {

            TextField field = (TextField) keyEvent.getSource();
            String searchTerm = field.getText();
            String fieldId = field.getId();
            if(fieldId.equals("partSearchField")) {
                ObservableList<Part> parts = FXCollections.observableArrayList();
                try {
                    // Check whether the search term is an integer id.
                    int id = Integer.parseInt(searchTerm);
                    parts.add(Inventory.lookupPart(id));
                } catch (Exception e) {
                    e.printStackTrace();
                    // In the event that it is not an integer id, we will search by name instead.
                    parts = Inventory.lookupPart(searchTerm.toLowerCase());
                }
                populatePartsTable(parts, partsTable);
            }else if(fieldId.equals("productSearchField")) {
                ObservableList<Product> products = FXCollections.observableArrayList();
                try {
                    int id = Integer.parseInt(searchTerm);
                    products.add(Inventory.lookupProduct(id));
                } catch (NumberFormatException e) {
                    products = Inventory.lookupProduct(searchTerm.toLowerCase());
                }
                populateProductTable(products, productsTable);
            }
        }
    }

    public void populatePartsTable(ObservableList<Part> list, TableView<Part> table) {
        table.setItems(list);
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void populateProductTable(ObservableList<Product> list, TableView<Product> table) {
        table.setItems(list);
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    public void shutDown(ActionEvent actionEvent) {
        Platform.exit();
    }
}
