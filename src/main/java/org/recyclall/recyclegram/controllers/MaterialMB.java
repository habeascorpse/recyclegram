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
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.recyclall.recyclegram.services.MaterialService;
import org.recyclall.recyclegram2.domain.Material;
import org.recyclall.recyclegram2.domain.MaterialUnity;

/**
 *
 * @author habeas
 */
@Named("materialMB")
@ViewScoped
public class MaterialMB implements Serializable {

    @Inject
    private MaterialService materialEJB;
    private List<Material> materialList;
    private Material material;
    private Part file;

    public MaterialMB() {
        material = null;
    }
    
    @PostConstruct
    public void init() {
        materialList = materialEJB.findAll();
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }
    
    public void newMaterial() {
        material = new Material();
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public StreamedContent getImage(Material material) {
        InputStream is = new ByteArrayInputStream( material.getImage());
        return new DefaultStreamedContent(is, "image/png");
    }
    
    
    public SelectItem[] getGenderValues() {
        SelectItem[] items = new SelectItem[MaterialUnity.values().length];
        int i = 0;
        for(MaterialUnity mu: MaterialUnity.values()) {
          items[i++] = new SelectItem(mu, mu.toString());
        }
        return items;
    }
    

    public void save() {
        try {
            
            material.setImage(org.apache.commons.io.IOUtils.toByteArray(file.getInputStream()));
            materialEJB.create(material);
            material = null;
            init();
        } catch (Exception ex) {

        }

    }

}
