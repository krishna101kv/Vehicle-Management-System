/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.domain;

import edu.iit.sat.itmd4515.kanand4.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kris
 */
@Entity
@Table(name = "CUSTOMER")
@NamedQuery(name = "VehicleCustomer.findAll", query = "select c from VehicleCustomer c")
@NamedQuery(name="VehicleCustomer.findCustomerByUserName",query = "select c from VehicleCustomer c where c.user.userName= :username ")
public class VehicleCustomer {

    @Column(name = "CUSTOMER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "CUSTOMER_FIRSTNAME")
    private String firstName;

    @Column(name = "CUSTOMER_LASTNAME")
    private String lastName;

    @Column(name = "CUSTOMER_EMAIL")
    private String email;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public VehicleCustomer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public VehicleCustomer(String firstName) {
        this.firstName = firstName;
    }

    public VehicleCustomer() {
    }

    public VehicleCustomer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //Bi-Directional M:M relationship between Customer and Vehicle
    @ManyToMany
    @JoinTable(name = "CUSTOMER_VEHICLE", joinColumns = @JoinColumn(name = "CUSTOMER_ID"),
            inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID"))
    private List<Vehicle> vehicle = new ArrayList<>();

    @OneToMany(mappedBy = "vehicleCustomer")
    private List<VehicleBooking> booking = new ArrayList<>();

    //helper method for relationship
    public void addVehicle(Vehicle v) {
        if (!this.getVehicle().contains(v)) {
            this.getVehicle().add(v);
        }
        if (!v.getCustomer().contains(this)) {
            v.getCustomer().add(this);
        }
    }

    public void removeVehicle(Vehicle v) {
        if (this.getVehicle().contains(v)) {
            this.getVehicle().remove(v);
        }
        if (v.getCustomer().contains(this)) {
            v.getCustomer().remove(this);
        }
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleCustomer other = (VehicleCustomer) obj;
        if ((this.id == null) || (other.id == null)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "VehicleCustomer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }

    public List<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    public List<VehicleBooking> getBooking() {
        return booking;
    }

    public void setBooking(List<VehicleBooking> booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}


/*
//helper method for relationship
    public void addVehicle(Vehicle v)
    {
        if(!this.getVehicle().contains(v))
        {
            this.getVehicle().add(v);
        }
        if(!v.getCustomer().contains(this))
        {
            v.getCustomer().add(this);
        }
    }
    
    public void removeVehicle(Vehicle v) {
        if (this.getVehicle().contains(v)) 
        {
            this.getVehicle().remove(v);
        }
        if (v.getCustomer().contains(this))
        { 
            v.getCustomer().remove(this);
        }
    }
*/
