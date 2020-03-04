package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    public Stage stage;

    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outsourcedRadio;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private TextField partIDField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvField;
    @FXML private TextField partPriceField;
    @FXML private Label partTypeLabel;
    @FXML private TextField partTypeField;
    @FXML private TextField partMaxField;
    @FXML private TextField partMinField;
    private boolean inHouse;
    private static Part partToModify;

    public static void partPass(Part part) {
        partToModify = part;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (partToModify instanceof InHouse) {
            inHouse = true;
            partTypeLabel.setText("Machine ID");
            partTypeField.setText(Integer.toString(((InHouse) partToModify).getMachineID()));
        } else if (partToModify instanceof Outsourced) {
            inHouse = false;
            partTypeLabel.setText("Company Name");
            partTypeField.setText(((Outsourced) partToModify).getCompanyName());
        }

        partIDField.setDisable(true);
        partIDField.setPromptText(Integer.toString(partToModify.getPartID()));
        partNameField.setText(partToModify.getPartName());
        partInvField.setText(Integer.toString(partToModify.getPartInStock()));
        partPriceField.setText(Double.toString(partToModify.getPartPrice()));
        partMaxField.setText(Integer.toString(partToModify.getMax()));
        partMinField.setText(Integer.toString(partToModify.getMin()));
    }

    @FXML
    void cancelPart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.initModality(Modality.NONE);
        alert.setTitle("Cancel Part");
        alert.setHeaderText("Confirm cancellation");
        alert.setContentText("Are you sure you want to cancel?\r");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Node source = (Node) event.getSource();
            stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void inHouseSelected(ActionEvent event) {
        partTypeLabel.setText("Machine ID");
        partTypeField.setPromptText("Machine ID");
        inHouse = true;
    }

    @FXML
    void outsourcedSelected(ActionEvent event) {
        partTypeLabel.setText("Company Name");
        partTypeField.setPromptText("Company Name");
        inHouse = false;
    }

    @FXML
    void savePart(ActionEvent event) {
        //initializing variables
        boolean parseError = false;
        int partID = partToModify.getPartID();
        String partName = partNameField.getText();
        int partInStock = 0;
        double partPrice = 0;
        int partMax = 0;
        int partMin = 0;
        String companyName = null;
        int machineID = 0;

        if(!inHouse) {
            companyName = partTypeField.getText();
        }

        //converts string inputs to proper types
        try {
            partInStock = Integer.parseInt(partInvField.getText());
            partPrice = Double.parseDouble(partPriceField.getText());
            partMax = Integer.parseInt(partMaxField.getText());
            partMin = Integer.parseInt(partMinField.getText());
            if (inHouse) {
                machineID = Integer.parseInt(partTypeField.getText());
            }
        } catch (NumberFormatException e) {
            parseError = true;
            System.err.println("Unable to format. " + e);
        }

        //displays any errors
        if (parseError || (partMax < partMin) || (partInStock > partMax)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.NONE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            if (parseError) {
                alert.setContentText("Invalid input");
            } else if (partMax < partMin) {
                alert.setContentText("Max must be greater than min.");
            } else {
                alert.setContentText("Inventory can not be greater than max amount of items to be stored.");
            }
            alert.showAndWait();
            return;
        }

        //add parts to inventory
        if (!parseError && (partMax >= partMin) && (partInStock <= partMax)) {
            if (inHouse) {
                Inventory.updatePart(partToModify.getPartID(), new InHouse(partID, partName, partPrice, partInStock, partMin, partMax, machineID));
            } else {
                Inventory.updatePart(partToModify.getPartID(), new Outsourced(partID, partName, partPrice, partInStock, partMin, partMax, companyName));
            }
        }

        //closes window
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
