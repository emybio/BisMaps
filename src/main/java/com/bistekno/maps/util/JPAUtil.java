package com.bistekno.maps.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private final EntityManagerFactory factory;

    public JPAUtil (String unit) { this.factory = Persistence.createEntityManagerFactory(unit); }

    public EntityManager createEntityManager() { return this.factory.createEntityManager(); }

    public void close(){ this.factory.close(); }
}
