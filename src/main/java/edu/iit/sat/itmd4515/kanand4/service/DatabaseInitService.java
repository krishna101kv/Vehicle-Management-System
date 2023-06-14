/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.service;

import edu.iit.sat.itmd4515.kanand4.domain.Vehicle;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleBooking;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleLocation;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleType;
import edu.iit.sat.itmd4515.kanand4.security.Group;
import edu.iit.sat.itmd4515.kanand4.security.GroupService;
import edu.iit.sat.itmd4515.kanand4.security.User;
import edu.iit.sat.itmd4515.kanand4.security.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

/**
 *
 * @author kris
 */
@Startup
@Singleton
public class DatabaseInitService {

    private static final Logger LOG = Logger.getLogger(DatabaseInitService.class.getName());
    
    //@PersistenceContext(name = "itmd4515PU")
    //private EntityManager em;
    
    @EJB private VehicleAdminService vaService;
    @EJB private VehicleBookingService vbService;
    @EJB private VehicleCustomerService vcService;
    @EJB private VehicleService vService;
    @EJB private UserService userSvc;
    @EJB private GroupService groupSvc;
    
    
    

    public DatabaseInitService() {
        
        
    }
    @PostConstruct
    private void postConstruct(){
        LOG.info("DatabaseInitService.postConstruct");
        
        //SECURITY REALM / IDENTITY STORE DATA POPULATION
        
       
        
        Group adminGroup = new Group("ADMIN_GROUP","This is a security realm group representing system administrators");
        Group vehicleAdminGroup = new Group("VEHICLEADMIN_GROUP", "This group represents admin in the security realm");
        Group customerGroup = new Group("CUSTOMER_GROUP","This is a security realm group representing Vehicle customers");
        
        groupSvc.create(adminGroup);
        groupSvc.create(vehicleAdminGroup);
        groupSvc.create(customerGroup);
        
        
        User admin = new User("admin","admin",true);
        admin.addGroup(adminGroup);
        userSvc.create(admin);
        
        User vehicleCustomer1 = new User("customer1","customer1",true);
        vehicleCustomer1.addGroup(customerGroup);
        vehicleCustomer1.addGroup(adminGroup);
        
        User vehicleCustomer2 = new User("customer2","customer2",true);
        vehicleCustomer2.addGroup(customerGroup);
        vehicleCustomer2.addGroup(vehicleAdminGroup);
        
        User vehicleAdmin1 = new User("vehicleAdmin1","vehicleAdmin1",true);
        vehicleAdmin1.addGroup(vehicleAdminGroup);
        
        User vehicleAdmin2 = new User("vehicleAdmin2","vehicleAdmin2",true);
        vehicleAdmin2.addGroup(vehicleAdminGroup);
        
        userSvc.create(vehicleCustomer1);
        userSvc.create(vehicleCustomer2);
        userSvc.create(vehicleAdmin1);
        userSvc.create(vehicleAdmin2);
        
        
        VehicleAdmin vm1=new VehicleAdmin("Admin one");
        vm1.setUser(vehicleAdmin1);
        vaService.create(vm1);
        
        VehicleAdmin vm2=new VehicleAdmin("Admin two");
        vm2.setUser(vehicleAdmin2);
        vaService.create(vm2);
        
        /*Vehicle v1=new Vehicle("Vehicle 1", LocalDate.of(2022, 4, 21), 10000, VehicleLocation.Chicago);
        Vehicle v2=new Vehicle("Vehicle 2", LocalDate.of(2021, 1, 2), 20000, VehicleLocation.Florida);
        Vehicle v3=new Vehicle("Vehicle 3", LocalDate.of(2012, 4, 1), 30000, VehicleLocation.SanFrancisco);
        Vehicle v4=new Vehicle("Vehicle 4", LocalDate.of(2009, 8, 13), 40000, VehicleLocation.NewYork);
        Vehicle v5=new Vehicle("Vehicle 5", LocalDate.of(2010, 11, 4), 50000, VehicleLocation.LosAngeles);
        Vehicle v6=new Vehicle("Vehicle 6", LocalDate.of(2020, 3, 8), 60000, VehicleLocation.Chicago,vm1);
        
    
        
        
        
        vService.create(v1);
        vService.create(v2);
        vService.create(v3);
        vService.create(v4);
        vService.create(v5);
        vService.create(v6);*/
        
        
        VehicleCustomer vc1 = new VehicleCustomer("Krishna", "Anand");
        vc1.setUser(vehicleCustomer1);
        
        VehicleCustomer vc2 = new VehicleCustomer("Rahul", "Kohli");
        vc2.setUser(vehicleCustomer2);
        
        VehicleCustomer vc3 = new VehicleCustomer("Keanu", "Reeves");
        vc3.setUser(vehicleAdmin2);
      
        /*vc1.addVehicle(v1);
        vc1.addVehicle(v2);
        vc1.addVehicle(v3);
        vc2.addVehicle(v4);
        vc2.addVehicle(v5);
        vc2.addVehicle(v6);*/
        vcService.create(vc1);
        vcService.create(vc2);
        vcService.create(vc3);
        
        /*VehicleBooking b1 = new VehicleBooking(LocalDate.of(2022, 4, 2), LocalTime.of(10,30),VehicleType.Bike);
        b1.scheduleVehicleBooking(v1, vm1, vc1);
        
        VehicleBooking b2 = new VehicleBooking(LocalDate.of(2022, 4, 2), LocalTime.of(10,30),VehicleType.Car);
        b2.scheduleVehicleBooking(v2, vm2, vc2);
        
        vbService.create(b1);
        vbService.create(b2);*/
       
        
        /*LOG.info("Satisfying the Output");
        for(VehicleCustomer c : vcService.findAll()){
            LOG.info("Vehicle Customer ======> " + c.toString());
            for(Vehicle h:c.getVehicle()){
                LOG.info("Customer has Vehicle ======> " + h.toString());
            }
            for(VehicleBooking b: vc1.getBooking()){
                LOG.info("Customer has Booking ======> " + b.toString());
            }
        }*/
        
        
        
        
        
        for(VehicleAdmin v: vaService.findAll()){
            LOG.info(v.toString());
            
        }
        
        //vm1.addVehicle(v1);
        /*vm1.addVehicle(v2);
        vm1.addVehicle(v3);
        vm1.addVehicle(v4);
        vm1.addVehicle(v5);
        vm1.addVehicle(v6);*/
        
        
    }
    
    
}
