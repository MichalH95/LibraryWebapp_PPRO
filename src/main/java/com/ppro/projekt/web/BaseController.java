package com.ppro.projekt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    @RequestMapping("/")
    public String zobrazeni() {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-cviceni");
//        EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//
//        em.createQuery("DELETE from University").executeUpdate();
//        em.createQuery("DELETE from Student").executeUpdate();
//        em.createQuery("DELETE from Project").executeUpdate();
//
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//
//        // TODO vytvorit instance, provazat vztahy, zpersistnet pomoci em.persist(o);
//        University u = new University("UHK");
//
////        Student s = new Student("Krátký", "Radim");
////
////        Project p = new Project("PPRO");
////
////        u.getStudenti().add(s);
////        s.setUniverzita(u);
//////		p.getStudenti().add(s);
////        s.getProjekty().add(p);
//
//        em.persist(u);
////        em.persist(s);
//
//        em.getTransaction().commit();
//        em.close();
//        emf.close();





//        DbInit.main(new String[0]);





//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-cviceni");
//        EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//
////        em.createQuery("SELECT * FROM University").getResultList();
//        University u = em.find(University.class, 20);
//
//        System.out.println();
//
//        System.out.println(u);
//
////        List<Student> students = u.getStudenti();
////
////        System.out.println("----------");
////
////        for (Student s :students)
////            System.out.println(s.getProjekty().get(0));
//
//        System.out.println();
//
//        em.getTransaction().commit();
//        em.close();
//        emf.close();

        return "view";
    }

}
