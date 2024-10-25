package com.kaoutar.bibliotheque.backend;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cds")
public class Cd implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String artiste;

    @Column(nullable = false)
    private boolean disponible;

    // Constructeurs
    public Cd() {
    }

    public Cd(String titre, String artiste, boolean disponible) {
        this.titre = titre;
        this.artiste = artiste;
        this.disponible = disponible;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Cd{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", artiste='" + artiste + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}