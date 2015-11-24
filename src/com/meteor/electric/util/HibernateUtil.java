package com.meteor.electric.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("hibernate/hibernate.cfg.xml")
				.build();
		SessionFactory sessionFactory = null;
		try{
			sessionFactory = new MetadataSources(registry)
					.buildMetadata()
					.buildSessionFactory();
		}catch(Exception e){
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
		return sessionFactory;
	}
	
	public static final SessionFactory getSessionFactory(){
		return SESSION_FACTORY;
	}
}
