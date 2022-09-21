/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Part;
import Model.Inventory;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tomy Li He
 */
public class ModifyProductsController implements Initializable {
    Stage stage;
    Parent scene;
    
    @FXML
    private TextField modProdID;
    @FXML
    private TextField modProdName;
    @FXML
    private TextField modProdInven;
    @FXML
    private TextField modProdPrice;
    @FXML
    private TextField modProdMin;
    @FXML
    private TextField modProdMax;
    @FXML
    private TextField modProdSearchField;
    @FXML
    private TableView<Part> modProdAddTable;
    @FXML
    private TableView<Part> modProdDeleteTable;
    @FXML
    private TableColumn<Part, Integer> addID;
    @FXML
    private TableColumn<Part, Integer> deleteID;
    @FXML
    private TableColumn<Part, Integer> addInvenLevel;
    @FXML
    private TableColumn<Part, Integer> deleteInvenLevel;
    @FXML
    private TableColumn<Part, Double> addPrice;
    @FXML
    private TableColumn<Part, Double> deletePrice;
    @FXML
    private TableColumn<Part, String> addName;
    @FXML
    private TableColumn<Part, String> deleteName;
    @FXML
    private Button modProdSearchButton;
    @FXML
    private Button modProdAdd;
    @FXML
    private Button modProdRemove;
    @FXML
    private Button modProdCancel;
    @FXML
    private Button modProdSave;
    
    private ObservableList<Part> addedParts = FXCollections.observableArrayList();
    private ObservableList<Part> partList = FXCollections.observableArrayList();
    String exceptMsg = new String();
    
    /**
     *
     */
    protected int idProd = MainformController.prodModInd + 1;
             
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        modProdDeleteTable.setItems(Inventory.getAllProducts().get(idProd).getAllAssociatedParts());
        deletePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        deleteInvenLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        deleteName.setCellValueFactory(new PropertyValueFactory<>("name"));
        deleteID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());   
        
        modProdAddTable.setItems(Inventory.getAllParts());
        addID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        addName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addInvenLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        modProdID.setText(Integer.toString(idProd + 1));
        Product modifyProducts = Inventory.getAllProducts().get(idProd);  
        modProdName.setText(modifyProducts.getName());
        modProdInven.setText(Integer.toString(modifyProducts.getStock()));
        modProdPrice.setText(Double.toString(modifyProducts.getPrice()));
        modProdMin.setText(Integer.toString(modifyProducts.getMin()));
        modProdMax.setText(Integer.toString(modifyProducts.getMax())); 
        
        
    }   
    
    /**
     * Initializes the controller class.
     */   
    
    // FUTURE ENHANCEMENT Will improve to retain previous associated parts when adding new parts instead of adding to empty associated parts
    @FXML
    private void addPart(MouseEvent event) throws IOException {
        Part addpart = modProdAddTable.getSelectionModel().getSelectedItem();
        Inventory.getAllProducts().get(idProd).addAssociatedPart(addpart);    
    }
    
    @FXML
    private void deletePart(MouseEvent event){
        Part p = modProdDeleteTable.getSelectionModel().getSelectedItem();
        Inventory.getAllProducts().get(idProd).deleteAssociatedPart(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Part removed");
        alert.setHeaderText("Part was removed");
        alert.showAndWait();
    }
    
    @FXML
    private void saveProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
        int prodID = Integer.parseInt(modProdID.getText());
        String prodName = modProdName.getText();
        Double prodPrice = Double.parseDouble(modProdPrice.getText());
        int prodInven = Integer.parseInt(modProdInven.getText());
        int prodMin = Integer.parseInt(modProdMin.getText());
        int prodMax = Integer.parseInt(modProdMax.getText());
        
        try {
            exceptMsg = Product.compareMinMax(prodMin, prodMax, prodInven);
            
            if (exceptMsg.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to add product");
                alert.setContentText(exceptMsg);
                alert.showAndWait();
            }
            else{
                partList = Inventory.getAllProducts().get(idProd).getAllAssociatedParts();
                Product prod = new Product(prodID, prodName, prodPrice, prodInven, prodMin, prodMax);                
                for (int i = 0; i < partList.size(); i++){
                    prod.addAssociatedPart(partList.get(i));
                }
                Inventory.updateProduct(prodID - 1, prod);
            }
        }
        catch (NumberFormatException except) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid");
            alert.setContentText("Invalid value was entered");
            alert.showAndWait();
        }
        scene = FXMLLoader.load(getClass().getResource("/View/Mainform.fxml"));
        stage.setScene(new Scene (scene));
        stage.show();
    }
    
    @FXML
    private void lookupPart(MouseEvent event) {
        String searchFieldText = modProdSearchField.getText();
        ObservableList<Part> foundPart = FXCollections.observableArrayList();
        foundPart = Inventory.lookupPart(searchFieldText);
        
        if (foundPart.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Part not found");
            alert.showAndWait();
        }
        else{
            modProdAddTable.setItems(foundPart);
        }
    }
    
    @FXML
    private void cancel(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Mainform.fxml"));
        stage.setScene(new Scene (scene));
        stage.show();
    }
    
}
