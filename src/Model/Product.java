/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
/**
 *
 * @author Tomy Li He
 */
public class Product {

    /**
     *
     */
    protected IntegerProperty productID;
    private String name;
    private double price;
    private int productStock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    /**
     *
     * @param productID
     * @param name
     * @param price
     * @param productStock
     * @param min
     * @param max
     */
    public Product(int productID, String name, double price, int productStock, int min, int max) {
        this.productID = new SimpleIntegerProperty(productID);
        setName(name);
        setPrice(price);
        setStock(productStock);
        setMin(min);
        setMax(max);
    }

    private void setProductID(int productID) {
        this.productID.set(productID);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    private void setStock(int productStock) {
        this.productStock = productStock;
    }

    private void setMin(int min) {
        this.min = min;
    }

    private void setMax(int max) {
        this.max = max;
    }
    
    /**
     *
     * @param associatedParts the associated parts to set
     */
    public void setAssociatedParts(ObservableList associatedParts) {
        this.associatedParts = associatedParts;
    }
    
    /**
     *
     * @return the productID
     */
    public int getProductId() {
        return this.productID.get();
    }
    
    /**
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }
    
    /**
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }    
    
    /**
     *
     * @return the stock
     */
    public int getStock() {
        return productStock;
    }
    
    /**
     *
     * @return the min
     */
    public int getMin() {
        return min;
    }
    
    /**
     *
     * @return the max
     */
    public int getMax() {
        return max;
    }
    
    /**
     *
     * @return associated parts
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    /**
     *
     * @param addingPart is added to associatedParts
     */
    public void addAssociatedPart(Part addingPart){
        associatedParts.add(addingPart);
    }
    
    /**
     *
     * @param removePart is removed from associatedParts
     * @return false if not found
     */
    public boolean deleteAssociatedPart(Part removePart){
        if (associatedParts.contains(removePart)){
            associatedParts.remove(removePart);
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     *
     * @return associated parts
     */
    public ObservableList getAllAssociatedParts(){
        return this.associatedParts;
    }
    
    /**
     *
     * @param min
     * @param max
     * @param inven
     * @return error message if min is greater than max
     */
    public static String compareMinMax(int min, int max, int inven) {
        return (inven > min && inven < max && min < max) ? "" : "The min value cannot be greater than the max value or the inven level must be between the min and max.";
    }
    
    /**
     *
     * @return productID
     */
    public IntegerProperty idProperty() {
        return productID;
    }
} 
