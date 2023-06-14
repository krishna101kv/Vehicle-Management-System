/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import edu.iit.sat.itmd4515.kanand4.service.VehicleAdminService;
import edu.iit.sat.itmd4515.kanand4.service.VehicleCustomerService;
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
public class VehicleCustomerWelcomeController {

    private static final Logger LOG = Logger.getLogger(VehicleCustomerWelcomeController.class.getName());

    @EJB VehicleCustomerService vcs;
    @Inject LoginController loginController;
    
    private VehicleCustomer vehicleCustomer; 
    public VehicleCustomerWelcomeController() {
    }
  
    @PostConstruct
    private void PostConstruct(){
        LOG.info("Inside VehicleCustomerWelcomeController.postConstruct");
        LOG.info(loginController.getAuthenticatedUser());
        //vehicleAdmin = vehicleAdminSvc.findByUserName(loginController.getAuthenticatedUser());
        vehicleCustomer = vcs.findCustomerByUserName(loginController.getAuthenticatedUser());
        LOG.info("Exiting VehicleCustomerWelcomeController with " + vehicleCustomer.toString());
    }
    
    
    public void refreshVehicleCustomer(){
        vehicleCustomer = vcs.findCustomerByUserName(loginController.getAuthenticatedUser());
    }
    


    public VehicleCustomerService getVcs() {
        return vcs;
    }

    public void setVcs(VehicleCustomerService vcs) {
        this.vcs = vcs;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public VehicleCustomer getVehicleCustomer() {
        return vehicleCustomer;
    }

    public void setVehicleCustomer(VehicleCustomer vehicleCustomer) {
        this.vehicleCustomer = vehicleCustomer;
    }
    
}

