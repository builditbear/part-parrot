package viewcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.Inventory.addPart;
import static model.Utilities.generateId;
import static model.Utilities.randomInt;

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
        Part selectedPart = getSelectedPart();
        if(getSelectedPart() != null) {
            title.setText("Modify Part");
            idField.setText(Integer.toString(selectedPart.getId()));
            nameField.setText(selectedPart.getName());
            invField.setText(Integer.toString(selectedPart.getStock()));
            priceField.setText(Double.toString(selectedPart.getPrice()));
            maxField.setText(Integer.toString(selectedPart.getMax()));
            minField.setText(Integer.toString(selectedPart.getMin()));

            if(selectedPart instanceof InHouse part) {
                inHouse.setSelected(true);
                typeSpecific.setText("Machine ID");
                typeSpecificField.setText(Integer.toString(part.getMachineId()));
            } else if(selectedPart instanceof Outsourced part){
                outsourced.setSelected(true);
                typeSpecific.setText("Company Name");
                typeSpecificField.setText(part.getCompanyName());
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
        // If this is the Add Part screen, validate and extract input from the text fields, create a new part,
        // and add it to inventory.
        Part newPart = null;
        if(inHouse.isSelected()) {
            newPart = getPartFromFields(InHouse.class);
        } else if(outsourced.isSelected()) {
            newPart = getPartFromFields(Outsourced.class);
        }
        if(title.getText().equals("Add Part")) {
            addPart(newPart);
        } else if(title.getText().equals("Modify Part")) {
            Part part = getSelectedPart();
            try {
                assert newPart != null;
            } catch(NullPointerException e) {
                System.out.println("the newPart object you are trying to save is null.");
                loadScene(actionEvent, "Main Screen", "MainScreen", "490x940");
            }
            updatePart(part, newPart);
            loadScene(actionEvent, "Main Screen", "MainScreen", "490x940");
        }
    }

    public void updatePart(Part part, Part newPart) {
        part.setName(newPart.getName());
        part.setStock(newPart.getStock());
        part.setPrice(newPart.getPrice());
        part.setMax(newPart.getMax());
        part.setMin(newPart.getMin());
        if (inHouse.isSelected()) {
            // If the entered machine ID isn't an integer, then the part's old machine ID will remain.
            if (validateIntInput(typeSpecificField.getText())) {
                int machineId = Integer.parseInt(typeSpecificField.getText());
                ((InHouse) part).setMachineId(machineId);
            } else if (outsourced.isSelected()) {
                String companyName = typeSpecificField.getText();
                ((Outsourced) part).setCompanyName(companyName);
            }
        }
    }

    // Used by both the product and part screen controllers to validate and extract values from the current view
    // that both classes have in common. These values are passed back in an InventoryItem whose values can then
    // be used to create a part or product.
    public <T extends Part> T getPartFromFields (Class<T> type){
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
        if(inHouse.isSelected()) {
            int machineId = randomInt(10000);
            if(validateIntInput(typeSpecificField.getText())) {
                machineId = Integer.parseInt(typeSpecificField.getText());
            }
            return type.cast(new InHouse(generateId(0), name, price, inv, min, max, machineId));
        } else if(outsourced.isSelected()) {
            String companyName = typeSpecificField.getText();
            return type.cast(new Outsourced(generateId(0), name, price, inv, min, max, companyName));
        }
        return null;
    }

    public void onCancel(ActionEvent actionEvent) throws IOException{
        loadScene(actionEvent, "Main Screen", "MainScreen", "490x940");
    }
}
