/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.recyclall.recyclegram.services.CityService;
import org.recyclall.recyclegram.services.UFService;
import org.recyclall.recyclegram.services.UserService;
import org.recyclall.recyclegram2.domain.City;
import org.recyclall.recyclegram2.domain.UF;
import org.recyclall.recyclegram2.domain.User;
import org.recyclall.recyclegram2.domain.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author habeas
 */
@Named
@SessionScoped
public class DashboardMB implements Serializable {
    
    @EJB
    private UserService userEJB;
    @EJB
    private CityService cityEJB;
    @EJB
    private UFService ufEJB;
    
    private User user;
    
    private UF selectedUF;

    public DashboardMB() {
    }
    
    @PostConstruct
    public void init() {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userEJB.getByLogin(login);
        if (user.getUserInfo() == null) {
            user.setUserInfo(new UserInfo());
            userEJB.edit(user);
            user = userEJB.find(user.getID());
        }
    }

    public User getUser() {
        return user;
    }
    
    public void save() {
        userEJB.edit(user);
        user = userEJB.find(user.getID());
    }

    public UF getSelectedUF() {
        return selectedUF;
    }

    public void setSelectedUF(UF selectedUF) {
        this.selectedUF = selectedUF;
    }

    
    public List<UF> getListUF() {
        return ufEJB.findAll();
        
    }
    
    public List<City> getCityList() {
        return cityEJB.getByUF(selectedUF);
    }
    
}
