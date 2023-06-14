/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.service;

import edu.iit.sat.itmd4515.kanand4.domain.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kris
 */
public abstract class AbstractService<T> {
    
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    
    protected final Class<T> entityClass;

    protected AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void create(T entity){
        em.persist(entity);
    }
    
    public T read(Long id){
        return em.find(entityClass, id);
    
    }
    
    public void update(T entity){
        em.merge(entity);
        
    }
    
    public void delete(T entity){
        em.remove(em.merge(entity));
    
    }
    
    protected List<T> findAll(String queryName){
        List<T> results=new ArrayList<>();
        results=em.createNamedQuery(queryName, entityClass).getResultList();
        return results;
    }
}
