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
public class InHouse extends Part{

    /**
     *
     */
    protected IntegerProperty machineID;
    
    /**
     *
     * @param id
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param machineID
     */
    public InHouse(int id, String name, double price, int inStock, int min, int max, int machineID) {
        
        this.id = new SimpleIntegerProperty(id);
        setName(name);
        setPrice(price);
        setStock(inStock);
        setMin(min);
        setMax(max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }
    
    /**
     *
     * @return the machine id
     */
    public int getMachineID() {
        return this.machineID.get();
    }
    
    /**
     *
     * @param id the id to set
     */
    public void setMachineID(int id) {
        this.machineID.set(id);
    }
}
