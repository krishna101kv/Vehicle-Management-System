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
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author kris
 */
public class VehicleJPATestDisabled {
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
    public void createTest(){
        tx.begin();
        //Creating an entity
        Vehicle v= new Vehicle("TestAudi", LocalDate.of(2023, Month.MARCH, 02), 20000,VehicleLocation.Florida);
        em.persist(v);
        tx.commit();
        //read the test data that we created from database
        Vehicle readBackFromDatabase = em.find(Vehicle.class,v.getId());
        //assert that readBackFromDatabase is not null so the data is created
        System.out.println("CreateTest:"+ v.toString());
        Assertions.assertNotNull(readBackFromDatabase); 
    }
    
    
    
    
    @Test
    public void readTest(){
        Vehicle v=em.createNamedQuery("Vehicle.findByName", Vehicle.class)
               .setParameter("vehicleName", "TestMustang")
               .getSingleResult();
        assertEquals("TestMustang",v.getVehicleName());
        System.out.println("readtest:" + v.toString());
        
    }
    
    
    
    
    
    
    
    @Test
    public void updateTest(){
        tx.begin();
        Vehicle v=em.createNamedQuery("Vehicle.findByName", Vehicle.class)
               .setParameter("vehicleName", "TestMustang")
               .getSingleResult();
        v.setVehicleLocation(VehicleLocation.Dallas);
        tx.commit();
        
        
        System.out.println("updatetest:" + v.toString());
        
        Vehicle readBackFromDatabase = em.find(Vehicle.class, v.getId());
        assertEquals(VehicleLocation.Dallas,readBackFromDatabase.getVehicleLocation());
        
    }
    @Test
    public void deleteTest(){
        tx.begin();
        Vehicle v= new Vehicle("DELETEME", LocalDate.of(2023, Month.MARCH, 02), 20000,VehicleLocation.Florida);
        em.persist(v);
        tx.commit();
        
        System.out.println("DeleteTest:"+ v.toString());
        Assertions.assertNotNull(v.getId());
        
        tx.begin();
        em.remove(v);
        tx.commit();
        
        
        Vehicle readBackFromDatabase = em.find(Vehicle.class, v.getId());
        assertNull(readBackFromDatabase);
        
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
