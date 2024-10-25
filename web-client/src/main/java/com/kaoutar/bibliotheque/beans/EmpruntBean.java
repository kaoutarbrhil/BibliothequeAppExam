package com.kaoutar.bibliotheque.beans;

import com.kaoutar.bibliotheque.backend.entities.Emprunt;
import com.kaoutar.bibliotheque.backend.services.PretRetourService;

import jakarta.ejb.EJB;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class EmpruntBean {

    @EJB
    private PretRetourService pretRetourService;

    private Long cdId;
    private String utilisateur;
    private Long empruntId;

    public String emprunterCd() {
        String result = pretRetourService.emprunterCd(cdId, utilisateur);
        return result.equals("CD emprunté avec succès") ? "emprunts?faces-redirect=true" : null;
    }

    public String retournerCd(Long empruntId) {
        String result = pretRetourService.retournerCd(empruntId);
        return result.equals("CD retourné avec succès") ? "emprunts?faces-redirect=true" : null;
    }

    public List<Emprunt> getCdsEmpruntes() {
        return pretRetourService.listerCdsEmpruntes(utilisateur);
    }

    // Getters et Setters
}
