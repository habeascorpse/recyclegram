/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.recyclall.recyclegram2.domain.User;
import org.recycleall.recyclegram2.utils.Criptography;

/**
 *
 * @author habeas
 */
@Stateless(mappedName = "UserEJB")
@LocalBean
public class UserService extends AbstractFacade<User> implements UserServiceRemote {

    @PersistenceContext(unitName = "recyclegramPU")
    private EntityManager em;

    public UserService() {
        super(User.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public User getByMail(String mail) {
        try {
            return (User) em.
                    createNamedQuery("User.findByMailAddress")
                    .setParameter("mail", mail)
                    .getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("Bacana2:" + nre);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public User getByLogin(String login) {
        try {
            return (User) em.
                    createNamedQuery("User.findByLogin")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("Bacana:" + nre);
            return null;
        }
    }

    public boolean alreadyExist(User user) {
        return !((user.getID() == null) && (this.getByLogin(user.getLogin()) == null)
                && (this.getByMail(user.getEmail()) == null));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User newUSer(User user) {

        if (!this.alreadyExist(user)) {

            em.persist(user);
            return user;
        } else {
            throw new RuntimeException("User already registered!");
        }

    }

    @Override
    public User authenticate(String login, String password) {

        password = Criptography.proccess(password);

        User user = (User) em.createNamedQuery("User.authenticate")
                .setParameter("password", password)
                .setParameter("login", login)
                .getSingleResult();

        return user;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
