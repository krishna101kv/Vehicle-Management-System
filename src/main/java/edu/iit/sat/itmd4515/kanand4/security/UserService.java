/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.security;

import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author kris
 */
@Stateless
public class UserService extends AbstractService<User> {

    public UserService() {
        super(User.class);
    }
    
    public List<User> findAll(){
        return super.findAll("User.findAll");
    }

    
    public void signUpNewVehicleAdminUser(VehicleAdmin vehicleAdmin) {

        Group hotelAdminGroup = em.createQuery("select g from Group g where g.groupName = 'VEHICLEADMIN_GROUP'", Group.class).getSingleResult();
        vehicleAdmin.getUser().addGroup(hotelAdminGroup);
        vehicleAdmin.getUser().setEnabled(true);
        em.persist(vehicleAdmin.getUser());

        em.persist(vehicleAdmin);
    }
    
    
    
    
}
