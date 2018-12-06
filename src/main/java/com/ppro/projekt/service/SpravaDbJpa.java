package com.ppro.projekt.service;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Uzivatele;
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

    public void vlozUzivatele(Uzivatele uzivatele) {
        em.persist(uzivatele);
    }

    public void odstranKnihu(int id) {
        Kniha kniha = em.getReference(Kniha.class, id);
        if(kniha != null) {
            em.remove(kniha);
        }
    }

    public boolean existujeuzivatel(String email)
    {
        String query = "Select u from Uzivatele u where u.email =:email";
        List<Uzivatele> u = em.createQuery(query).setParameter("email",email).getResultList();
        if(u.isEmpty())
        {return false;}else{return true;}
    }

    public boolean overlogin(String email, String heslo)
    {
        String query = "Select u from Uzivatele u where u.email =:email AND u.heslo =:heslo";
        List<Uzivatele> u = em.createQuery(query).setParameter("email",email).setParameter("heslo",heslo).getResultList();
        if(u.isEmpty())
        {return false;}else{return true;}
    }

    public Kniha najdiPodleId(long id) {
        return em.find(Kniha.class, id);
    }

    public List<Kniha> najdiVsechnyKnihy() {
        return em.createQuery("SELECT k FROM Kniha k", Kniha.class).getResultList();
    }

}
