package com.ppro.projekt;

import com.ppro.projekt.entity.Project;
import com.ppro.projekt.entity.Student;
import com.ppro.projekt.entity.University;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbInit {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-cviceni");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		em.createQuery("DELETE from University").executeUpdate();
		em.createQuery("DELETE from Student").executeUpdate();
        em.createQuery("DELETE from Project").executeUpdate();

//        University u = em.find(University.class, 29);
//        System.out.println("\n\n\n\n" + u.getName() + "\n\n\n\n");

        em.getTransaction().commit();

		em.getTransaction().begin();

		University u = new University("UHK");

		Student s = new Student("Krátký", "Radim");

		Project p = new Project("PPRO");

		u.getStudenti().add(s);
		s.setUniverzita(u);
		p.getStudenti().add(s);
		s.getProjekty().add(p);

		em.persist(u);
		em.persist(s);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
