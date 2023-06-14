/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

import edu.iit.sat.itmd4515.kanand4.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static jakarta.ws.rs.core.Response.status;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kris
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
  
    
    private User user;
    
    @Inject SecurityContext securityContext;
    @Inject FacesContext facesContext;
    
    public LoginController() {
    }
    
    @PostConstruct
    private void PostConstruct(){
        LOG.info("Inside LoginController postConstruct");
        user= new User();
    }
    
    
    public String getAuthenticatedUser(){
        return facesContext.getExternalContext().getRemoteUser();
    }
    
    public boolean isAdmin(){
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }
    public boolean isVehicleAdmin() {
        return securityContext.isCallerInRole("VEHICLEADMIN_ROLE");
    }

    public boolean isCustomer() {
        return securityContext.isCallerInRole("CUSTOMER_ROLE");
    }
    
    
    
        //Action methods
    public String doLogin() {
        LOG.info("LoginController.doLogin() for " + this.user.getUserName());
        
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        Credential cred = new UsernamePasswordCredential(
                this.user.getUserName(),
                new Password(this.user.getPassword()));
        
        
        AuthenticationStatus status = securityContext.authenticate(
                request, 
                response, 
                AuthenticationParameters.withParams().credential(cred));
        
        switch (status) {
            case SUCCESS:
                LOG.info(status.toString());
                break;
            case SEND_FAILURE:
                LOG.info(status.toString());
                return "/error.xhtml";
            case NOT_DONE:
                LOG.info(status.toString());
                return "/error.xhtml";
            case SEND_CONTINUE:
                LOG.info(status.toString());
                break;
        }

     
       

        return "/welcome.xhtml?faces-redirect=true";
    }

    
    public String doLogout() {
        try {
            HttpServletRequest request
                    = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return "/login.xhtml?faces-redirect=true";
    }

    
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    
}
