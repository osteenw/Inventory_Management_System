package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View_Controller/MainScreen.fxml"));
        primaryStage.setTitle("Inventory System");
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Inventory inv = new Inventory();
        addTestData(inv); //add test data

        launch(args);
    }

    public static void addTestData(Inventory inventory) {
        //In house parts
        Part a1 = new InHouse(1, "Gear", 10.45,
                15, 1, 25, 324);
        Part a2 = new InHouse(2, "Cog", 10.95,
                10, 1, 34, 3205);
        Part a3 = new InHouse(3, "Spork", 1.15,
                12, 1, 30, 86);

        //outsourced parts
        Part b1 = new Outsourced(4, "Gear2", 10.45,
                15, 1, 50, "Test Labs");
        Part b2 = new Outsourced(5, "Cog2", 10.95,
                10, 1, 40, "Test Labs");
        Part b3 = new Outsourced(6, "Spork2", 1.15,
                12, 1, 30, "Test Labs");

        //add in house parts to inventory
        inventory.addPart(a1);
        inventory.addPart(a2);
        inventory.addPart(a3);

        //add outsourced parts to inventory
        inventory.addPart(b1);
        inventory.addPart(b2);
        inventory.addPart(b3);

        //new product
        Product c1 = new Product(1, "Gear and Cog", 24.45,
                5, 1, 20);
        Product c2 = new Product(2, "Spork and Cog", 14.05,
                8, 1, 14);
        //add part to product
        c1.addAssociatedPart(b1);
        c1.addAssociatedPart(a2);

        c2.addAssociatedPart(a3);
        c2.addAssociatedPart(b2);

        //add product to inventory
        inventory.addProduct(c1);
        inventory.addProduct(c2);
    }
}
