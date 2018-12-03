package com.ppro.projekt.service;

import com.ppro.projekt.entity.Kniha;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SpravaDbJpa implements SpravaDb{

    @PersistenceContext
    private EntityManager em;

    public void vlozKnihu(Kniha Kniha) {
        em.persist(Kniha);
    }

    public void odstranKnihu(int id) {
        Kniha kniha = em.getReference(Kniha.class, id);
        if(kniha != null) {
            em.remove(kniha);
        }
    }

    public Kniha najdiPodleId(long id) {
        return em.find(Kniha.class, id);
    }

    public List<Kniha> najdiVsechnyKnihy() {
        return em.createQuery("SELECT k FROM Kniha k", Kniha.class).getResultList();
    }

}
