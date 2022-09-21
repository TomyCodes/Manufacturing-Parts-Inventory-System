/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Tomy Li He
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    // Counter for auto increment IDs
    private static int partCounter = 0;
    private static int productCounter = 0;
    
    /**
     *
     */
    public Inventory() {}
    
    /**
     *
     * @return the partID
     */
    public static int setPartID(){
        partCounter++;
        return partCounter;
    }
    
    /**
     *
     * @return productID
     */
    public static int setProductID(){
        productCounter++;
        return productCounter;
    }
    
    /**
     *
     * @param newPart added to allParts
     */
    public static void addPart(Part newPart){ 
        allParts.add(newPart);   
    }
    
    /**
     *
     * @param newProduct added to allProducts
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);   
    }
    
    /**
     *
     * @param partId
     * @return part by partId else if not found return null
     */
    public static Part lookupPart(int partId) {
        if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getId() == partId) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }
    
    /**
     *
     * @param productId
     * @return product by productId else if not found return null
     */
    public static Product lookupProduct(int productId) {
        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getProductId() == productId) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }

    /**
     *
     * @param partName
     * @return part by name
     */
    public static ObservableList<Part> lookupPart(String partName){
         
        ObservableList<Part> results = FXCollections.observableArrayList();
        
        if(!allParts.isEmpty()){
            for (int i = 0; i < allParts.size(); i++){
                if(allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())){
                    results.add(allParts.get(i));
                }
            }
        } else{
            return results;
        }
        return results;
    } 
    
    /**
     *
     * @param prodName
     * @return product by name
     */
    public static ObservableList<Product> lookupProduct(String prodName){
     ObservableList<Product> results = FXCollections.observableArrayList();
    
        if(!allProducts.isEmpty()){
            for (int i = 0; i < allProducts.size(); i++){
                if(allProducts.get(i).getName().toLowerCase().equals(prodName.toLowerCase())){
                    results.add(allProducts.get(i));
                }
            }
        } else {
        return results;
        }
        
        return results;
    }    
    
    /**
     *
     * @param index
     * @param selectedPart is updated
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    
    /**
     *
     * @param index
     * @param newProduct is updated
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
    
    /**
     *
     * @param selectedPart
     * @return allParts after removing selectedPart
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    
    /**
     *
     * @param selectedProduct
     * @return allProducts after removing selectedProduct
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }
    
    /**
     *
     * @return all parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    /**
     *
     * @return all products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    
    /**
     *
     * @param part
     * @return associated part names
     */
    public static String checkAssociatedParts(Part part){
        for(int i = 0; i < allParts.size() ; i++){
            if(allProducts.get(i).getAllAssociatedParts().contains(part)){
                return allProducts.get(i).getName();
            }
        }
        return "";
    }
}
