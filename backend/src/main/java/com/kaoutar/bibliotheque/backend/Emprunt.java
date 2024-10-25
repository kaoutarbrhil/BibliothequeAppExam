package com.kaoutar.bibliotheque.backend;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emprunts")
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_id", nullable = false)
    private Cd cd;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_emprunt", nullable = false)
    private Date dateEmprunt;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_retour")
    private Date dateRetour;

    // Constructeurs
    public Emprunt() {
    }

    public Emprunt(String utilisateur, Cd cd, Date dateEmprunt) {
        this.utilisateur = utilisateur;
        this.cd = cd;
        this.dateEmprunt = dateEmprunt;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Cd getCd() {
        return cd;
    }

    public void setCd(Cd cd) {
        this.cd = cd;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", utilisateur='" + utilisateur + '\'' +
                ", cd=" + cd +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
