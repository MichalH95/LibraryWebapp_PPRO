package com.ppro.projekt.service;

import com.ppro.projekt.entity.University;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UniversityServiceJpa implements UniversityService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public University findById(long id) {
        return em.find(University.class, id);
    }

    @Override
    public University findByName(String name) {
        return null;
    }

    @Override
    public void saveUniversity(University University) {
        em.persist(University);
    }

    @Override
    public void updateUniversity(University University) {

    }

    @Override
    public void deleteUniversityById(long id) {

    }

    @Override
    public List<University> findAllUniversitys() {
        return em.createQuery("SELECT u FROM University u", University.class).getResultList();
    }

    @Override
    public void deleteAllUniversitys() {

    }

    @Override
    public boolean isUniversityExist(University University) {
        return false;
    }
}
