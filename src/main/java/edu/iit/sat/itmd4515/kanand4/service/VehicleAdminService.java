/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.service;

import edu.iit.sat.itmd4515.kanand4.domain.Vehicle;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleBooking;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author kris
 */
//@Named(value = "vehicleAdmin")
@Stateless
public class VehicleAdminService extends AbstractService<VehicleAdmin> {

    private static final Logger LOG = Logger.getLogger(VehicleAdminService.class.getName());

    public VehicleAdminService() {
        super(VehicleAdmin.class);
    }

    public List<VehicleAdmin> findAll() {
        return em.createNamedQuery("Admin.findAll", VehicleAdmin.class).getResultList();
    }

    public VehicleAdmin findByUserName(String username) {
        return em.createNamedQuery("Admin.findByUserName", VehicleAdmin.class)
                .setParameter("userName", username).getSingleResult();
    }

    /*public void createNewVehicleForVehicleAdmin(Vehicle v, VehicleAdmin va) {
        em.persist(v);
        VehicleAdmin managedHotelAdminRef = em.getReference(VehicleAdmin.class, va.getId());
        managedVehicleAdminRef.addVehicle(v);
        em.merge(managedHotelAdminRef);
    }*/
 /*
    public List<VehicleAdmin> findAll(){
        return super.findAll("Admin.findAll");
    }
    
     */
    public void createVehicleForVehicleAdmin(Vehicle v, VehicleAdmin va) {
        LOG.info("Inside createVehicleForVehicleAdmin " + v.toString() + va.toString());
        em.persist(v);
        LOG.info("Inside createVehicleForVehicleAdmin here 1");

        VehicleAdmin managedVehicleAdminRef = em.getReference(VehicleAdmin.class, va.getId());
        LOG.info("Inside createVehicleForVehicleAdmin here 2" + v.toString() + va.toString());

        managedVehicleAdminRef.addVehicle(v);
        LOG.info("Inside createVehicleForVehicleAdmin here 3" + v.toString() + va.toString());

        em.merge(managedVehicleAdminRef);
    }

    public void updateVehicleForVehicleAdmin(Vehicle v) {
        Vehicle managedVehicleRef = em.getReference(Vehicle.class, v.getId());

        managedVehicleRef.setVehicleName(v.getVehicleName());
        managedVehicleRef.setVehicleBookDate(v.getVehicleBookDate());
        managedVehicleRef.setVehiclePrice(v.getVehiclePrice());
        managedVehicleRef.setVehicleLocation(v.getVehicleLocation());

        em.merge(managedVehicleRef);

    }

    public void deleteVehicleForVehicleAdmin(Vehicle v) {
        LOG.info("Inside deleteVehicleForVehicleAdmin " + v.toString());
        Vehicle managedVehicleRef = em.getReference(Vehicle.class, v.getId());

        managedVehicleRef.delVehicle();
        LOG.info("after deleteVehicleForVehicleAdmin ");

        //em.merge(v);
        em.remove(managedVehicleRef);
        LOG.info("after remove deleteVehicleForVehicleAdmin ");

        List<VehicleBooking> bookings = em.createNamedQuery("VehicleBooking.findVehicleBookingByVehicle", VehicleBooking.class)
                .setParameter("ID", v.getId())
                .getResultList();

        for (VehicleBooking v1 : bookings) {
            v1.cancelVehicleBooking();
            em.remove(v1);
        }

        em.remove(managedVehicleRef);

    }

    public void create(Vehicle vehicle) {
        em.persist(vehicle);
    }
}
