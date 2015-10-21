package smcr.repository.nosql;

import java.util.List;

import smcr.domain.Responsibility.ResponsibilityDom;
import smcr.domain.person.PersonDom;
import smcr.repository.IPersonDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.RowMapper;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;


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

	@Override
	public List<PersonDom> showAllPersons() {
		Select select = QueryBuilder.select().from("user");
		//Select select = QueryBuilder.select().from("prescribed_functions");
		List<PersonDom> allPersons = cassandraOperations.query(select, new RowMapper<PersonDom>() {

			@Override
			public PersonDom mapRow(Row row, int rowNum)
					throws DriverException {
				
				
				PersonDom person = new PersonDom(row.getString("username"), row.getString("authority"), row.getBool("enabled"), row.getString("first_name"), row.getBool("is_Admin"), row.getString("last_name"),row.getString("password"));
				
				return person;
			}
		});
	
			
		return allPersons;
	}

}
