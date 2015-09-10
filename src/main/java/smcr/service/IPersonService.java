package smcr.service;

import smcr.domain.person.PersonDom;

public interface IPersonService {
	
	 public   void addPerson() throws Exception;
	 
	 public abstract  PersonDom showPerson();

}
