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
import java.util.logging.Logger;

/**
 *
 * @author kris
 */
@Entity
@Table(name = "ADMIN")
@NamedQuery(name = "Admin.findAll", query = "select a from VehicleAdmin a")
@NamedQuery(name = "Admin.findByUserName", query = "select a from VehicleAdmin a where a.user.userName = :userName")

public class VehicleAdmin {

    private static final Logger LOG = Logger.getLogger(VehicleAdmin.class.getName());

    @Column(name = "ADMIN_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "ADMIN_FIRSTNAME")
    private String firstName;

    @Column(name = "ADMIN_LASTNAME")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;
    public VehicleAdmin() {
    }

    //@OneToOne
    //private Vehicle vehicle;

    @OneToMany
    @JoinTable(name = "ADMIN_VEHICLE", joinColumns = @JoinColumn(name = "ADMIN_ID"),
            inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID"))
    private List<Vehicle> vehicle;


    
    @OneToMany(mappedBy = "vehicleAdmin")
    private List<VehicleBooking> booking = new ArrayList<>();

    public VehicleAdmin(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public VehicleAdmin(String firstName) {
        this.firstName = firstName;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final VehicleAdmin other = (VehicleAdmin) obj;
        if ((this.id == null) || (other.id == null)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "VehicleAdmin{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }

    public void addVehicle(Vehicle v) {
        LOG.info("Inside VehicleAdmin.addvehicle: " + v.toString());
        LOG.info("Inside VehicleAdmin.addvehicle: " + this.toString());
        if (!this.getVehicle().contains(v)) {
            this.getVehicle().add(v);
        }
        LOG.info("Inside VehicleAdmin.addvehicle: " + v.toString());
        
        v.setVehicleAdmin(this);
        LOG.info("Inside VehicleAdmin.addvehicle: " + v.toString());
    }

    public void removeVehicle(Vehicle v) {
        LOG.info("Inside VehicleAdmin.removeVehicle: " + v.toString());
        if (this.getVehicle().contains(v)) {
            LOG.info("Inside VehicleAdmin.removeVehicle inside if: " + v.toString());
            this.getVehicle().remove(v);
        }
        
        //v.setVehicleAdmin();
    
    }
    
    public List<Vehicle> getVehicle() {
        return vehicle;
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
    
    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

}
