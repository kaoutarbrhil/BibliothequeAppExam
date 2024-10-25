package com.kaoutar.bibliotheque.backend;

import com.kaoutar.bibliotheque.backend.Cd;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class GestionCdService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Ajoute un nouveau CD.
     */
    public String ajouterCd(String titre, String artiste) {
        Cd cd = new Cd(titre, artiste, true);
        entityManager.persist(cd);
        return "CD ajouté avec succès";
    }

    /**
     * Met à jour les informations d'un CD.
     */
    public String modifierCd(Long cdId, String titre, String artiste, boolean disponible) {
        Cd cd = entityManager.find(Cd.class, cdId);
        if (cd == null) {
            return "CD inexistant";
        }

        cd.setTitre(titre);
        cd.setArtiste(artiste);
        cd.setDisponible(disponible);

        entityManager.merge(cd);
        return "CD modifié avec succès";
    }

    /**
     * Supprime un CD.
     */
    public String supprimerCd(Long cdId) {
        Cd cd = entityManager.find(Cd.class, cdId);
        if (cd == null) {
            return "CD inexistant";
        }

        entityManager.remove(cd);
        return "CD supprimé avec succès";
    }

    /**
     * Liste tous les CDs disponibles.
     */
    public List<Cd> listerCdsDisponibles() {
        return entityManager.createQuery("SELECT c FROM Cd c WHERE c.disponible = true", Cd.class)
                .getResultList();
    }
}
