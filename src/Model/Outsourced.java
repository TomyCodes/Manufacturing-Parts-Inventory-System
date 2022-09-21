/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Tomy Li He
 */
public final class Outsourced extends Part{
    
    private StringProperty companyName;

    /**
     *
     * @param id
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int inStock, int min, int max, String companyName) {
        
        this.id = new SimpleIntegerProperty(id);
        setName(name);
        setPrice(price);
        setStock(inStock);
        setMin(min);
        setMax(max);
        this.companyName = new SimpleStringProperty(companyName);
    }
    
    /**
     *
     * @return the company name
     */
    public String getCompanyName() {
        return this.companyName.get();
    }
    
    /**
     *
     * @param name the name to set
     */
    public void setCompanyName(String name) {
        this.companyName.set(name);
    }
}
