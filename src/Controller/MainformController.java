/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Part;
import Model.Product;
import Model.Inventory;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tomy Li He
 */
public class MainformController implements Initializable {
    Stage stage;
    Parent scene;
    
    @FXML
    private TextField PartSearchField;
    @FXML
    private TextField ProdSearchField;
    @FXML
    private Button SearchButton;
    @FXML
    private Button AddPart;
    @FXML
    private Button ModifyPart;
    @FXML
    private Button DeletePart;
    @FXML
    private Button ProdSearchButton;
    @FXML
    private Button AddProduct;
    @FXML
    private Button ModifyProduct;
    @FXML
    private Button DeleteProduct;
    @FXML
    private Button ExitButton;
    @FXML
    private TableView<Part> PartTable;
    @FXML
    private TableView<Product> ProdTable;
    @FXML
    private TableColumn<Part, Integer> PartIDCol;
    @FXML
    private TableColumn<Part, String> PartNameCol;
    @FXML
    private TableColumn<Part, Integer> PartInvenCol;
    @FXML
    private TableColumn<Part, Double> PartPriceCol;
    @FXML
    private TableColumn<Product, Integer> ProdIDCol;
    @FXML
    private TableColumn<Product, String> ProdNameCol;
    @FXML
    private TableColumn<Product, Integer> ProdInvenCol;
    @FXML
    private TableColumn<Product, Double> ProdPriceCol;
    
    // Used to autoincrement product and part ID;

    /**
     *
     */
    protected static int partModInd;

    /**
     *
     */
    protected static int prodModInd;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PartTable.setItems(Inventory.getAllParts());
        PartIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInvenCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        ProdTable.setItems(Inventory.getAllProducts());
        ProdIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        ProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProdInvenCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
    
    @FXML
    private void searchForPart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
        String searchPart = PartSearchField.getText();
        ObservableList<Part> foundPart = FXCollections.observableArrayList();
        foundPart = Inventory.lookupPart(searchPart);
        
        if (foundPart.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Part could not be found");
            alert.showAndWait();
        }
        else {
            PartTable.setItems(foundPart);
        }
    }
    
    @FXML
    private void addPart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @FXML
    private void deletePart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Part partToDelete = PartTable.getSelectionModel().getSelectedItem();
        Inventory.deletePart(partToDelete);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deleted");
        alert.setHeaderText("Part deleted");
        alert.showAndWait();
    }
    
    @FXML
    private void updatePart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        partModInd = Inventory.getAllParts().indexOf((PartTable.getSelectionModel().getSelectedItem()));
        scene = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @FXML
    private void searchForProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
        String searchProduct = ProdSearchField.getText();
        ObservableList<Product> foundProd = FXCollections.observableArrayList();
        foundProd = Inventory.lookupProduct(searchProduct);
        
        if (foundProd.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Product could not be found");
            alert.showAndWait();
        }
        else {
            ProdTable.setItems(foundProd);
        }
    }
    
    @FXML 
    private void addProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // RUNTIME ERROR when using the productID, it kept going out of index range, so subtracting 1 to index was neccessary in order to properly select the correct product when updating
    @FXML
    private void updateProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();  
        prodModInd = Inventory.getAllProducts().indexOf((ProdTable.getSelectionModel().getSelectedItem())) - 1;
        scene = FXMLLoader.load(getClass().getResource("/View/ModifyProducts.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }
    @FXML 
    private void deleteProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();  
        Product prod = ProdTable.getSelectionModel().getSelectedItem();
        
        for(int i = 0; i < Inventory.getAllParts().size(); i++) {
            if (Inventory.checkAssociatedParts(Inventory.getAllParts().get(i)).equals("")){
                Inventory.deleteProduct(prod);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product deleted");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to delete product");
                alert.setContentText("Check to see if there are any parts associated with product");
                alert.showAndWait();
            }
        }
        
    }
    
    @FXML
    private void exit(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
