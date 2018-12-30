package com.ppro.projekt.service;

import com.ppro.projekt.entity.Autor;
import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Uzivatel;
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

    @Override
    public void initDb() {

        initKnihy();

        initUzivatele();

    }

    private void initKnihy() {
        em.createQuery("select k from Kniha k").getResultList().forEach(kniha -> em.remove(kniha));

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
                "2005-03-07", 104, "Albatros", "3564583115", 3, "Čeština");
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
        em.createQuery("select u from Uzivatel u").getResultList().forEach(u -> em.remove(u));

        //   email: test@test.cz   heslo: test (bez privilegia)
        List<Uzivatel> uzivatele = new ArrayList<>();
        uzivatele.add(new Uzivatel("Honza", "Admin", "Praha", "Prazska", "52", 10010, "test@test.cz",
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", false,0));

        //    email: testpriv@test.cz  heslo: test (s privilegii)
        uzivatele.add(new Uzivatel("Honza", "Admin", "Praha", "Prazska", "52", 10010, "testpriv@test.cz",
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", false,1));

        uzivatele.forEach(uzivatel -> em.persist(uzivatel));
    }

}
