/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

//import edu.iit.sat.itmd4515.kanand4.domain.VehicleAdmin;
import edu.iit.sat.itmd4515.kanand4.domain.VehicleCustomer;
import edu.iit.sat.itmd4515.kanand4.service.VehicleCustomerService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author kris*/

@Named
@RequestScoped
public class VehicleCustomerController {

    private static final Logger LOG = Logger.getLogger(VehicleCustomerController.class.getName());

    @EJB VehicleCustomerService vcs;
    @Inject LoginController loginController;
    
    private VehicleCustomer vehicleCustomer; 
    public VehicleCustomerController() {
    }
  
    @PostConstruct
    private void PostConstruct(){
        LOG.info("Inside VehicleCustomerController.PostConstruct()");
        LOG.info(loginController.getAuthenticatedUser());
        vehicleCustomer =vcs.findCustomerByUserName(loginController.getAuthenticatedUser());
        LOG.info("Exiting VehicleCustomerController with " + vehicleCustomer.toString());
    }

    public VehicleCustomer getVehicleCustomer() {
        return vehicleCustomer;
    }

    public void setVehicleCustomer(VehicleCustomer vehicleCustomer) {
        this.vehicleCustomer = vehicleCustomer;
    }

    
}

/* welcome customer
<--<!-- comment <ui:define name="content">
        <h:dataTable    
            styleClass="table table-hover table-striped"
            value="#{vehicleCustomerController.vehicleCustomer.customer}" 
            var="customer">
            <h:column>
                <f:facet name="header"><h:outputText value="Customer ID"/></f:facet>
                <h:outputText value="#{customer.id}"/>
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Customer FirstName"/></f:facet>
                <h:outputText value="#{customer.firstName}"/>
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Customer LastName"/></f:facet>
                <h:outputText value="#{customer.lastName}"/>
            </h:column>
            

        </h:dataTable>
    </ui:define> 
-->

*/

