/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.service;

import edu.iit.sat.itmd4515.kanand4.domain.Vehicle;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kris
 */
@Named(value = "vehicleService")
@Stateless
public class VehicleService extends AbstractService<Vehicle> {
    
    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    public VehicleService() {
        super(Vehicle.class);
    }
    
   /* public void create(Vehicle v){
        em.persist(v);
    }
    
    public Vehicle read(Long id){
        return em.find(Vehicle.class, id);
    
    }
    
    public void update(Vehicle v){
        em.merge(v);
        
    }
    
    public void delete(Vehicle v){
        em.remove(em.merge(v));
    
    }
    
    public List<Vehicle> findAll(){
        List<Vehicle> v=new ArrayList<>();
        v=em.createQuery("select v from Vehicle v", Vehicle.class).getResultList();
        return v;
    }*/
    
    
   

    //@Override
    public List<Vehicle> findAll() {
        return em.createNamedQuery("Vehicle.findAll",Vehicle.class).getResultList();
    }
    
   public void createVehicleForVehicleAdmin(Vehicle v, VehicleAdmin va){
        em.persist(v);
        
        VehicleAdmin managedVehicleAdminRef = em.getReference(VehicleAdmin.class, va.getId());
        managedVehicleAdminRef.addVehicle(v);
        em.merge(managedVehicleAdminRef);
    } 
    
   
   
    
    
}
