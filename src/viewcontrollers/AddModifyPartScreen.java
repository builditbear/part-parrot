package viewcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.DataModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.DataModel.randomInt;

public class AddModifyPartScreen extends AddModifyController implements Initializable {

    public Label title;
    public RadioButton inHouse;
    public ToggleGroup partType;
    public RadioButton outsourced;
    public Label typeSpecific;
    public TextField typeSpecificField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Render the part to be modified if one was passed in.
        DataModel.Part selectedPart = getSelectedPart();
        if(getSelectedPart() != null) {
            title.setText("Modify Part");
            idField.setText(Integer.toString(selectedPart.getId()));
            nameField.setText(selectedPart.getName());
            invField.setText(Integer.toString(selectedPart.getStock()));
            priceField.setText(Double.toString(selectedPart.getPrice()));
            maxField.setText(Integer.toString(selectedPart.getMax()));
            minField.setText(Integer.toString(selectedPart.getMin()));
            String typeField = selectedPart.getTypeSpecificField();
            if(validateIntInput(typeField)) {
                typeSpecific.setText("Machine ID");
                typeSpecificField.setText(typeField);
            } else {
                typeSpecific.setText("Company Name");
                typeSpecificField.setText(typeField);
            }
        }
    }

    public void onInHouse(ActionEvent actionEvent) {
        typeSpecific.setText("Machine ID");
    }

    public void onOutsourced(ActionEvent actionEvent) {
        typeSpecific.setText("Company Name");
    }

    public void onSave(ActionEvent actionEvent) throws IOException {
        // Get the core values that all part objects have in common.
        DataModel.InventoryItem values = getCommonFields();
        DataModel.Part part = null;
        // Assign a machineId or companyName as appropriate for the part type, then create a new part
        // with the values we just extracted.
        if(inHouse.isSelected()) {
            int machineId = randomInt(10000);
            if(validateIntInput(typeSpecificField.getText())) {
                machineId = Integer.parseInt(typeSpecificField.getText());
            }
            part = new DataModel.InHouse(0, values.getName() , values.getPrice(), values.getStock(),
                    values.getMin(), values.getMax(), machineId);
        } else if(outsourced.isSelected()) {
            String companyName = typeSpecificField.getText();
            part = new DataModel.Outsourced(0, values.getName(), values.getPrice(), values.getStock(),
                    values.getMin(), values.getMax(), companyName);
        }
        // Finally, add the finished part to our inventory and take user back to main screen.
        DataModel.Inventory.addPart(part);
        loadScene(actionEvent, "Main Screen", "MainScreen", "490x940");
    }

    public void onCancel(ActionEvent actionEvent) throws IOException{
        loadScene(actionEvent, "Main Screen", "MainScreen", "490x940");
    }
}
