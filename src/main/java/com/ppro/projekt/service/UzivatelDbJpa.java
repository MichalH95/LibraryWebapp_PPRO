package com.ppro.projekt.service;

import com.ppro.projekt.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.ppro.projekt.ProjektTools.*;

@Repository
@Transactional
public class UzivatelDbJpa implements UzivatelDb {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    @Lazy
    private SpravaDb spravaDb;

    @Autowired
    private EmailService emailService;

    public void vlozUzivatele(Uzivatel uzivatel) {
        em.persist(uzivatel); }

    public void vlozUpominku(int uzivatelid, int knihaID,int pokuta,String popis,int idvypujcky) {
        Upominka upominka = new Upominka(popis,pokuta);
        upominka.setKniha(em.find(Kniha.class,knihaID));
        upominka.setUzivatel(em.find(Uzivatel.class,uzivatelid));
        upominka.setVypujcka(em.find(Vypujcka.class,idvypujcky));
        em.persist(upominka);
    }

    public void odstranUzivatele(int id) {
        Uzivatel uzivatel = em.getReference(Uzivatel.class, id);

        if (uzivatel != null) {
            // pokud ma vypujcene knihy, vratit je
            List<Vypujcka> vypujcky = em.createQuery("SELECT v from Vypujcka v where v.uzivatel=:uziv").setParameter("uziv", uzivatel).getResultList();
            vypujcky.forEach(v -> {
                spravaDb.vratVypujcku(v.getId());
            });
            // pokud ma rezervace, zrus je
            List<Rezervace> rezervace = em.createQuery("SELECT r from Rezervace r where r.uzivatel=:uziv").setParameter("uziv", uzivatel).getResultList();
            rezervace.forEach(r -> {
                spravaDb.odstranRezervaci(r.getId());
            });
            // smaz upominky uzivatele
            em.createQuery("DELETE from Upominka u where u.uzivatel=:uziv").setParameter("uziv", uzivatel).executeUpdate();

            em.remove(uzivatel);
        }
    }

    public boolean existujeUzivatel(String email) {
        String query = "Select u from Uzivatel u where u.email =:email";
        List<Uzivatel> u = em.createQuery(query).setParameter("email", email).getResultList();
        return !u.isEmpty();
    }

    public boolean existujeVypujcka(String email) {
        String query = "Select v from Vypujcka v inner join Uzivatel u on v.uzivatel.id=u.id where u.email =:email";
        List<Uzivatel> u = em.createQuery(query).setParameter("email", email).getResultList();
        return !u.isEmpty();
    }

    public boolean existujeRezervace(String email) {
        String query = "Select r from Rezervace r inner join Uzivatel u on r.uzivatel.id=u.id where u.email =:email";
        List<Uzivatel> u = em.createQuery(query).setParameter("email", email).getResultList();
        return !u.isEmpty();
    }

    public boolean overlogin(String email, String heslo) {
        String query = "Select u from Uzivatel u where u.email =:email AND u.heslo =:heslo AND u.blokace=false";
        List<Uzivatel> u = em.createQuery(query).setParameter("email", email).setParameter("heslo", heslo).getResultList();
        return !u.isEmpty();
    }

    public boolean privilegium(String email) {
        String query = "Select u.privilegia from Uzivatel u where u.email =:email AND u.privilegia=1";
        List<Uzivatel> u = em.createQuery(query).setParameter("email", email).getResultList();
        return !u.isEmpty();
    }

    public List<Rezervace> vypisRezervaceProUzivatele(String email) {
        return em.createQuery("select r from Rezervace r inner join Uzivatel u on r.uzivatel.id=u.id inner join Kniha k on r.kniha.id=k.id where u.email=:email").setParameter("email", email).getResultList();
    }

    public List<Upominka> vypisUpominkyProUzivatele(String email) {
        return em.createQuery("select u from Upominka u inner join Kniha k on u.kniha.id=k.id inner join Uzivatel uz on u.uzivatel.id=uz.id inner join Vypujcka v on u.vypujcka.id=v.id where uz.email=:email").setParameter("email", email).getResultList();
    }

    public List<Uzivatel> vypisUzivatele() {
        return em.createQuery("select u from Uzivatel u").getResultList();
    }

    public List<Uzivatel> filtraceProUzi(String emailuzivatele) {
        return em.createQuery("select u from Uzivatel u where u.email like :email").setParameter("email", "%" + emailuzivatele + "%").getResultList();
    }

    public Uzivatel najdiUzivatele(String email) {
        return (Uzivatel) em.createQuery("Select u from Uzivatel u where u.email=:email").setParameter("email", email).getSingleResult();
    }

    public void nastavitVypujcku(int idKnihy, String email) {
        em.createQuery("UPDATE Kniha k SET k.pocet_kusu =k.pocet_kusu-1 where k.id=:idKnihy").setParameter("idKnihy", idKnihy).executeUpdate();
        Vypujcka vypujcka = new Vypujcka(new Date(), datePlusDays(new Date(), 30), false);
        vypujcka.setUzivatel(najdiUzivatele(email));
        vypujcka.setKniha(em.find(Kniha.class, idKnihy));
        em.persist(vypujcka);
    }

    public void nastavitRezervaci(int idKnihy, String email) {
        Kniha k = em.find(Kniha.class, idKnihy);
        Uzivatel u = najdiUzivatele(email);

        if ( k.getPocet_kusu() > 0 ) {
            // pokud je kniha dostupna, nastavime vypujcku a rovnou posilame email
            Vypujcka vypujcka = new Vypujcka(new Date(), datePlusDays(new Date(), 30), false);
            vypujcka.setUzivatel(najdiUzivatele(email));
            vypujcka.setKniha(em.find(Kniha.class, idKnihy));
            em.persist(vypujcka);
            emailService.sendSimpleMessage(email, email_rezervace_pripravena_subject, email_rezervace_pripravena(k.getNazev()));
        } else {
            // pokud kniha neni dostupna, vytvorime rezervaci s poradim
            List<Rezervace> rezervaceKeKnize = em.createQuery("SELECT r from Rezervace r where r.kniha.id=:kid").setParameter("kid", k.getId()).getResultList();
            long maxPoradi = 0;
            for (Rezervace r : rezervaceKeKnize) {
                if (r.getPoradi() > maxPoradi) {
                    maxPoradi = r.getPoradi();
                }
            }
            Rezervace rezervace = new Rezervace(maxPoradi+1, new Date(), datePlusDays(new Date(), 7));
            rezervace.setUzivatel(u);
            rezervace.setKniha(k);
            em.persist(rezervace);
        }

        em.createQuery("UPDATE Kniha k SET k.pocet_kusu =k.pocet_kusu-1 where k.id=:idKnihy").setParameter("idKnihy", idKnihy).executeUpdate();
    }


    public boolean zjistiRecenze(String emailUzivatele, int idKnihy)
    {
        String query = "select r from Recenze r inner join Kniha k on r.kniha.id=k.id inner join Uzivatel u on r.uzivatel.id=u.id where k.id=:idknihy and u.email=:email";
        List<Recenze> r = em.createQuery(query).setParameter("idknihy", idKnihy).setParameter("email",emailUzivatele).getResultList();
        return !r.isEmpty();
    }

    public void blokovatUzivatele(int idecko) {
        em.createQuery("UPDATE Uzivatel u SET u.blokace =true where u.id=:idecko").setParameter("idecko", idecko).executeUpdate();
    }

    public void odblokovatUzivatele(int idecko) {
        em.createQuery("UPDATE Uzivatel u SET u.blokace =false where u.id=:idecko").setParameter("idecko", idecko).executeUpdate();
    }

}
