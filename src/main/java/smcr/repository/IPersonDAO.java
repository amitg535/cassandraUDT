package smcr.repository;

import java.util.List;

import smcr.domain.person.PersonDom;

public interface IPersonDAO {

	public void addPerson() throws Exception;

	public PersonDom showPerson();
	
	public List<PersonDom> showAllPersons();

}
