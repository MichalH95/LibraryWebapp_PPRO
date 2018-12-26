package com.ppro.projekt.service;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Uzivatel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class InitDbServiceJpa implements InitDbService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initDb() {

        em.createQuery("select k from Kniha k").getResultList().forEach(kniha -> em.remove(kniha));

        List<Kniha> knihy = new ArrayList<>();
        knihy.add(new Kniha("Maly princ", "Kniha o malem princi", "Sci-fi", "26.11.2005",
                232, "Albatros", "4564562456", 2, "Cestina"));
        knihy.add(new Kniha("Karkulka", "Pohadka o Karkulce", "Pohadka", "3.1.2002",
                104, "Albatros", "3564583115", 3, "Cestina"));
        knihy.forEach(kniha -> em.persist(kniha));

        em.createQuery("select u from Uzivatel u").getResultList().forEach(u -> em.remove(u));

        //   email: asd@gmail.com   heslo: asd
        Uzivatel uzivatel = new Uzivatel("Honza", "Admin", "Praha", "Prazska", "52", 10010, "asd@gmail.com",
                "688787d8ff144c502c7f5cffaafe2cc588d86079f9de88304c26b0cb99ce91c6", false);
        em.persist(uzivatel);
    }

}
