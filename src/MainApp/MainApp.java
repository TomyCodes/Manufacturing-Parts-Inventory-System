/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package MainApp;

import Model.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 *
 * @author Tomy Li He
 * Main java doc file is located in the dist folder
 */
public class MainApp extends Application{
    Inventory inven = new Inventory();
   
    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        launch(args);
    }
     
    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        insertData(inven);
        
        Parent root = FXMLLoader.load(getClass().getResource("/View/Mainform.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
                
    }
    
    void insertData(Inventory inven){
        Part part1 = new InHouse(Inventory.setPartID(), "Part 1", 4.99, 55, 50, 100, 100);
        Part part2 = new InHouse(Inventory.setPartID(), "Part 2", 2.99, 22, 20, 35, 101);
        Part part3 = new InHouse(Inventory.setPartID(), "Part 3", 7.99, 5, 3, 25, 102);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(new InHouse(Inventory.setPartID(), "Part 4", 3.99, 10, 5, 50, 103));
        Inventory.addPart(new InHouse(Inventory.setPartID(), "Part 5", 6.99, 15, 8, 24, 104));
        
        Part out1 = new Outsourced(Inventory.setPartID(), "Part 6", 14.99, 15, 10, 32, "Company 1");
        Part out2 = new Outsourced(Inventory.setPartID(), "Part 7", 12.99, 11, 8, 12, "Company 2");
        Part out3 = new Outsourced(Inventory.setPartID(), "Part 8", 11.99, 15, 12, 24, "Company 1");
        Inventory.addPart(out1);
        Inventory.addPart(out2);
        Inventory.addPart(out3);
        Inventory.addPart(new Outsourced(Inventory.setPartID(), "Part 9", 8.99, 13, 10, 22, "Company 2"));
        Inventory.addPart(new Outsourced(Inventory.setPartID(), "Part 10", 10.99, 5, 11, 23, "Company 2"));
        
        Product prod1 = new Product(Inventory.setProductID(), "Product 1", 5.99, 17, 16, 20);
        Product prod2 = new Product(Inventory.setProductID(), "Product 2", 6.99, 18, 17, 25);
        Product prod3 = new Product(Inventory.setProductID(), "Product 3", 7.99, 19, 18, 30);
        prod1.addAssociatedPart(part1);
        prod2.addAssociatedPart(part2);
        prod3.addAssociatedPart(part3);
        prod1.addAssociatedPart(out1);
        prod2.addAssociatedPart(out2);
        prod3.addAssociatedPart(out3);
        Inventory.addProduct(prod1);
        Inventory.addProduct(prod2);
        Inventory.addProduct(prod3);
    }
}
