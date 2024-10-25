package com.kaoutar.bibliotheque.backend;

import com.kaoutar.bibliotheque.backend.Cd;
import com.kaoutar.bibliotheque.backend.Emprunt;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class PretRetourService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Permet à un utilisateur d'emprunter un CD.
     */
    public String emprunterCd(Long cdId, String utilisateur) {
        Cd cd = entityManager.find(Cd.class, cdId);
        if (cd == null || !cd.isDisponible()) {
            return "CD indisponible ou inexistant";
        }

        Emprunt emprunt = new Emprunt(utilisateur, cd, new Date());
        cd.setDisponible(false);

        entityManager.persist(emprunt);
        entityManager.merge(cd);

        return "CD emprunté avec succès";
    }

    /**
     * Permet de retourner un CD emprunté.
     */
    public String retournerCd(Long empruntId) {
        Emprunt emprunt = entityManager.find(Emprunt.class, empruntId);
        if (emprunt == null || emprunt.getDateRetour() != null) {
            return "Emprunt inexistant ou déjà retourné";
        }

        Cd cd = emprunt.getCd();
        cd.setDisponible(true);
        emprunt.setDateRetour(new Date());

        entityManager.merge(emprunt);
        entityManager.merge(cd);

        return "CD retourné avec succès";
    }

    /**
     * Liste tous les CDs empruntés par un utilisateur.
     */
    public List<Emprunt> listerCdsEmpruntes(String utilisateur) {
        return entityManager.createQuery("SELECT e FROM Emprunt e WHERE e.utilisateur = :utilisateur AND e.dateRetour IS NULL", Emprunt.class)
                .setParameter("utilisateur", utilisateur)
                .getResultList();
    }
}
