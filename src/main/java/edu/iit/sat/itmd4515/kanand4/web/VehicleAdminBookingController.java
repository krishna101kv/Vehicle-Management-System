/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

import edu.iit.sat.itmd4515.kanand4.domain.Vehicle;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleBooking;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import edu.iit.sat.itmd4515.kanand4.service.VehicleBookingService;
import edu.iit.sat.itmd4515.kanand4.service.VehicleService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author kris
 */
@Named
@RequestScoped
public class VehicleAdminBookingController {

    private static final Logger LOG = Logger.getLogger(VehicleAdminBookingController.class.getName());

    private VehicleBooking vehicleBooking;
    
    @Inject
    VehicleAdminWelcomeController vawc;
    
    @EJB 
    VehicleBookingService vbs;
    
    @EJB
    VehicleService vehicleSvc;

    public VehicleAdminBookingController() {
        
    }

    
    public void initBookingById(){
        LOG.info("Inside initBookingById " + this.vehicleBooking.toString());
        this.vehicleBooking= vbs.read(vehicleBooking.getId());
        
         LOG.info("Outside initBookingById " + this.vehicleBooking.toString());
    }
    
    
    
    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside VehicleAdminBookingController.postConstruct");
        vehicleBooking = new VehicleBooking();
        vehicleBooking.setVehicle(new Vehicle());
        vehicleBooking.setVehicleAdmin(vawc.getVehicleAdmin());
        vehicleBooking.setVehicleCustomer(new VehicleCustomer());
        
    }

    
    public void initVehicleById(){
        Vehicle  v=vehicleSvc.read(this.vehicleBooking.getVehicle().getId());
        this.vehicleBooking.setVehicle(v);
    }
    
    
    
    /**
     * Displays Vehicle Booking
     * @return
     */
    public String displayBookingSchedulePage(Vehicle v) {
         LOG.info("Inside displayBookingSchedulePage with " + v.toString());
         vehicleBooking.setVehicle(v);

        return "/vehicleAdmin/scheduleBooking.xhtml";
    }
    
    public String executeCreateButtonClick(){
        
        LOG.info("VehicleAdminBookingController.executeCreateButtonClick " + this.vehicleBooking.toString());
        LOG.info("VehicleAdminBookingController.executeCreateButtonClick " + this.vehicleBooking.getVehicle().toString());
        LOG.info("VehicleAdminBookingController.executeCreateButtonClick "+ this.vehicleBooking.getVehicleAdmin().toString());
        LOG.info("VehicleAdminBookingController.executeCreateButtonClick "+ this.vehicleBooking.getVehicleCustomer().toString());
        
        //appointment
        vbs.scheduleBooking(vehicleBooking);
        
         return "/vehicleAdmin/welcome.xhtml?faces-redirect=true;";
    }
    
    public String executeCancelBookingButtonClick() {
        LOG.info("Inside VehicleAdminBookingController executeCancelBookingButtonClick with " + this.vehicleBooking.toString());

        vbs.cancelVehicleBooking(vehicleBooking);

        return "/vehicleAdmin/welcome.xhtml?faces-redirect=true";
    }

    /**
     * Change button click
     *
     * @return
     */
    public String executeChangeBookingButtonClick() {
        LOG.info("Inside HotelAdminBookingController executeChangeBookingButtonClick with " + this.vehicleBooking.toString());

        vbs.changeVehicleBooking(vehicleBooking);

        return "/hotelAdmin/welcome.xhtml?faces-redirect=true";
    }
    
    
    
    
    
    public VehicleBooking getVehicleBooking() {
        return vehicleBooking;
    }

    public void setVehicleBooking(VehicleBooking vehicleBooking) {
        this.vehicleBooking = vehicleBooking;
    }

}
