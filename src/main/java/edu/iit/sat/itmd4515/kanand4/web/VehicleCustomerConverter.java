/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import edu.iit.sat.itmd4515.kanand4.service.VehicleCustomerService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author kris
 */
@FacesConverter(value = "vehicleCustomerConverter" , managed = true)
public class VehicleCustomerConverter implements Converter<VehicleCustomer>{
    
    @EJB VehicleCustomerService vehicleCustomerSvc;
    

    @Override
    public VehicleCustomer getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return vehicleCustomerSvc.read(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, VehicleCustomer value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value.getId());
    }
    
}
