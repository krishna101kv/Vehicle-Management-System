/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.domain;

/**
 *
 * @author kris
 */
public enum VehicleLocation {
    Chicago("Chicago"),
    Florida("Florida"),
    Dallas("Dallas"),
    NewYork("NewYork"),
    LosAngeles("LosAngeles"),
    SanFrancisco("SanFrancisco  ");
    
    private String description;

    private VehicleLocation(String description) {
        this.description = description;
    }

    
    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
