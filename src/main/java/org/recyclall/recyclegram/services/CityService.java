/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.services;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.recyclall.recyclegram2.domain.City;
import org.recyclall.recyclegram2.domain.UF;

/**
 *
 * @author habeas
 */
@Stateless
@LocalBean
public class CityService extends AbstractFacade<City> {

    @PersistenceContext
    private EntityManager em;
    
    public CityService() {
        super(City.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<City> getByName(String name ) {
        return em.createNamedQuery("City.findByName")
                .setParameter("name", "%"+name+"%")
                .getResultList();
    }
    
    public List<City> getByUF(UF uf) {
        return em
                .createNamedQuery("City.findByUF")
                .setParameter("uf", uf)
                .getResultList();
    }
    
}
