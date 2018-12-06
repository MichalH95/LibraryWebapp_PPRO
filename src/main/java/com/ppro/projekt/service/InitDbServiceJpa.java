package com.ppro.projekt.service;

import com.ppro.projekt.entity.Kniha;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public class InitDbServiceJpa implements InitDbService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initDb() {

        /*Kniha k = new Kniha("Maly princ", "Kniha o malem princi", "Sci-fi", new Date(),
                232, "Albatros", "4564562456", "Cestina");

        em.persist(k);



            University u = new University("UHK");
              Student s = new Student("Krátký", "Radim");
              Project p = new Project("PPRO");

              u.getStudenti().add(s);
              s.setUniverzita(u);
              p.getStudenti().add(s);
              s.getProjekty().add(p);

              em.persist(u);
              em.persist(s);
              em.persist(p);
        */
    }

}
