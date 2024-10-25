package com.kaoutar.bibliotheque.beans;

import com.kaoutar.bibliotheque.backend.Cd;
import com.kaoutar.bibliotheque.backend.GestionCdService;
import jakarta.ejb.EJB;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;

import java.util.List;

@ManagedBean
@RequestScoped
public class CdBean {

    @EJB
    private GestionCdService gestionCdService;

    private Long id;
    private String titre;
    private String artiste;
    private boolean disponible;

    public List<Cd> getCdsDisponibles() {
        return gestionCdService.listerCdsDisponibles();
    }

    public String ajouterCd() {
        gestionCdService.ajouterCd(titre, artiste);
        return "cds?faces-redirect=true";
    }

    public String modifierCd() {
        gestionCdService.modifierCd(id, titre, artiste, disponible);
        return "cds?faces-redirect=true";
    }

    public String supprimerCd(Long cdId) {
        gestionCdService.supprimerCd(cdId);
        return "cds?faces-redirect=true";
    }

    // Getters et Setters
}
