package com.nagarro;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.nagarro.model.Product;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {
	private static SessionFactory factory;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		 try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		Junction junction=Restrictions.conjunction();
		Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(Product.class);
	         System.out.println(cr.list());

	}
	      catch(Exception e) {
	    	  
	      }
	}

}
