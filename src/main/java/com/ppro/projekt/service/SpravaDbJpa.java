package com.ppro.projekt.service;

import com.ppro.projekt.entity.*;
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

    public void odstranUpominku(int id) {
        Upominky upominky = em.getReference(Upominky.class, id);
        if(upominky != null) {
            em.remove(upominky);
        }
    }

    public void odstranUzivatele(int id) {
        Uzivatele uzivatele = em.getReference(Uzivatele.class, id);
        if(uzivatele != null) {
            em.remove(uzivatele);
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
        String query = "Select u from Uzivatele u where u.email =:email AND u.heslo =:heslo AND u.blokace=false";
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

    public List<Rezervace> vypisRezervaceProUzivatele(String email)
    {
        return em.createQuery("select r from Rezervace r inner join Uzivatele u on r.uzivatele.id=u.id inner join Kniha k on r.kniha.id=k.id where u.email=:email").setParameter("email",email).getResultList();
    }

    public List<Upominky> vypisUpominkyProUzivatele(String email)
    {
        return em.createQuery("select u from Upominky u inner join Kniha k on u.kniha.id=k.id inner join Uzivatele uz on u.uzivatele.id=uz.id inner join Vypujcky v on u.vypujcky.id=v.id where uz.email=:email").setParameter("email",email).getResultList();
    }

    public List<Rezervace> vypisrezervace()
    {
        return em.createQuery("select r from Rezervace r inner join Kniha k on r.kniha.id=k.id").getResultList();
    }

    public List<Upominky> vypisUpominky()
    {
        return em.createQuery("select u from Upominky u inner join Vypujcky v on u.vypujcky.id=v.id inner join Uzivatele uz on u.uzivatele.id=uz.id").getResultList();
    }

    public List<Uzivatele> vypisUzivatele()
    {
        return em.createQuery("select u from Uzivatele u").getResultList();
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

    public List<Upominky> najdiUpoPodleId(int id) {
        String query = "Select u from Upominky u where u.id =:idecko";
        List<Upominky> u = em.createQuery(query).setParameter("idecko",id).getResultList();
        return u;
    }

    public List<Kniha> najdiKniPodleId(int id) {
        String query = "Select k from Kniha k where k.id =:idecko";
        List<Kniha> u = em.createQuery(query).setParameter("idecko",id).getResultList();
        return u;
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

    public void upravKnihu (int idecko,String nazev,String jazyk,String zanr,String nakladatelstvi,String datum_vydani,String isbn,int pocet_kusu, int pocet_stran,String popis)
    {
        em.createQuery("UPDATE Kniha k SET k.nazev =:nazev, k.jazyk =:jazyk, k.zanr=:zanr,k.nakladatelstvi=:nakladatelstvi,k.datum_vydani=:datum_vydani,k.isbn=:isbn,k.pocet_kusu=:pocet_kusu,k.pocet_stran=:pocet_stran, k.popis=:popis where k.id=:idecko").setParameter("idecko",idecko).setParameter("nazev",nazev)
                .setParameter("jazyk",jazyk).setParameter("zanr",zanr).setParameter("nakladatelstvi",nakladatelstvi).setParameter("datum_vydani",datum_vydani).setParameter("isbn",isbn).setParameter("pocet_kusu",pocet_kusu).setParameter("pocet_stran",pocet_stran).setParameter("popis",popis).executeUpdate();
    }

    public void upravUpominku (int idecko,int pokuta,String popis)
    {
        em.createQuery("UPDATE Upominky u SET u.pokuta =:pokuta, u.popis =:popis where u.id=:idecko").setParameter("idecko",idecko).setParameter("pokuta",pokuta).setParameter("popis",popis).executeUpdate();
    }

    public void blokovatuzivatele (int idecko)
    {
        em.createQuery("UPDATE Uzivatele u SET u.blokace =true where u.id=:idecko").setParameter("idecko",idecko).executeUpdate();
    }

    public void odblokovatuzivatele (int idecko)
    {
        em.createQuery("UPDATE Uzivatele u SET u.blokace =false where u.id=:idecko").setParameter("idecko",idecko).executeUpdate();
    }

    public void upravVypujcku (int idecko,String datum_vypujceni,String vypujceno_do,Boolean vraceno)
    {
        em.createQuery("UPDATE Vypujcky v SET v.datum_vypujceni =:datum_vypujceni, v.vypujceno_do =:vypujceno_do, v.vraceno=:vraceno where v.id=:idecko").setParameter("idecko",idecko).setParameter("datum_vypujceni",datum_vypujceni).setParameter("vypujceno_do",vypujceno_do).setParameter("vraceno",vraceno).executeUpdate();
    }


    public List<Kniha> najdiVsechnyKnihy() {
        return em.createQuery("SELECT k FROM Kniha k", Kniha.class).getResultList();
    }

}
