/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.service.VehicleAdminService;
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
public class VehicleAdminWelcomeController {

    private static final Logger LOG = Logger.getLogger(VehicleAdminWelcomeController.class.getName());

    @EJB VehicleAdminService vehicleAdminSvc;
    @Inject LoginController loginController;
    
    private VehicleAdmin vehicleAdmin; 
    public VehicleAdminWelcomeController() {
    }
  
    @PostConstruct
    private void PostConstruct(){
        LOG.info("Inside VehicleAdminWelcomeController.postConstruct");
        LOG.info(loginController.getAuthenticatedUser());
        //vehicleAdmin = vehicleAdminSvc.findByUserName(loginController.getAuthenticatedUser());
        vehicleAdmin = vehicleAdminSvc.findByUserName(loginController.getAuthenticatedUser());
        LOG.info("Exiting VehicleAdminWelcomeController with " + vehicleAdmin.toString());
    }
    
    
    public void refreshVehicleAdmin(){
        vehicleAdmin = vehicleAdminSvc.findByUserName(loginController.getAuthenticatedUser());
    }

    public VehicleAdmin getVehicleAdmin() {
        return vehicleAdmin;
    }
    
    public void setVehicleAdmin(VehicleAdmin vehicleAdmin) {
        this.vehicleAdmin = vehicleAdmin;
    }
    
}

