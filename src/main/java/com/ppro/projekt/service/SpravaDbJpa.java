package com.ppro.projekt.service;

import com.ppro.projekt.entity.Autori;
import com.ppro.projekt.entity.Knihy;
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

    public void VlozKnihu(Knihy Knihy) {
        em.persist(Knihy);
    }



    public Knihy findById(long id) { return em.find(Knihy.class, id);
    }

    public void OdstranKnihu(int id){
        Knihy kniha = em.getReference(Knihy.class, id);
         if(kniha != null) {
             em.remove(kniha);
         }
    }



    public List<Autori> NajdiAutoraByKnihaId(long id_knihy) {
           return (List<Autori>) em.find(Autori.class,id_knihy);

    }


    public List<Knihy> zobrazKnihy() {
        return em.createQuery("SELECT k FROM Knihy k",Knihy.class).getResultList();
    }

    public List<Autori> NajdiAutoraByKnihaId() {
        return em.createQuery("SELECT a FROM Autori a").getResultList();
    }


    public boolean isUniversityExist(Knihy Knihy) {
        return false;
    }


}
