/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author kris
 */
public class VehicleJPARelationshipTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
     @BeforeAll public static void beforeAll(){
         emf =Persistence.createEntityManagerFactory("itmd4515testPU");
       
    }
   
    @BeforeEach public void beforeEach(){
        em=emf.createEntityManager();
        tx=em.getTransaction();
        tx.begin();
        Vehicle v= new Vehicle("TestMustang", LocalDate.of(2023, Month.MARCH, 02), 20000,VehicleLocation.Florida);
        em.persist(v);
        tx.commit();
    
    }
    
    
    
    
    @Test
    public void testOneToOneUniVehicleAdminRelationship(){
        Vehicle v = new Vehicle("TESTVEHICLE");
        VehicleAdmin a= new VehicleAdmin( "TESTADMIN");
        v.setVehicleAdmin(a);
        tx.begin();
        em.persist(a);
        em.persist(v);
        tx.commit();
     }
    
   
    
    
    @Test
    public void testBiDirectionalMAnyToManyCustomerVehicleRelationship()
    {
        VehicleCustomer vc = new VehicleCustomer("Krishna");
        Vehicle v= new Vehicle("TestTesla", LocalDate.of(2023, Month.MARCH, 02), 20000,VehicleLocation.Chicago);

        
        vc.addVehicle(v);

        tx.begin();
        em.persist(vc);
        em.persist(v);
        tx.commit();
        
        System.out.println("Navigating the relationship from thr owning side: " + vc.getVehicle());
        System.out.println("Navigating the relationship from thr inverse side: " + v.getCustomer());

        //assert success
        assertTrue(vc.getVehicle().size() == 1);
        assertTrue(v.getCustomer().size() == 1);

        //clean up the test data
        tx.begin();
        vc.removeVehicle(v);
        em.remove(v);
        em.remove(vc);
        tx.commit();
    }
    
    
 

 

    
    
    
  
    
    @AfterEach public void afterEach(){
        tx.begin();
        Vehicle v=em.createNamedQuery("Vehicle.findByName", Vehicle.class)
               .setParameter("vehicleName", "TestMustang")
               .getSingleResult();
        em.remove(v);
        tx.commit();
    }
     
    @AfterAll public static void afterAll(){}
}
