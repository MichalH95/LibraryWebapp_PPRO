package com.ppro.projekt.service;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Rezervace;
import com.ppro.projekt.entity.Uzivatele;
import com.ppro.projekt.entity.Vypujcky;
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

    public void odstranRezervaci(int id) {
        Rezervace rezervace = em.getReference(Rezervace.class, id);
        if(rezervace != null) {
            em.remove(rezervace);
        }
    }

    public void odstranVypujcku(int id) {
        Vypujcky vypujcky = em.getReference(Vypujcky.class, id);
        if(vypujcky != null) {
            em.remove(vypujcky);
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

    public boolean privilegium(String email)
    {

        String query = "Select u.privilegia from Uzivatele u where u.email =:email AND privilegia='1'";
        List<Uzivatele> u = em.createQuery(query).setParameter("email",email).getResultList();
        if(u.isEmpty())
        {return false;}else{return true;}


    }



    public List<Rezervace> vypisrezervace()
    {
        return em.createQuery("select r from Rezervace r inner join Kniha k on r.kniha.id=k.id").getResultList();
    }

    public List<Vypujcky> najdiVypujcky(String email)
    {
        return em.createQuery("select v from Vypujcky v inner join Uzivatele u on v.uzivatele.id=u.id inner join Kniha k on v.kniha.id=k.id where u.email=:email",Vypujcky.class).setParameter("email",email).getResultList();
    }

    public List<Vypujcky> najdiVypujckyProSpravu()
    {
        return em.createQuery("select v from Vypujcky v inner join Uzivatele u on v.uzivatele.id=u.id inner join Kniha k on v.kniha.id=k.id",Vypujcky.class).getResultList();
    }


    public List<Kniha> filtrace(String zanr, String jazyk, String nakladatelstvi)
    {
        return em.createQuery("SELECT k FROM Kniha k where k.zanr like :zanr and k.jazyk like :jazyk and k.nakladatelstvi like :nakladatelstvi", Kniha.class)
                .setParameter("zanr","%"+zanr+"%").setParameter("jazyk","%"+jazyk+"%").setParameter("nakladatelstvi","%"+nakladatelstvi+"%").getResultList();

    }


    public List<Rezervace> najdiRezPodleId(int id) {
        String query = "Select r from Rezervace r where r.id =:idecko";
        List<Rezervace> u = em.createQuery(query).setParameter("idecko",id).getResultList();
        return u;
    }

    public List<Vypujcky> najdiVypPodleId(int id) {
        String query = "Select v from Vypujcky v where v.id =:idecko";
        List<Vypujcky> u = em.createQuery(query).setParameter("idecko",id).getResultList();
        return u;
    }

    public void upravRezervaci (int idecko,String rezer_od,String rezer_do)
    {
        em.createQuery("UPDATE Rezervace r SET r.rezervace_od =:rezer_od, r.rezervace_do =:rezer_do where r.id=:idecko").setParameter("idecko",idecko).setParameter("rezer_od",rezer_od).setParameter("rezer_do",rezer_do).executeUpdate();
    }

    public void upravVypujcku (int idecko,String datum_vypujceni,String vypujceno_do,Boolean vraceno)
    {
        em.createQuery("UPDATE Vypujcky v SET v.datum_vypujceni =:datum_vypujceni, v.vypujceno_do =:vypujceno_do, v.vraceno=:vraceno where v.id=:idecko").setParameter("idecko",idecko).setParameter("datum_vypujceni",datum_vypujceni).setParameter("vypujceno_do",vypujceno_do).setParameter("vraceno",vraceno).executeUpdate();
    }

    public List<Kniha> najdiVsechnyKnihy() {
        return em.createQuery("SELECT k FROM Kniha k", Kniha.class).getResultList();
    }

}
