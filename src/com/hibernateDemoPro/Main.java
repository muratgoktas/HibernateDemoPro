package com.hibernateDemoPro;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class Main {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			// SQL ->  (Structured Query Language)
			// HQL ->  (Hibernate Query Language)
			// Select * from City ile yaný kod.
			//List<City> cities = session.createQuery("from City as c Where c.countryCode='TUR' and c.district = 'Erzurum'").getResultList();
			//List<City> cities = session.createQuery("from City as c Where c.countryCode='TUR' and c.district LIKE '%kar%'").getResultList();
			List<City> cities = session.createQuery("from City as c Where c.name LIKE '%kar%'").getResultList();
			for (City city: cities) {
				System.out.println(city.getName());
			}

			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}
