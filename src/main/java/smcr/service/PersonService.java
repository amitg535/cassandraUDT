package smcr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smcr.repository.nosql.PersonDAO;
import smcr.domain.person.PersonDom;;

@Service
public class PersonService implements IPersonService {

	@Autowired
	PersonDAO personDao;
	
	public   void addPerson() throws Exception {
		// TODO Auto-generated method stub
		
		personDao.addPerson();

	}

	@Override
	public PersonDom showPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonDom> showAllPersons() {
		// TODO Auto-generated method stub
		return null;
	}

}
