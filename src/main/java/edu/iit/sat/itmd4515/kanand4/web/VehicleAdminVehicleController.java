/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

import edu.iit.sat.itmd4515.kanand4.domain.Vehicle;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleLocation;
import edu.iit.sat.itmd4515.kanand4.service.VehicleAdminService;
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

public class VehicleAdminVehicleController {

    private static final Logger LOG = Logger.getLogger(VehicleAdminVehicleController.class.getName());

    private Vehicle vehicle;

    @EJB
    VehicleService vehicleSvc;
    @EJB VehicleAdminService vaSvc;
    @Inject
    VehicleAdminWelcomeController vawc;

    public VehicleAdminVehicleController() {
    }

    public String displayReadVehiclePage(Vehicle v) {
        LOG.info("Inside displayReadVehicleAdminPage" + v.toString());
        this.vehicle = v;
        return "/vehicleAdmin/readVehicle.xhtml";
    }

    public String displayUpdateVehiclePage(Vehicle v) {
        LOG.info("Inside displayUpdateVehicleAdminPage" + v.toString());
        this.vehicle = v;
        return "/vehicleAdmin/updateVehicle.xhtml";
    }

    public String displayDeleteVehiclePage(Vehicle v) {
        LOG.info("Inside displayDeleteVehicleAdminPage" + v.toString());
        this.vehicle = v;
        return "/vehicleAdmin/deleteVehicle.xhtml";
    }
    
    
    
    

    @PostConstruct
    private void postConstruct() {
        LOG.info("VehicleController.postConstruct() -> Initializing Vehicle Model");
        vehicle = new Vehicle();

    }

    public String executeCreateButtonClick() {
        LOG.info("VehicleController.saveVehicle() -> Invoking the application on button click");
        LOG.info("Vehicle Model has following values : ");
        LOG.info("\t" + vehicle.toString());

        vaSvc.create(vehicle);
        //  vehicleSvc.createVehicleForVehicleAdmin(vehicle, vawc.getVehicleAdmin());
        //vaSvc.createVehicleForVehicleAdmin(vehicle,vawc.getVehicleAdmin());
        
        
        LOG.info("Vehicle Model has following values after saving in database: ");
        LOG.info("\t" + vehicle.toString());

        return "/vehicleAdmin/welcome.xhtml";
    }

    public String executeUpdateButtonClick() {
        LOG.info("Inside updateVehicle" + vehicle.toString());
        vaSvc.updateVehicleForVehicleAdmin(vehicle);
        return "/vehicleAdmin/welcome.xhtml";
    }

    public String executeDeleteButtonClick() {
        LOG.info("Inside deleteVehicle " + vehicle.toString());
        Vehicle v = vehicleSvc.read(vehicle.getId());
        vehicle = v;
        LOG.info("Inside deleteVehicle after set " + vehicle.toString());
        vaSvc.deleteVehicleForVehicleAdmin(vehicle);
        return "/vehicleAdmin/welcome.xhtml";
    }

    //helper method
    public VehicleLocation[] getallVehicleLocation() {
        return VehicleLocation.values();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
