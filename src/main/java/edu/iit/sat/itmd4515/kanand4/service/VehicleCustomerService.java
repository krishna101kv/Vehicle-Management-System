/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.service;

import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author kris
 */
@Stateless
public class VehicleCustomerService extends AbstractService<VehicleCustomer>{
    
    public VehicleCustomerService(){
        super(VehicleCustomer.class);
        
    }
    
    public List<VehicleCustomer> findAll(){
        return em.createNamedQuery("VehicleCustomer.findAll", VehicleCustomer.class).getResultList();
    }
    
   
    
    // public List<VehicleCustomer> findAll(){
    //    return super.findAll("Customer.findAll");
    //}
     
     
  public VehicleCustomer findCustomerByUserName(String username){
        return em.createNamedQuery("VehicleCustomer.findCustomerByUserName",
                VehicleCustomer.class).setParameter("username",username)
                .getSingleResult();
    }
   
}
