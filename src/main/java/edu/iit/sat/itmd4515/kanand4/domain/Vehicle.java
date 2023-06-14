/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.domain;
import edu.iit.sat.itmd4515.kanand4.service.VehicleAdminService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author kris
 */
@Entity
@Table(name = "VEHICLE")
@NamedQuery(name = "Vehicle.findByName", query = "select v from Vehicle v where v.vehicleName = :vehicleName")
@NamedQuery(name = "Vehicle.findAll", query = "select v from Vehicle v")
public class Vehicle extends AbstractNamedEntity {

    private static final Logger LOG = Logger.getLogger(Vehicle.class.getName());

    
    @Column(name = "VEHICLE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotBlank
    @Column(name = "VEHICLE_NAME", nullable = false, unique = true, length = 30)

    private String vehicleName;
    @Column(name = "VEHICLE_BOOKDATE")
    private LocalDate vehicleBookDate;

    @Positive
    @Column(name = "VEHICLE_PRICE")
    private Integer vehiclePrice;
    @Enumerated(EnumType.STRING)
    private VehicleLocation vehicleLocation;
    
    @ManyToOne
    @JoinTable(name = "ADMIN_VEHICLE", joinColumns = @JoinColumn(name = "ADMIN_ID"),
            inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID"))
   

    private VehicleAdmin vehicleAdmin;

    public Vehicle() {

    }

    //@OneToOne(mappedBy = "vehicle")
    //private VehicleAdmin vehicleAdmin;

    @ManyToMany(mappedBy = "vehicle")
    private List<VehicleCustomer> customer = new ArrayList<>();


    public Vehicle(String vehicleName, LocalDate vehicleBookDate, Integer vehiclePrice, VehicleLocation vehicleLocation) {
        this.vehicleName = vehicleName;
        this.vehicleBookDate = vehicleBookDate;
        this.vehiclePrice = vehiclePrice;
        this.vehicleLocation = vehicleLocation;
    }

    public Vehicle(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Vehicle(Long id, String vehicleName, LocalDate vehicleBookDate, Integer vehiclePrice, VehicleLocation vehicleLocation, VehicleAdmin vehicleAdmin) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.vehicleBookDate = vehicleBookDate;
        this.vehiclePrice = vehiclePrice;
        this.vehicleLocation = vehicleLocation;
        this.vehicleAdmin = vehicleAdmin;
    }

    public Vehicle(String vehicleName, LocalDate vehicleBookDate, Integer vehiclePrice, VehicleLocation vehicleLocation, VehicleAdmin vehicleAdmin) {
        this.vehicleName = vehicleName;
        this.vehicleBookDate = vehicleBookDate;
        this.vehiclePrice = vehiclePrice;
        this.vehicleLocation = vehicleLocation;
        this.vehicleAdmin = vehicleAdmin;
    }
    public void delVehicle(){
        LOG.info("Inside delVehicle : " + this.toString());
        
        /*if(this.vehicleAdmin.getBooking().contains(this)){
            this.vehicleAdmin.getBooking().remove(this);   
        }*/
        if(this.vehicleAdmin != null){
            LOG.info(" Vehicle.delVehicle Admin not null: " + vehicleAdmin.toString());
            this.vehicleAdmin.removeVehicle(this);
        }
            
        LOG.info(" delVehicle Admin deleted: " + this.toString());
            
        this.vehicleAdmin = null;
        //this.vehicleBookDate= null;
        //this.vehicleBookDate = null;
        //this.vehicleName = null;
        //this.vehicleLocation = null;
     LOG.info(" delVehicle Admin deleted: " + this.toString());   
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

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public LocalDate getVehicleBookDate() {
        return vehicleBookDate;
    }

    public void setVehicleBookDate(LocalDate vehicleBookDate) {
        this.vehicleBookDate = vehicleBookDate;
    }

    public Integer getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(Integer vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public VehicleLocation getVehicleLocation() {
        return vehicleLocation;
    }

    public void setVehicleLocation(VehicleLocation vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
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

        final Vehicle other = (Vehicle) obj;
        if ((this.id == null) || (other.id == null)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    public List<VehicleCustomer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<VehicleCustomer> customer) {
        this.customer = customer;
    }

    public VehicleAdmin getVehicleAdmin() {
        return vehicleAdmin;
    }

    public void setVehicleAdmin(VehicleAdmin vehicleAdmin) {
        LOG.info("Inside Vehicle.setVehicleAdmin Admin: " + vehicleAdmin.toString());
        this.vehicleAdmin = vehicleAdmin;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", vehicleName=" + vehicleName + ", vehicleBookDate=" + vehicleBookDate + ", vehiclePrice=" + vehiclePrice + ", vehicleLocation=" + vehicleLocation + ", vehicleAdmin=" + vehicleAdmin + ", customer=" + customer + '}';
    }
    
}
