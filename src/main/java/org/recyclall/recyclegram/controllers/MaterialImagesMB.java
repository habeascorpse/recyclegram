/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recyclall.recyclegram.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.recyclall.recyclegram.services.MaterialService;
import org.recyclall.recyclegram2.domain.Material;

/**
 *
 * @author habeas
 */
@Named
@ApplicationScoped
public class MaterialImagesMB implements Serializable {

    @EJB
    private MaterialService service;

    public byte[] getImage(Long materialId) {
        
        if (materialId != null) {
            Material material = service.find(materialId);
            return material.getImage();
        } else {
            return null;
        }
    }

}
