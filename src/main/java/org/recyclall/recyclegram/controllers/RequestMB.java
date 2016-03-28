/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.recyclall.recyclegram.services.MaterialRequestService;
import org.recyclall.recyclegram.services.MaterialService;
import org.recyclall.recyclegram.services.UFService;
import org.recyclall.recyclegram2.domain.Material;
import org.recyclall.recyclegram2.domain.MaterialRequest;
import org.recyclall.recyclegram2.domain.UF;

/**
 *
 * @author habeas
 */
@Named
@ViewScoped
public class RequestMB implements Serializable {
    
    private MaterialRequest request;
    
    private List<MaterialRequest> requestList;
    private String pesquisaMaterial;
    private Material selectedMaterial;
    private List<Material> materialList;
    private UF selectedUF;
    
    @Inject private DashboardMB dashboard;
    
    
    @EJB private MaterialRequestService materialRequestEJB;
    @EJB private MaterialService materialEJB;
    @EJB private UFService ufEJB;
    
    

    public RequestMB() {
    }

    @PostConstruct
    public void init() {
        requestList = materialRequestEJB.findAll();
        request = new MaterialRequest();
    }
    
    
    public MaterialRequest getRequest() {
        return request;
    }

    public void setRequest(MaterialRequest request) {
        this.request = request;
    }
    
    public void pesquisar() {
        if (selectedUF != null) {
            requestList = materialRequestEJB.findByUF(selectedUF);
        }
        else {
            requestList = materialRequestEJB.findAll();
        }
    }

    public List<MaterialRequest> getRequestList() {
        return requestList;
    }

    public String getPesquisaMaterial() {
        return pesquisaMaterial;
    }

    public void setPesquisaMaterial(String pesquisaMaterial) {
        this.pesquisaMaterial = pesquisaMaterial;
    }

    public Material getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(Material selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }
    
    public List<Material> getMaterialList() {
        return materialList;
    }
    
    public List<UF> getUFList() {
        return ufEJB.findAll();
    }
    
    public void pesquisarMaterial() {
        materialList = materialEJB.findByDescription(pesquisaMaterial);
        selectedMaterial = null;
    }

    public void newRequest() {
        request = new MaterialRequest();
        
    }
    

    public UF getSelectedUF() {
        return selectedUF;
    }

    public void setSelectedUF(UF selectedUF) {
        this.selectedUF = selectedUF;
    }
    
    public void  save() {
        request.setUser(dashboard.getUser());
        request.setMaterial(selectedMaterial);
        
        materialRequestEJB.create(request);
        request = null;
        pesquisaMaterial = "";
        pesquisar();
        
    }
    
    
    
    
}
