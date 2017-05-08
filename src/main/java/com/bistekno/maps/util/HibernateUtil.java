package com.bistekno.maps.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final Configuration configuration;
	private static final SessionFactory factory;
	
	static {
		(configuration = new Configuration()).configure("hibernate.cfg.xml");
		
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        factory = configuration.buildSessionFactory(builder.build());
	}

    public static Session createSession() { return factory.openSession(); }
}
