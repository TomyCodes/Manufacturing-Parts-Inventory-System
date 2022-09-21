/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Inventory;
import Model.Product;
import Model.Part;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Tomy Li He
 */
public class AddProductController implements Initializable {
    Stage stage;
    Parent scene;
    
    @FXML
    private TextField addProductMin;
    @FXML
    private TextField addProductMax;
    @FXML
    private TextField addProductID;
    @FXML
    private TextField addProductName;
    @FXML
    private TextField addProductInven;
    @FXML
    private TextField addProductPrice;    
    @FXML
    private TextField prodSearchField;
    @FXML
    private TableView<Part> addTable;
    @FXML
    private TableView<Part> deleteTable;    
    @FXML
    private TableColumn<Part, Double> addPrice;
    @FXML
    private TableColumn<Part, Integer> addInvenLevel;
    @FXML
    private TableColumn<Part, String> addPartName;
    @FXML
    private TableColumn<Part, Integer> addPartID;    
    @FXML
    private TableColumn<Part, Double> deletePrice;
    @FXML
    private TableColumn<Part, Integer> deleteInvenLevel;
    @FXML
    private TableColumn<Part, String> deletePartName;
    @FXML
    private TableColumn<Part, Integer> deletePartID;    
    @FXML
    private Button prodSearchButton;    
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;    
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;     
    
    private int productIDCounter;
    private String exceptMsg = new String();
    private final ObservableList<Part> addedParts = FXCollections.observableArrayList();
    Inventory inven = new Inventory();
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addTable.setItems(Inventory.getAllParts());
        addPartID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        addPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addInvenLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        deleteTable.setItems(addedParts);
        deletePartID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        deletePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        deleteInvenLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        deletePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productIDCounter = Inventory.setProductID();
        addProductID.setText(Integer.toString(productIDCounter + 1));        
    }    
    
    @FXML
    private void lookupPart(MouseEvent event) {
        String searchText = prodSearchField.getText();
        ObservableList<Part> partList = FXCollections.observableArrayList();
        partList = Inventory.lookupPart(searchText);
        
        if (partList.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Part not found");
            alert.showAndWait();
        }
        else{
            addTable.setItems(partList);
        }
    }
    
    @FXML
    private void addPart(MouseEvent event) throws IOException {
        addedParts.add(Inventory.lookupPart((Inventory.getAllParts().indexOf((addTable.getSelectionModel().getSelectedItem())) + 1)));
    }
    
    @FXML
    private void deletePart(MouseEvent event) {
        Part partToRemove = deleteTable.getSelectionModel().getSelectedItem();
        addedParts.remove(partToRemove);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Part was removed");
        alert.showAndWait();
    }
    
    @FXML 
    private void saveProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        int prodID = Integer.parseInt(addProductID.getText());
        String prodName = addProductName.getText();
        int prodInven = Integer.parseInt(addProductInven.getText());
        double prodPrice = Double.parseDouble(addProductPrice.getText());
        int prodMin = Integer.parseInt(addProductMin.getText());
        int prodMax = Integer.parseInt(addProductMax.getText());
        
        try {
            exceptMsg = Product.compareMinMax(prodMin, prodMax, prodInven);
            
            if (exceptMsg.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to add part");
                alert.setContentText(exceptMsg);
                alert.showAndWait();
            }
            else{
                Product prod = new Product(prodID, prodName, prodPrice, prodInven, prodMin, prodMax);
                Inventory.addProduct(prod);
            }
        }
        catch (NumberFormatException except) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");  
        alert.setContentText("Invalid values were entered");
        alert.showAndWait();
        }
        scene = FXMLLoader.load(getClass().getResource("/View/Mainform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show(); 
    }
    
    @FXML 
    private void cancel(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Mainform.fxml"));
        stage.setScene(new Scene (scene));
        stage.show();
    }
    
}
