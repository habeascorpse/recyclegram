/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.controllers;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.recyclall.recyclegram.services.UserService;
import org.recyclall.recyclegram2.domain.User;

/**
 *
 * @author habeas
 */
@Named("register")
@ViewScoped
public class RegisterMB implements Serializable {

    public RegisterMB() {
    }
    
    
    @EJB
    private UserService userEJB;
    
    private String password;
    private String login;
    private String mail;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public boolean verifyMailAndLogin() {
        if ((this.login != null) && (!this.login.isEmpty())
                && (this.mail != null ) && (!this.mail.isEmpty())) {
            User user = new User(login, password, mail); 
            return userEJB.alreadyExist(user);
                
        }
        else {
           return false;
        }
        
    }
    
    public void save() {
        
        if (!this.verifyMailAndLogin()) {
            if ((this.password != null) && (!this.password.isEmpty())) {
                User user = new User(login,password,mail);
                userEJB.newUSer(user);
            }
        } 
        
    }
    
    
    
}
