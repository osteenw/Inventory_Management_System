package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part partToAdd) {
        if (partToAdd != null) {
            allParts.add(partToAdd);
        }
    }

    public static void addProduct(Product productToAdd) {
        if (productToAdd != null) {
            allProducts.add(productToAdd);
        }
    }

    public static Part lookupPart(int partID) {
        if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getPartID() == partID) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }

    public static Product lookupProduct(int productID) {
        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getProductID() == productID) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partList = FXCollections.observableArrayList();;
        partName = partName.toLowerCase();

        if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
//              Finds part name from parts list and converts to lower case
                String lowerCasePart = allParts.get(i).getPartName().toLowerCase();
                if (lowerCasePart.contains(partName)) {
                    partList.add(allParts.get(i));
                }
            }
            return partList;
        }
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productList = FXCollections.observableArrayList();;
        productName = productName.toLowerCase();

        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
//              Finds product name from products list and converts to lower case
                String lowerCaseProduct = allProducts.get(i).getProductName().toLowerCase();
                if (lowerCaseProduct.contains(productName)) {
                    productList.add(allProducts.get(i));
                }
            }
            return productList;
        }
        return null;
    }

    public static void updatePart(int index, Part part) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getPartID() == part.getPartID()) {
                allParts.set(i, part);
                break;
            }
        }
    }

    public static void updateProduct(int index, Product product) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductID() == product.getProductID()) {
                allProducts.set(i, product);
                break;
            }
        }
    }

    public static void deletePart(Part part) {
       for (int i = 0; i < allParts.size(); i++) {
           if (allParts.get(i).getPartID() == part.getPartID()) {
               allParts.remove(i);
               break;
           }
       }
    }

    public static void deleteProduct(Product product) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductID() == product.getProductID()) {
                allProducts.remove(i);
                break;
            }
        }
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
