/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.services;

import javax.ejb.Remote;
import org.recyclall.recyclegram2.domain.User;

/**
 *
 * @author habeas
 */
@Remote
public interface UserServiceRemote {
    
    public User authenticate(String login, String password);
    
}
