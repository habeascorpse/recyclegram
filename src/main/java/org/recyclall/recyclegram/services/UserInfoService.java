/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.recyclall.recyclegram2.domain.UserInfo;

/**
 *
 * @author habeas
 */
@Stateless
@LocalBean
public class UserInfoService extends AbstractFacade<UserInfo> {

    @PersistenceContext
    private EntityManager em;
    
    public UserInfoService() {
        super(UserInfo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
