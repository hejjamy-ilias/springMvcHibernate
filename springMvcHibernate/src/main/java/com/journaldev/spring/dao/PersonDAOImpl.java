package com.journaldev.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO{
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		logger.info("Person saved successfully, Person Details="+p);
	}
	
	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(p);
		tx.commit();
		logger.info("Person updated successfully, Person Details="+p);
	}
	
	@Override
	public List<Person> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Person> personsList = session.createQuery("from Person").list();
		tx.commit();
		for(Person p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
	}
	
	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Transaction tx = session.beginTransaction();
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		tx.commit();
		return p;
	}
	
	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Person p = (Person) session.load(Person.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		tx.commit();
		logger.info("Person deleted successfully, person details="+p);
	}
}
