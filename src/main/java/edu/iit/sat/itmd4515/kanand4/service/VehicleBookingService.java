/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.service;

import edu.iit.sat.itmd4515.kanand4.domain.Vehicle;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleBooking;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author kris
 */
@Stateless
public class VehicleBookingService extends AbstractService<VehicleBooking>{
    
    public VehicleBookingService(){
        super(VehicleBooking.class);
        
    }
    
     public List<VehicleBooking> findAll(){
        return super.findAll("VehicleBooking.findAll");
    }
     
    public void scheduleBooking(VehicleBooking vehicleBooking){
        VehicleBooking newBooking=new VehicleBooking();
        newBooking.setBookingDate(vehicleBooking.getBookingDate());
        
        newBooking.setBookingTime(vehicleBooking.getBookingTime());
        newBooking.setType(vehicleBooking.getType());
        
        newBooking.scheduleVehicleBooking(em.getReference(Vehicle.class, vehicleBooking.getVehicle().getId()),
                em.getReference(VehicleAdmin.class, vehicleBooking.getVehicleAdmin().getId())
                ,  em.getReference(VehicleCustomer.class,vehicleBooking.getVehicleCustomer().getId() ));
        
        
        em.persist(newBooking);
        
    }
    
     public void changeVehicleBooking(VehicleBooking vehicleBooking) {

        VehicleBooking managedBooking = em.getReference(VehicleBooking.class, vehicleBooking.getId());
        managedBooking.setBookingDate(vehicleBooking.getBookingDate());
        managedBooking.setBookingTime(vehicleBooking.getBookingTime());
        managedBooking.setType(vehicleBooking.getType());

        //Cannot change so removing and scheduling again
        managedBooking.cancelVehicleBooking();
        managedBooking.scheduleVehicleBooking(
                em.getReference(Vehicle.class, vehicleBooking.getVehicle().getId()),
                em.getReference(VehicleAdmin.class, vehicleBooking.getVehicleAdmin().getId()),
                em.getReference(VehicleCustomer.class, vehicleBooking.getVehicleCustomer().getId()));

        em.persist(managedBooking);
    }

    /**
     * Cancel vehicle booking
     *
     * @param Vehicle booking 
     */
    public void cancelVehicleBooking(VehicleBooking vehicleBooking) {
        vehicleBooking = em.getReference(VehicleBooking.class, vehicleBooking.getId());

        vehicleBooking.cancelVehicleBooking();
        em.remove(vehicleBooking);
    }
}
