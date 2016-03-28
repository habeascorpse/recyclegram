/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.recyclall.recyclegram2.domain.Offer;

/**
 *
 * @author habeas
 */
public class OfferService extends AbstractFacade<Offer> {

    @PersistenceContext
    private EntityManager em;
    
    public OfferService() {
        super(Offer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
