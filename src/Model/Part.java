/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 *
 * @author Tomy Li He
 */
public abstract class Part {

    /**
     *
     */
    protected IntegerProperty id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    /**
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = new SimpleIntegerProperty(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    /**
     *
     */
    public Part(){
    }
    /**
     * @return the id
     */
    public int getId() {
        return this.id.get();
    }

    /**
     * @param id the id to set
     */
    public final void setId(int id) {
        this.id.set(id);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     *
     * @param min
     * @param max
     * @param inven
     * @return potential error message if entered values of min, max, and inven are not within range
     */
    public static String compareMinMax(int min, int max, int inven) {
        return (inven > min && inven < max && min < max) ? "" : "The min value cannot be greater than the max value or the inven level must be between the min and max";
    }
    
    /**
     *
     * @return
     */
    public IntegerProperty idProperty() {
        return id;
    }
    
}
