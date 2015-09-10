package smcr.repository;

import smcr.domain.person.PersonDom;

public interface IPersonDAO {

	public void addPerson() throws Exception;

	public PersonDom showPerson();

}
