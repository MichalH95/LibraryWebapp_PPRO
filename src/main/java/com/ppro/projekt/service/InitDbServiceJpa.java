package com.ppro.projekt.service;

import com.ppro.projekt.entity.Knihy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class InitDbServiceJpa implements InitDbService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initDb() {


        /*    University u = new University("UHK");
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
