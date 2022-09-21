/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Part;
import Model.Inventory;
import Model.InHouse;
import Model.Outsourced;

import java.util.ResourceBundle;
import java.net.URL;
import java.io.IOException;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tomy Li He
 */
public class ModifyPartController implements Initializable {
    Stage stage;
    Parent scene;
    
    @FXML
    private ToggleGroup InOrOutToggle;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outSourcedRadio;
    @FXML
    private Label partType;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField inven;
    @FXML
    private TextField min;
    @FXML
    private TextField max;
    @FXML
    private TextField companyOrMachineID;
    @FXML
    private Button saveButton;
    @FXML 
    private Button cancelButton;
    
    private String exceptMsg = new String();

    /**
     *
     */
    protected int idCounter = MainformController.partModInd; // Used for keeping track of part id
    private boolean boolInHouse;
    
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(Integer.toString(idCounter + 1));
        Part modifyParts = Inventory.getAllParts().get(idCounter);
        name.setText(modifyParts.getName());
        inven.setText(Integer.toString(modifyParts.getStock()));
        min.setText(Integer.toString(modifyParts.getMin()));
        max.setText(Integer.toString(modifyParts.getMax()));
        price.setText(Double.toString(modifyParts.getPrice()));
        
        if (modifyParts instanceof InHouse) {
            inHouseRadio.setSelected(true);
            partType.setText("Machine ID");
            companyOrMachineID.setText(Integer.toString(((InHouse)Inventory.getAllParts().get(idCounter)).getMachineID()));
        }
        else {
            outSourcedRadio.setSelected(true);
            partType.setText("Company Name");
            companyOrMachineID.setText(((Outsourced)Inventory.getAllParts().get(idCounter)).getCompanyName());
        }

    }
    /**
     * Initializes the controller class.
     */
    @FXML
    private void setOutsourced(MouseEvent event){
        partType.setText("Company Name");
        boolInHouse = false;
    }
    
    @FXML
    private void setInHouse(MouseEvent event){
        partType.setText("Machine ID");
        boolInHouse = true;
    }
    
    @FXML
    private void savePart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
     
        int partMin = Integer.parseInt(min.getText());
        int partMax = Integer.parseInt(max.getText());
        String partName = name.getText();
        int partInven = Integer.parseInt(inven.getText());
        double partPrice = Double.parseDouble(price.getText());
        String pType = companyOrMachineID.getText();
        
        try {
            exceptMsg = Part.compareMinMax(partMin, partMax, partInven);
            
            if (exceptMsg.length() > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to add Part");
                alert.setContentText(exceptMsg);
                alert.showAndWait();                
            }
            else{
                if (boolInHouse) {
                    int machineID = Integer.parseInt(pType);
                    InHouse part = new InHouse(idCounter + 1, partName, partPrice, partInven, partMin, partMax, machineID);
                    Inventory.updatePart(idCounter, part);
                }
                else {
                    String companyName = pType;
                    Outsourced part = new Outsourced(idCounter + 1, partName, partPrice, partInven, partMin, partMax, companyName);
                    Inventory.updatePart(idCounter, part);
                }
            }
        }
        catch (NumberFormatException except) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
