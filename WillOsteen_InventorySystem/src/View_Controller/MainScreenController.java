package View_Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    public Stage stage;

    @FXML private Button exitButton;
    @FXML private Button addPartButton;
    @FXML private Button modifyPartButton;
    @FXML private Button deletePartButton;
    @FXML private TextField searchPartTextField;
    @FXML private Button searchPartButton;
    @FXML private Button addProductButton;
    @FXML private Button modifyProductButton;
    @FXML private Button deleteProductButton;
    @FXML private TextField searchProductTextField;
    @FXML private Button searchProductButton;

    private static Part partToModify;
    private static Product productToModify;

    //parts table
    @FXML private TableView<Part> PartsTable;
    @FXML private TableColumn<Part, Integer> partID_Column;
    @FXML private TableColumn<Part, String> partName_Column;
    @FXML private TableColumn<Part, Integer> itemCount_Column;
    @FXML private TableColumn<Part, Double> partPrice_Column;

    //products table
    @FXML private TableView<Product> ProductsTable;
    @FXML private TableColumn<Product, Integer> productID_Column;
    @FXML private TableColumn<Product, String> productName_Column;
    @FXML private TableColumn<Product, Integer> inventoryLevel_Column;
    @FXML private TableColumn<Product, Double> pricePerUnit_Column;

    //initializing table
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //parts table
        partID_Column.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partName_Column.setCellValueFactory(new PropertyValueFactory<>("partName"));
        itemCount_Column.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        partPrice_Column.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        PartsTable.setItems(Inventory.getAllParts());

        //products table
        productID_Column.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productName_Column.setCellValueFactory(new PropertyValueFactory<>("productName"));
        inventoryLevel_Column.setCellValueFactory(new PropertyValueFactory<>("productInStock"));
        pricePerUnit_Column.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        ProductsTable.setItems(Inventory.getAllProducts());
    }

    @FXML
    void addPart(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void addProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    void deletePart(ActionEvent event) {
        if (PartsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.NONE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("No part selected");
            alert.showAndWait();
            return;
        }
        Part part = PartsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.initModality(Modality.NONE);
        alert.setTitle("Delete Part");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("Are you sure you want to delete " + part.getPartName() + "?\r");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Inventory.deletePart(part);
        }
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        if (ProductsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.NONE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("No product selected");
            alert.showAndWait();
            return;
        }

        Product product = ProductsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


        alert.initModality(Modality.NONE);
        alert.setTitle("Delete Part");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("Are you sure you want to delete " + product.getProductName() + "?\r");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Inventory.deleteProduct(product);
        }
    }

    @FXML
    void modifyPart(ActionEvent event) throws IOException {
        if (PartsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.NONE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("No part selected");
            alert.showAndWait();
            return;
        }

        partToModify = PartsTable.getSelectionModel().getSelectedItem();
        ModifyPartController.partPass(partToModify);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void modifyProduct(ActionEvent event) throws IOException {
        if (ProductsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.NONE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("No product selected");
            alert.showAndWait();
            return;
        }

        productToModify = ProductsTable.getSelectionModel().getSelectedItem();
        ModifyProductController.productPass(productToModify);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void searchPart(ActionEvent event) {
        String searchText = searchPartTextField.getText().trim();
        if (!searchText.isEmpty()) {
            PartsTable.setItems(Inventory.lookupPart(searchText));
        } else {
            PartsTable.setItems(Inventory.getAllParts());
        }
    }

    @FXML
    void searchProduct(ActionEvent event) {
        String searchText = searchProductTextField.getText().trim();
        if (!searchText.isEmpty()) {
            ProductsTable.setItems(Inventory.lookupProduct((searchText)));
        } else {
            ProductsTable.setItems(Inventory.getAllProducts());
        }
    }

    @FXML
    void exitApplication(ActionEvent event) {
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}


