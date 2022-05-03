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
			//List<City> cities = session.createQuery("from City as c Where c.name LIKE '%kar%'").getResultList();
			// ORDER BY
			// ASC -> Ascending -Yükselen deðer  -Deafult
			// DESC -> Descending - Azalan deðer
			
			List<City> cities = session.createQuery("from City as c  ORDER BY c.name Desc" ).getResultList(); 
			for (City city: cities) {
				System.out.println(city.getName());
			}

			// GROUP BY
			List<String> countryCodes = session.createQuery("Select c.countryCode from City as c GROUP BY c.countryCode").getResultList();
			
			for (String country: countryCodes) {
				System.out.println(country);
			}
			// INSERT INTO  Start
			// Hibernate: insert into city (CountryCode, District, Name, Population, ID) values (?, ?, ?, ?, ?)
			/*
			 * City city = new City(); city.setCountryCode("TUR");
			 * city.setDistrict("Karadeniz"); city.setName("Erzurum25");
			 * city.setPopulation(123000);
			 * 
			 * session.save(city);
			 */
			// INSERT INTO end.
			// UPDATE 
			City city =session.get(City.class, 4099);
			city.setPopulation(999999);
			
			session.save(city);
			
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}
	

}
