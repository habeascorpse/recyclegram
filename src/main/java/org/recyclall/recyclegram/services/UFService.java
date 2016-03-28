/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.recyclall.recyclegram2.domain.UF;

/**
 *
 * @author habeas
 */
@Stateless
public class UFService extends AbstractFacade<UF> {

    @PersistenceContext
    private EntityManager em;
    
    public UFService() {
        super(UF.class);
    }

    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
