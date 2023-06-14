/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author kris
 */
public class VehicleValidationTest {
    private static Validator validator;
   
    @BeforeAll public static void beforeAll(){
       
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
   
    }
   
    @BeforeEach public void beforeEach(){}
    
    @Test
    public void invalidvehicleNameValidationShouldFail(){
        Vehicle v = new Vehicle("Txxxxxxxtxtxtxtxttxtxtxtxxtxtxtxtxtxtxtxtxtxtxtxtxtxtxtxtxtxtxtxtxtx", LocalDate.of(2022, Month.MARCH, 02), -10000,VehicleLocation.Chicago);
        Set<ConstraintViolation<Vehicle>> violations = validator.validate(v); 
        assertEquals(1, violations.size());
        for (ConstraintViolation<Vehicle> violation : violations) {
            System.out.println(violation.toString());
        }
    }
    @Test
    public void validVehicle(){
        Vehicle v = new Vehicle("BMW", LocalDate.of(2023, Month.MARCH, 02), 15000,VehicleLocation.Chicago);
        Set<ConstraintViolation<Vehicle>> violations = validator.validate(v); 
        assertEquals(0, violations.size());
        for (ConstraintViolation<Vehicle> violation : violations) {
            System.out.println(violation.toString());
        }
    }
    
    @AfterEach public void afterEach(){}
     
    @AfterAll public static void afterAll(){}
    
}
