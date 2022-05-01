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
			// Select * from City ile yan� kod.
			List<City> cities = session.createQuery("from city").getResultList();
		
			for (City city: cities) {
				System.out.println(city.getName());
			}

			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}