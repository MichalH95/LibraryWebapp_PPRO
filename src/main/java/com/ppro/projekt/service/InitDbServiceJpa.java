package com.ppro.projekt.service;

import com.ppro.projekt.entity.Autor;
import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Recenze;
import com.ppro.projekt.entity.Uzivatel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class InitDbServiceJpa implements InitDbService {

    @PersistenceContext
    private EntityManager em;

    private UzivatelDb uzivatelDb;
    private SpravaDb spravaDb;


    public InitDbServiceJpa(@Autowired UzivatelDb uzivatelDb,@Autowired SpravaDb spravadb) {
        this.uzivatelDb = uzivatelDb;
        this.spravaDb=spravadb;
    }

    @Override
    public void initDb() {

        initAutori();

        initKnihy();

        initUzivatele();

        initRezervace();

        initVypujcky();

        initUpominky();

        initRecenze();

    }

    private void initAutori() {
        em.createQuery("DELETE from Autor ").executeUpdate();
    }

    private void initRecenze() {
        em.createQuery("DELETE from Recenze ").executeUpdate();

        //Recenze od uzivatele Honza - 5 hvězd - kniha Malý princ
        Recenze recenze = new Recenze("Honza","Opravdu dobrá kniha. Všem doporučuji.",5);
        recenze.setUzivatel(uzivatelDb.najdiUzivatele("test@test.cz"));
        recenze.setKniha(spravaDb.najdiKnihu("Malý princ"));
        em.persist(recenze);

        //Recenze od uzivatele Pepa - 1 hvězda - kniha Karkulka
        Recenze recenze1 = new Recenze("Pepa","Špatná kniha. Určite nedoporučuji.",1);
        recenze1.setUzivatel(uzivatelDb.najdiUzivatele("testpriv@test.cz"));
        recenze1.setKniha(spravaDb.najdiKnihu("Karkulka"));
        em.persist(recenze1);
    }

    private void initUpominky() {
        em.createQuery("DELETE from Upominka").executeUpdate();
    }

    private void initRezervace() {
        em.createQuery("DELETE from Rezervace").executeUpdate();
    }

    private void initVypujcky() {
        em.createQuery("DELETE from Vypujcka").executeUpdate();
    }

    private void initKnihy() {
        em.createQuery("DELETE from Recenze").executeUpdate();
        em.createQuery("DELETE from Kniha").executeUpdate();

        List<Kniha> knihy = new ArrayList<>();

        Kniha kniha1 = new Kniha("Malý princ", "Havárie letadla donutí vypravěče příběhu, který je zároveň autorovým alter egem, k přistání uprostřed pouště. Má zásobu pitné vody sotva na týden a proto musí opravit motor pokud možno co nejrychleji. Prvního dne ulehne unaven po celodenní práci a za úsvitu, stále tisíc kilometrů od nejbližšího lidského obydlí, ho probudí zvláštní hlásek, který ho žádá, aby nakreslil beránka... Alegorická pohádka pro děti i pro dospělé, kteří přemýšlí o ztraceném mládí a hledají životní moudrost, patří mezi nejvýznamnější díla svého druhu.", "Sci-fi",
                "2012-01-04", 232, "Albatros", "4564562456", 2, "Čeština");
        Autor autor1 = new Autor("spisovatel", "Antoine De Saint-Exupéry");
        autor1.setKniha(kniha1);
        Autor autor2 = new Autor("ilustrátor", "Helena Zmatlíková");
        autor2.setKniha(kniha1);
        kniha1.setAutori(Arrays.asList(autor1, autor2));
        knihy.add(kniha1);


        Kniha kniha2 = new Kniha("Karkulka", "Červená karkulka je známá pohádka o setkání mladé dívky s vlkem. Tento příběh se během své historie velmi měnil a stal se předlohou pro obrovské množství moderních adaptací. Nejstarší psaná verze pochází z pera Charlese Perraulta, dnes asi nejrozšířenější verze je založena na zpracování bratří Grimmů. Slovo karkulka pochází z latinského slova carracalla a znamená čepeček, čapku, či pokrývku hlavy. Poznámka k pravopisu: V souladu s Pravidly českého pravopisu se jméno dívky píše jako „Červená karkulka“ nebo jen „Karkulka“. V některých překladech se ale objevuje i verze „červená Karkulka“. Obě možnosti lze považovat za správné.", "Pohádka",
                "2005-03-07", 104, "Prometheus", "3564583115", 3, "Čeština");
        Autor autor3 = new Autor("spisovatel", "Bratři Grimmové");
        autor3.setKniha(kniha2);
        kniha2.setAutori(Arrays.asList(autor3));
        knihy.add(kniha2);

        knihy.forEach(k -> {
            em.persist(k);
            if ( k.getAutori() != null ) {
                k.getAutori().forEach(a -> em.persist(a));
            }
        });
    }

    private void initUzivatele() {
        em.createQuery("DELETE from Uzivatel").executeUpdate();

        //   email: test@test.cz   heslo: test (bez privilegia)
        List<Uzivatel> uzivatele = new ArrayList<>();
        uzivatele.add(new Uzivatel("Honza", "Uzivatel", "Praha", "Prazska", "52", 10010, "test@test.cz",
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", false,0));
        uzivatele.add(new Uzivatel());

        //    email: testpriv@test.cz  heslo: test (s privilegii)
        uzivatele.add(new Uzivatel("Pepa", "Admin", "Praha", "Pricna", "13", 40511, "testpriv@test.cz",
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", false,1));
        uzivatele.forEach(uzivatel -> em.persist(uzivatel));

    }

}
