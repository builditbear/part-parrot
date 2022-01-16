package viewcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.DataModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreen extends Controller implements Initializable {

    public TableView partsTable;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn partStock;
    public TableColumn partPrice;
    public Button partAddButton;
    public Button partModButton;
    public Button partDelButton;

    public TableView productsTable;
    public TableColumn productId;
    public TableColumn productName;
    public TableColumn productStock;
    public TableColumn productPrice;
    public Button productAddButton;
    public Button productModButton;
    public Button productDelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Associating table columns with appropriate object properties.
        partsTable.setItems(DataModel.Inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(DataModel.Inventory.getAllProducts());
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Dummy product data for productTable testing.
        DataModel.Inventory.addProduct(new DataModel.Product(1, "Alpha",30.50, 10, 1, 100,
                FXCollections.observableArrayList()));
        DataModel.Inventory.addProduct(new DataModel.Product(2, "Beta",30.50, 10, 1, 100,
                FXCollections.observableArrayList()));
        DataModel.Inventory.addProduct(new DataModel.Product(3, "Gamma",10.75, 50, 1, 100,
                FXCollections.observableArrayList()));
        DataModel.Inventory.addProduct(new DataModel.Product(4, "Omega",100.54, 5, 1, 100,
                FXCollections.observableArrayList()));
    }

    public void onPartAdd(ActionEvent actionEvent) throws IOException{
        loadScene(actionEvent, "Add Part", "AddModifyPart", "450x550");
    }

    public void onPartMod(ActionEvent actionEvent) {
    }

    public void onPartDel(ActionEvent actionEvent) {
        DataModel.Part selectedPart = (DataModel.Part) partsTable.getSelectionModel().getSelectedItem();
        boolean successfulDelete = DataModel.Inventory.deletePart(selectedPart);
        if(!successfulDelete){
            System.out.println("Error: No part selected.");
        }
    }

    public void onProductAdd(ActionEvent actionEvent) throws IOException {
        loadScene(actionEvent, "Add Product", "AddModifyProduct","500x900");
    }

    public void onProductMod(ActionEvent actionEvent) {
    }

    public void onProductDel(ActionEvent actionEvent) {
        DataModel.Product selectedProduct = (DataModel.Product) productsTable.getSelectionModel().getSelectedItem();
        boolean successfulDelete = DataModel.Inventory.deleteProduct(selectedProduct);
        if(!successfulDelete){
            System.out.println("Error: No product selected.");
        }
    }
}
