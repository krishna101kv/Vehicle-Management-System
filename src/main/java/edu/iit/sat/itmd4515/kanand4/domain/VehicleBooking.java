/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author kris
 */
@Entity
@Table(name = "BOOKING")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "VehiclsBooking.findAll", query = "select b from VehicleBooking b")
@NamedQuery(name = "VehicleBooking.findVehicleBookingByVehicle", query = "select b from VehicleBooking b where b.vehicle.id = :ID")
public class VehicleBooking {

    private static final Logger LOG = Logger.getLogger(VehicleBooking.class.getName());

    @Column(name = "BOOKING_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOKING_DATE")
    private LocalDate bookingDate;

    @Column(name = "BOOKING_TIME")
    private LocalTime BookingTime;

    @Column(name = "BOOKING_TYPE")
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    public VehicleBooking(LocalDate bookingDate, LocalTime BookingTime, VehicleType type) {
        this.bookingDate = bookingDate;
        this.BookingTime = BookingTime;
        this.type = type;
    }

    public VehicleBooking(LocalDate of, LocalTime of0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void scheduleVehicleBooking(Vehicle h, VehicleAdmin ha, VehicleCustomer c) {
        this.vehicle = h;
        this.vehicleAdmin = ha;
        this.vehicleCustomer = c;

        if (!c.getBooking().contains(this)) {
            c.getBooking().add(this);
        }
        if (!ha.getBooking().contains(this)) {
            ha.getBooking().add(this);
        }
    }

    public void cancelVehicleBooking() {
        if (this.vehicleCustomer.getBooking().contains(this)) {
            this.vehicleCustomer.getBooking().remove(this);
        }
        if (this.vehicleAdmin.getBooking().contains(this)) {
            this.vehicleAdmin.getBooking().remove(this);
        }

        this.vehicle = null;
        this.vehicleAdmin = null;
        this.vehicleCustomer = null;
    }

    public VehicleBooking() {
    }

    //Uni-directional relationship between Booking and Customer
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private VehicleCustomer vehicleCustomer;

    //Uni-directional relationship between Booking and Admin
    @ManyToOne
    @JoinColumn(name = "ADMIN_ID")
    private VehicleAdmin vehicleAdmin;

    //Uni-directional relationship between Booking and Vechile
    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    //helper method for relationship
    public void addCustomer(VehicleCustomer vc) {
        this.vehicleCustomer = vc;
        if (!vehicleCustomer.getBooking().contains(this)) {
            vehicleCustomer.getBooking().add(this);
        }
    }

    public void removeCustomer(VehicleCustomer vc) {
        this.vehicleCustomer = null;
        if (vehicleCustomer.getBooking().contains(this)) {
            vehicleCustomer.getBooking().remove(this);
        }
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(LocalTime BookingTime) {
        this.BookingTime = BookingTime;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final VehicleBooking other = (VehicleBooking) obj;
        if ((this.id == null) || (other.id == null)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "VehicleBooking{" + "id=" + id + ", bookingDate=" + bookingDate + ", BookingTime=" + BookingTime + ", type=" + type + '}';
    }

    public VehicleCustomer getVehicleCustomer() {
        return vehicleCustomer;
    }

    public void setVehicleCustomer(VehicleCustomer vehicleCustomer) {
        this.vehicleCustomer = vehicleCustomer;
    }

    public VehicleAdmin getVehicleAdmin() {
        return vehicleAdmin;
    }

    public void setVehicleAdmin(VehicleAdmin vehicleAdmin) {
        LOG.info("Inside VehicleBooking.setVehicleAdmin Admin: " + vehicleAdmin.toString());
        
        this.vehicleAdmin = vehicleAdmin;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
