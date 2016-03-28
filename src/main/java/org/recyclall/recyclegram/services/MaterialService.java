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
import org.recyclall.recyclegram2.domain.Material;

/**
 *
 * @author habeas
 */
@Stateless
@LocalBean
public class MaterialService extends AbstractFacade<Material>{

    @PersistenceContext
    private EntityManager em;
    
    
    public MaterialService() {
        super(Material.class);
    }
    
    public List<Material>findByDescription(String description) {
        return em
                .createNamedQuery("Material.findByDescription")
                .setParameter("description", "%"+description+"%")
                .getResultList();
        
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
