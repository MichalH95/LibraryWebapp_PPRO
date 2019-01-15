package com.ppro.projekt.service;

import com.ppro.projekt.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.ppro.projekt.ProjektTools.*;

@Repository
@Transactional
public class SpravaDbJpa implements SpravaDb {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UzivatelDb uzivatelDb;

    @Autowired
    private EmailService emailService;

    public void vlozAutora(int idKnihy, String jmeno, String vztah) {
        Autor autor = new Autor(vztah, jmeno);
        autor.setKniha(em.find(Kniha.class, idKnihy));
        em.persist(autor);
    }

    public void vlozKnihu(Kniha Kniha) {
        em.persist(Kniha);
    }

    public void vytvorNoveUpominky() {
        // najdi vypujcky s proslou dobou vraceni
        List<Vypujcka> vypujcky = em.createQuery("SELECT v from Vypujcka v where v.vypujceno_do < current_timestamp").getResultList();
        vypujcky.forEach(v -> {
            Upominka u = null;
            try {
                u = (Upominka) em.createQuery("SELECT u from Upominka u where u.uzivatel.id=:uz and u.kniha.id=:kn")
                        .setParameter("uz", v.getUzivatel().getId())
                        .setParameter("kn", v.getKniha().getId())
                        .getSingleResult();
            }
            catch (NoResultException e) {
            }
            if ( u == null ) {
                // pokud zatim neexistuje upominka, vytvor ji a posli email
                Upominka upominka = new Upominka("Pozdní vrácení", 20);
                upominka.setKniha(v.getKniha());
                upominka.setUzivatel(v.getUzivatel());
                upominka.setVypujcka(v);
                em.persist(upominka);

                emailService.sendSimpleMessage(v.getUzivatel().getEmail(), email_pozdni_vraceni_subject, email_pozdni_vraceni(v.getKniha().getNazev(), v.getVypujceno_do()));
            }
        });
    }

    public void smazStareRezervace() {
        List<Rezervace> stareRezervace = em.createQuery("SELECT r from Rezervace r where r.rezervace_do < current_timestamp").getResultList();
        stareRezervace.forEach(r -> {
            odstranRezervaci(r.getId());
        });
    }

    public void odstranKnihu(int id) {
        Kniha kniha = em.getReference(Kniha.class, id);
        if (kniha != null) {
            em.remove(kniha);
        }
        em.createQuery("DELETE from Recenze r where r.kniha.id=:id").setParameter("id",id).executeUpdate();
        em.createQuery("DELETE from Rezervace r where r.kniha.id=:id").setParameter("id",id).executeUpdate();
        em.createQuery("DELETE from Autor r where r.kniha.id=:id").setParameter("id",id).executeUpdate();
        em.createQuery("DELETE from Vypujcka r where r.kniha.id=:id").setParameter("id",id).executeUpdate();
    }

    public void odstranAutora(int id) {
        Autor autor = em.getReference(Autor.class, id);
        if (autor != null) {
            em.remove(autor);
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
            int idKnihy = rezervace.getKniha().getId();
            em.remove(rezervace);
            zkontrolujRezervace(idKnihy);
            em.createQuery("UPDATE Kniha k SET k.pocet_kusu =k.pocet_kusu+1 where k.id=:idKnihy").setParameter("idKnihy", idKnihy).executeUpdate();
        }
    }

    public void vratVypujcku(int id) {
        Vypujcka vypujcka = em.getReference(Vypujcka.class, id);
        if (vypujcka != null) {
            int idKnihy = vypujcka.getKniha().getId();
            zkontrolujRezervace(idKnihy);
            em.createQuery("UPDATE Kniha k SET k.pocet_kusu =k.pocet_kusu+1 where k.id=:idKnihy").setParameter("idKnihy", idKnihy).executeUpdate();
            em.remove(vypujcka);
        }
    }

    public void zkontrolujRezervace(int idKnihy) {
        List<Rezervace> rezervaceKeKnize = em.createQuery("SELECT r from Rezervace r where r.kniha.id=:kid").setParameter("kid", idKnihy).getResultList();
        if ( ! rezervaceKeKnize.isEmpty() ) {
            // pokud nekdo ma rezervaci, posleme prvnimu v poradi email a udelame z rezervace vypujcku
            long minPoradi = Long.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0 ; i < rezervaceKeKnize.size() ; i++) {
                if (rezervaceKeKnize.get(i).getPoradi() < minPoradi) {
                    minPoradi = rezervaceKeKnize.get(i).getPoradi();
                    minIndex = i;
                }
            }
            Rezervace r = rezervaceKeKnize.get(minIndex);
            String email = r.getUzivatel().getEmail();
            emailService.sendSimpleMessage(email, email_rezervace_pripravena_subject, email_rezervace_pripravena(r.getKniha().getNazev()));
            prevedRezervaciNaVypujcku(r.getId());
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
        return !u.isEmpty();
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

    public List<Kniha> filtrace(String zanr, String jazyk, String nakladatelstvi, String hledani) {
        return em.createQuery("SELECT k FROM Kniha k where k.zanr like :zanr and k.jazyk like :jazyk and k.nakladatelstvi like :nakladatelstvi and k.nazev like :nazev", Kniha.class)
                .setParameter("zanr", "%" + zanr + "%").setParameter("jazyk", "%" + jazyk + "%").setParameter("nakladatelstvi", "%" + nakladatelstvi + "%").setParameter("nazev", "%" + hledani + "%").getResultList();

    }

    public List<Upominka> filtraceProUpo(String emailuzivatele) {
        return em.createQuery("select u from Upominka u inner join Vypujcka v on u.vypujcka.id=v.id inner join Uzivatel uz on u.uzivatel.id=uz.id where uz.email like :email").setParameter("email", "%" + emailuzivatele + "%").getResultList();
    }

    public List<Recenze> filtraceProRec(String nazevknihy) {
        return em.createQuery("select r from Recenze r inner join Kniha k on r.kniha.id=k.id where k.nazev like :nazev").setParameter("nazev", "%" + nazevknihy + "%").getResultList();
    }

    public List<Vypujcka> filtraceProVyp(String nazevknihy) {
        return em.createQuery("select v from Vypujcka v inner join Uzivatel u on v.uzivatel.id=u.id inner join Kniha k on v.kniha.id=k.id where k.nazev like :nazev", Vypujcka.class).setParameter("nazev", "%" + nazevknihy + "%").getResultList();
    }

    public List<Kniha> filtraceProRez(String nazevknihy) {
        return em.createQuery("select r from Rezervace r inner join Kniha k on r.kniha.id=k.id where k.nazev like :nazev").setParameter("nazev", "%" + nazevknihy + "%").getResultList();
    }

    public List<Upominka> najdiUpoPodleId(int id) {
        String query = "Select u from Upominka u where u.id =:idecko";
        return em.createQuery(query).setParameter("idecko", id).getResultList();
    }

    public List<Kniha> najdiKniPodleId(int id) {
        String query = "Select k from Kniha k where k.id =:idecko";
        return em.createQuery(query).setParameter("idecko", id).getResultList();
    }

    public List<Rezervace> najdiRezPodleId(int id) {
        String query = "Select r from Rezervace r where r.id =:idecko";
        return em.createQuery(query).setParameter("idecko", id).getResultList();
    }

    public List<Vypujcka> najdiVypPodleId(int id) {
        String query = "Select v from Vypujcka v where v.id =:idecko";
        return em.createQuery(query).setParameter("idecko", id).getResultList();
    }

    public void upravRezervaci(int idecko, Date rezer_od, Date rezer_do) {
        em.createQuery("UPDATE Rezervace r SET r.rezervace_od =:rezer_od, r.rezervace_do =:rezer_do where r.id=:idecko").setParameter("idecko", idecko).setParameter("rezer_od", rezer_od).setParameter("rezer_do", rezer_do).executeUpdate();
    }

    public boolean existujeKniha(String nazev) {
        String query = "Select k from Kniha k where k.nazev =:nazev";
        List<Kniha> u = em.createQuery(query).setParameter("nazev", nazev).getResultList();
        return !u.isEmpty();
    }

    public boolean existujeAutor(String jmeno) {
        String query = "Select a from Autor a where a.jmeno =:jmeno";
        List<Autor> u = em.createQuery(query).setParameter("jmeno", jmeno).getResultList();
        return !u.isEmpty();
    }

    public void prevedRezervaciNaVypujcku(int idecko) {
        Rezervace rezervace = em.getReference(Rezervace.class, idecko);
        if (rezervace != null) {
            Kniha k = rezervace.getKniha();
            Uzivatel u = rezervace.getUzivatel();
            Vypujcka vypujcka = new Vypujcka(new Date(), datePlusDays(new Date(), 30), false);
            vypujcka.setUzivatel(u);
            vypujcka.setKniha(k);
            em.persist(vypujcka);
            em.remove(rezervace);
        }
    }

    public void vlozRezervaci(Rezervace rezervace)
    {
        em.persist(rezervace);
    }

    public void upravKnihu(int idecko, String nazev, String jazyk, String zanr, String nakladatelstvi, String datum_vydani, String isbn, int pocet_kusu, int pocet_stran, String popis) {
        em.createQuery("UPDATE Kniha k SET k.nazev =:nazev, k.jazyk =:jazyk, k.zanr=:zanr,k.nakladatelstvi=:nakladatelstvi,k.datum_vydani=:datum_vydani,k.isbn=:isbn,k.pocet_kusu=:pocet_kusu,k.pocet_stran=:pocet_stran, k.popis=:popis where k.id=:idecko").setParameter("idecko", idecko).setParameter("nazev", nazev)
                .setParameter("jazyk", jazyk).setParameter("zanr", zanr).setParameter("nakladatelstvi", nakladatelstvi).setParameter("datum_vydani", datum_vydani).setParameter("isbn", isbn).setParameter("pocet_kusu", pocet_kusu).setParameter("pocet_stran", pocet_stran).setParameter("popis", popis).executeUpdate();
    }

    public void upravUpominku(int idecko, int pokuta, String popis) {
        em.createQuery("UPDATE Upominka u SET u.pokuta =:pokuta, u.popis =:popis where u.id=:idecko").setParameter("idecko", idecko).setParameter("pokuta", pokuta).setParameter("popis", popis).executeUpdate();
    }

    public void upravVypujcku(int idecko, Date datum_vypujceni, Date vypujceno_do, Boolean vraceno) {
        em.createQuery("UPDATE Vypujcka v SET v.datum_vypujceni =:datum_vypujceni, v.vypujceno_do =:vypujceno_do, v.vraceno=:vraceno where v.id=:idecko").setParameter("idecko", idecko).setParameter("datum_vypujceni", datum_vypujceni).setParameter("vypujceno_do", vypujceno_do).setParameter("vraceno", vraceno).executeUpdate();
    }

   public void ulozitRecenzi(int idknihy,String emailuzivatele,String popis,int hodnoceni, String jmeno) {
        Recenze recenze = new Recenze(jmeno,popis,hodnoceni);
        recenze.setKniha(em.find(Kniha.class,idknihy));
        recenze.setUzivatel(uzivatelDb.najdiUzivatele(emailuzivatele));
        em.persist(recenze);
    }

    public Kniha najdiKnihu(String nazev) {
        return (Kniha) em.createQuery("Select k from Kniha k where k.nazev=:nazev").setParameter("nazev", nazev).getSingleResult();
    }

    public List<Kniha> najdiVsechnyKnihy() {
        return em.createQuery("SELECT k FROM Kniha k", Kniha.class).getResultList();
    }

    public List<Recenze> najdiRecenzi(String nazevknihy) {
        return em.createQuery("SELECT r FROM Recenze r inner join Kniha k on r.kniha.id=k.id where k.nazev=:nazev").setParameter("nazev",nazevknihy).getResultList();
    }

}
