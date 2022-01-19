package viewcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyPartScreen extends Controller implements Initializable {

    public Label title;
    public RadioButton inHouse;
    public ToggleGroup partType;
    public RadioButton outsourced;
    public Label typeSpecific;
    public Button save;
    public Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onInHouse(ActionEvent actionEvent) {
        typeSpecific.setText("Machine ID");
    }

    public void onOutsourced(ActionEvent actionEvent) {
        typeSpecific.setText("Company Name");
    }
}
