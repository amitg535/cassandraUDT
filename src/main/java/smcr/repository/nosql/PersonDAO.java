package smcr.repository.nosql;

import smcr.domain.person.PersonDom;
import smcr.repository.IPersonDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;


@Repository
public class PersonDAO implements IPersonDAO {
	
	@Autowired
	private CassandraOperations cassandraOperations;
	
	 
	public void addPerson() throws Exception{
		//PersonDom(String username, String authority, boolean enabled, String first_name, boolean is_admin, String last_name, String password)
		 cassandraOperations.insert(new PersonDom("sajuv", "Role_SM", true, "Saju", false,"Thomas","12345678"));
		//throw new Exception ("checking error handling in controller");
	 }

	@Override
	public PersonDom showPerson() {
		// TODO Auto-generated method stub
		return null;
	}

}
