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
import org.recyclall.recyclegram2.domain.MaterialRequest;
import org.recyclall.recyclegram2.domain.UF;
import org.recyclall.recyclegram2.domain.User;

/**
 *
 * @author habeas
 */
@Stateless
@LocalBean
public class MaterialRequestService extends AbstractFacade<MaterialRequest> {

    @PersistenceContext
    private EntityManager em;
    
    
    public MaterialRequestService() {
        super(MaterialRequest.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<MaterialRequest> findByUF(UF uf) {
        return em.createNamedQuery("MaterialRequest.findByUF")
                .setParameter("uf", uf)
                .getResultList();
    }
    
    public MaterialRequest newRequest(Material material, User user, Float quantity, String description) {
        
        MaterialRequest request = new MaterialRequest();
        request.setMaterial(material);
        request.setUser(user);
        request.setQuantity(quantity);
        request.setDescription(description);
        
        em.persist(request);
        
        return request;
    }
    
}
