/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram2.domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.recycleall.recyclegram2.utils.Criptography;

/**
 *
 * @author habeas
 */
@Entity(name = "user_recycle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findById", query = "SELECT u FROM user_recycle u WHERE u.ID = :id"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM user_recycle u WHERE u.login = :login"),
    @NamedQuery(name = "User.authenticate", query = "SELECT u FROM user_recycle u WHERE u.login = :login AND u.password = :password"),
    @NamedQuery(name = "User.findByMailAddress", query = "SELECT u FROM user_recycle u WHERE u.email = :mail")
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    
    private String login;
    
    private String password;
    
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserInfo userInfo;
    
    @OneToMany
    @JoinColumn(referencedColumnName = "user_id" )
    private List<MaterialRequest> requestList;

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = Criptography.proccess(password);
        this.email = email;
    }
    
    

    public Long getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    
    @XmlTransient
    public List<MaterialRequest> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<MaterialRequest> requestList) {
        this.requestList = requestList;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }
    
    
    
}
