package viewcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.DataModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.DataModel.randomInt;

public class AddModifyPartScreen extends Controller implements Initializable {

    public Label title;
    public RadioButton inHouse;
    public ToggleGroup partType;
    public RadioButton outsourced;
    public Label typeSpecific;
    public Button save;
    public Button cancel;
    public TextField idField;
    public TextField nameField;
    public TextField invField;
    public TextField priceField;
    public TextField maxField;
    public TextField typeSpecificField;
    public TextField minField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onInHouse(ActionEvent actionEvent) {
        typeSpecific.setText("Machine ID");
    }

    public void onOutsourced(ActionEvent actionEvent) {
        typeSpecific.setText("Company Name");
    }

    public void onSave(ActionEvent actionEvent) throws IOException {
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

        DataModel.Part part = null;
        if(inHouse.isSelected()) {
            int machineId = randomInt(10000);
            if(validateIntInput(typeSpecificField.getText())) {
                machineId = Integer.parseInt(typeSpecificField.getText());
            }
            part = new DataModel.InHouse(0, name, price, inv,
                    min, max, machineId);
        } else if(outsourced.isSelected()) {
            String companyName = typeSpecificField.getText();
            part = new DataModel.Outsourced(0, name, price, inv,
                    min, max, companyName);
        }
        DataModel.Inventory.addPart(part);
        loadScene(actionEvent, "Main Screen", "MainScreen", "490x940");
    }
}
