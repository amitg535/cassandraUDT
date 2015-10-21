package smcr.service;

import java.util.List;

import smcr.domain.person.PersonDom;

public interface IPersonService {
	
	 public   void addPerson() throws Exception;
	 
	 public abstract  PersonDom showPerson();
	 
	 public List<PersonDom> showAllPersons();

}
