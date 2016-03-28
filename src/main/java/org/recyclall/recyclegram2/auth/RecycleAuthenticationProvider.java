/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram2.auth;

import javax.ejb.EJB;
import org.recyclall.recyclegram.services.UserServiceRemote;
import org.recyclall.recyclegram2.domain.User;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author habeas
 */
@Component
public class RecycleAuthenticationProvider implements AuthenticationProvider {

    @EJB(mappedName = "UserEJB")
    private UserServiceRemote userEJB;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {

        try {
            String login = a.getName();
            String password = a.getCredentials().toString();
            User user = userEJB.authenticate(login, password);

            Authentication auth = new UsernamePasswordAuthenticationToken(login, password,null);

            return auth;

        } catch (Exception nre) {
            throw new AuthenticationCredentialsNotFoundException("Login/senha incorreto");
        }
        

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
