/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Part;
import Model.InHouse;
import Model.Outsourced;
import Model.Inventory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Tomy Li He
 */
public class AddPartController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private ToggleGroup SourceToggleGroup;
    @FXML
    private RadioButton outSourceRadio;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField inven;
    @FXML
    private TextField price;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    @FXML
    private TextField CompanyOrMachineID;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label sourceLabel;
    
    private boolean isInHouse;
    private String exceptMsg = new String();
    private int idCount;
    
    
    /**
     * Initializes the controller class
     * @param url
     * @param rb
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        idCount = Inventory.setPartID();
        id.setText(Integer.toString(idCount + 1));
    }    
    
    @FXML
    private void switchToInHouse(MouseEvent event){
        sourceLabel.setText("Machine ID");
        isInHouse = true;
    }
    
    @FXML
    private void switchToOutsourced(MouseEvent event) {
        sourceLabel.setText("Company Name");
        isInHouse = false;
    }
    
    
    @FXML
    private void addPart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        String partName = name.getText();
        int partInven = Integer.parseInt(inven.getText());
        double partPrice = Double.parseDouble(price.getText());
        int partMax = Integer.parseInt(max.getText());
        int partMin = Integer.parseInt(min.getText());
        String inOrOutsource = CompanyOrMachineID.getText();
        
        try {
            exceptMsg = Part.compareMinMax(partMin, partMax, partInven);
            
            if (exceptMsg.length() > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to add part");
                alert.setContentText(exceptMsg);
                alert.showAndWait();
            }
            else {
                if(isInHouse){
                    int machineID = Integer.parseInt(inOrOutsource);
                    InHouse part = new InHouse(idCount, partName, partPrice, partInven, partMin, partMax, machineID);
                    Inventory.addPart(part);
                }
                else{
                    String companyName = inOrOutsource;
                    Outsourced part = new Outsourced(idCount, partName, partPrice, partInven, partMin, partMax, companyName);
                    Inventory.addPart(part);
                }
            }
        }
        // Catch invalid string to numeric conversion
        catch (NumberFormatException except){
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Error");
           alert.setHeaderText("Failed to add part");
           alert.setContentText("Invalid value was entered.");
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
        stage.setScene(new Scene(scene));
        stage.show();
    }
            
}
