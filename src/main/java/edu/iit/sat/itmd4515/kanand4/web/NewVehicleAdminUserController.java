/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

import edu.iit.sat.itmd4515.kanand4.domain.Vehicle;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleBooking;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import edu.iit.sat.itmd4515.kanand4.security.User;
import edu.iit.sat.itmd4515.kanand4.security.UserService;
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
public class NewVehicleAdminUserController {

    private static final Logger LOG = Logger.getLogger(NewVehicleAdminUserController.class.getName());

    private VehicleAdmin vehicleAdmin;
    

    @EJB
    private UserService userSvc;

    /**
     * Constructor
     */
    public NewVehicleAdminUserController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the NewVehicleAdminUserController");
        vehicleAdmin = new VehicleAdmin();
        vehicleAdmin.setUser(new User());
    }

    /**
     * Sign up New Hotel Admin User
     *
     * @return
     */
    public String signUpNewVehicleAdminUser() {
        userSvc.signUpNewVehicleAdminUser(vehicleAdmin);
        return "login.xhtml?faces-redirect=true";
    }

  

    public VehicleAdmin getVehicleAdmin() {
        return vehicleAdmin;
    }

    public void setVehicleAdmin(VehicleAdmin vehicleAdmin) {
        this.vehicleAdmin = vehicleAdmin;
    }
}
