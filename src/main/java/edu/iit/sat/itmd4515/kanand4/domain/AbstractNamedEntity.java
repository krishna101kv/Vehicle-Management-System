/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 *
 * @author kris
 */
@MappedSuperclass
//public class AbstractNamedEntity extends AbstractEntity {
public class AbstractNamedEntity  extends AbstractEntity{
    
    @Column(name = "FIRSTNAME")
    protected String firstName;
    @Column(name = "LASTNAME")
    protected String lastName;

    public AbstractNamedEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
