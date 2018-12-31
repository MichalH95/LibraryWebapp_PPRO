package com.ppro.projekt.service;

import com.ppro.projekt.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SpravaDbJpa implements SpravaDb {

    @PersistenceContext
    private EntityManager em;

    public void vlozKnihu(Kniha Kniha) {
        em.persist(Kniha);
    }

    public void odstranKnihu(int id) {
        Kniha kniha = em.getReference(Kniha.class, id);
        if (kniha != null) {
            em.remove(kniha);
        }
    }

    public void odstranRecenzi(int id) {
        Recenze recenze = em.getReference(Recenze.class, id);
        if (recenze != null) {
            em.remove(recenze);
        }
    }

    public void odstranRezervaci(int id) {
        Rezervace rezervace = em.getReference(Rezervace.class, id);
        if (rezervace != null) {
            em.remove(rezervace);
        }
    }

    public void odstranVypujcku(int id) {
        Vypujcka vypujcka = em.getReference(Vypujcka.class, id);
        if (vypujcka != null) {
            int idKnihy = vypujcka.getKniha().getId();
            em.createQuery("UPDATE Kniha k SET k.pocet_kusu =k.pocet_kusu+1 where k.id=:idKnihy").setParameter("idKnihy", idKnihy).executeUpdate();
            em.remove(vypujcka);
        }
    }

    public void odstranUpominku(int id) {
        Upominka upominka = em.getReference(Upominka.class, id);
        if (upominka != null) {
            em.remove(upominka);
        }
    }

    public boolean dostupnost(int idecko) {
        String query = "Select k from Kniha k where k.id =:idecko AND k.pocet_kusu>0";
        List<Kniha> u = em.createQuery(query).setParameter("idecko", idecko).getResultList();
        if (u.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<Rezervace> vypisRezervace() {
        return em.createQuery("select r from Rezervace r inner join Kniha k on r.kniha.id=k.id").getResultList();
    }

    public List<Recenze> vypisRecenze() {
        return em.createQuery("select r from Recenze r inner join Kniha k on r.kniha.id=k.id").getResultList();
    }

    public List<Upominka> vypisUpominky() {
        return em.createQuery("select u from Upominka u inner join Vypujcka v on u.vypujcka.id=v.id inner join Uzivatel uz on u.uzivatel.id=uz.id").getResultList();
    }

    public List<Vypujcka> najdiVypujcky(String email) {
        return em.createQuery("select v from Vypujcka v inner join Uzivatel u on v.uzivatel.id=u.id inner join Kniha k on v.kniha.id=k.id where u.email=:email", Vypujcka.class).setParameter("email", email).getResultList();
    }

    public List<Vypujcka> najdiVypujckyProSpravu() {
        return em.createQuery("select v from Vypujcka v inner join Uzivatel u on v.uzivatel.id=u.id inner join Kniha k on v.kniha.id=k.id", Vypujcka.class).getResultList();
    }


    public List<Kniha> filtrace(String zanr, String jazyk, String nakladatelstvi) {
        return em.createQuery("SELECT k FROM Kniha k where k.zanr like :zanr and k.jazyk like :jazyk and k.nakladatelstvi like :nakladatelstvi", Kniha.class)
                .setParameter("zanr", "%" + zanr + "%").setParameter("jazyk", "%" + jazyk + "%").setParameter("nakladatelstvi", "%" + nakladatelstvi + "%").getResultList();

    }

    public List<Upominka> najdiUpoPodleId(int id) {
        String query = "Select u from Upominka u where u.id =:idecko";
        List<Upominka> u = em.createQuery(query).setParameter("idecko", id).getResultList();
        return u;
    }

    public List<Kniha> najdiKniPodleId(int id) {
        String query = "Select k from Kniha k where k.id =:idecko";
        List<Kniha> u = em.createQuery(query).setParameter("idecko", id).getResultList();
        return u;
    }

    public List<Rezervace> najdiRezPodleId(int id) {
        String query = "Select r from Rezervace r where r.id =:idecko";
        List<Rezervace> u = em.createQuery(query).setParameter("idecko", id).getResultList();
        return u;
    }

    public List<Vypujcka> najdiVypPodleId(int id) {
        String query = "Select v from Vypujcka v where v.id =:idecko";
        List<Vypujcka> u = em.createQuery(query).setParameter("idecko", id).getResultList();
        return u;
    }

    public void upravRezervaci(int idecko, String rezer_od, String rezer_do) {
        em.createQuery("UPDATE Rezervace r SET r.rezervace_od =:rezer_od, r.rezervace_do =:rezer_do where r.id=:idecko").setParameter("idecko", idecko).setParameter("rezer_od", rezer_od).setParameter("rezer_do", rezer_do).executeUpdate();
    }

    public void upravKnihu(int idecko, String nazev, String jazyk, String zanr, String nakladatelstvi, String datum_vydani, String isbn, int pocet_kusu, int pocet_stran, String popis) {
        em.createQuery("UPDATE Kniha k SET k.nazev =:nazev, k.jazyk =:jazyk, k.zanr=:zanr,k.nakladatelstvi=:nakladatelstvi,k.datum_vydani=:datum_vydani,k.isbn=:isbn,k.pocet_kusu=:pocet_kusu,k.pocet_stran=:pocet_stran, k.popis=:popis where k.id=:idecko").setParameter("idecko", idecko).setParameter("nazev", nazev)
                .setParameter("jazyk", jazyk).setParameter("zanr", zanr).setParameter("nakladatelstvi", nakladatelstvi).setParameter("datum_vydani", datum_vydani).setParameter("isbn", isbn).setParameter("pocet_kusu", pocet_kusu).setParameter("pocet_stran", pocet_stran).setParameter("popis", popis).executeUpdate();
    }

    public void upravUpominku(int idecko, int pokuta, String popis) {
        em.createQuery("UPDATE Upominka u SET u.pokuta =:pokuta, u.popis =:popis where u.id=:idecko").setParameter("idecko", idecko).setParameter("pokuta", pokuta).setParameter("popis", popis).executeUpdate();
    }

    public void upravVypujcku(int idecko, String datum_vypujceni, String vypujceno_do, Boolean vraceno) {
        em.createQuery("UPDATE Vypujcka v SET v.datum_vypujceni =:datum_vypujceni, v.vypujceno_do =:vypujceno_do, v.vraceno=:vraceno where v.id=:idecko").setParameter("idecko", idecko).setParameter("datum_vypujceni", datum_vypujceni).setParameter("vypujceno_do", vypujceno_do).setParameter("vraceno", vraceno).executeUpdate();
    }


    public List<Kniha> najdiVsechnyKnihy() {
        return em.createQuery("SELECT k FROM Kniha k", Kniha.class).getResultList();
    }

}
