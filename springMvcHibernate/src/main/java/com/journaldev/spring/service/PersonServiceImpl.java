package com.journaldev.spring.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;

public class PersonServiceImpl implements PersonService{
	private PersonDAO personDAO;
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDAO.addPerson(p);
	}
	
	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}
	
	@Transactional
	@Override
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}
	
	@Transactional
	@Override
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}
	
	@Transactional
	@Override
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}
}
