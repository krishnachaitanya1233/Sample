package com.nbos.hb.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;

import com.nbos.hb.pojos.Employee;
import com.nbos.hb.util.HibernateUtilities;

//example using simple Criteria with alias
public class SimpleCriteriaWithAlias {

	public static void main(String[] args) {

		// getting a named logger
		Logger log = Logger.getLogger("hib");

		// getting SessionFactory from HibernateUtilities.
		SessionFactory sf = HibernateUtilities.getSessionFactory();
		// open session factory using session factory. this is a one time step
		Session s = sf.openSession();
		// creating a Criteria instance
		Criteria cr = s.createCriteria(Employee.class,"emp");
		// getting the alias name of the Criteria instance
		log.info("alias name : " + cr.getAlias());
		log.info("list()");
		// using list method on criteria instance
		List l = cr.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();

		log.info("no.of records : " + l.size());

		for (int i = 0; i < l.size(); i++) {
			Map m = (Map)l.get(i);
			log.info(m);
	/*		Employee e = (Employee) l.get(i);
			log.info(e.getEid() + ", " + e.getEname() + ", " + e.getSal()
					+ ", " + e.getDeptno().getDno());
*/		}

		// close session will release database connection
		s.close();
		// close session factory will release any resources held by hibernate
		sf.close();
	}
}