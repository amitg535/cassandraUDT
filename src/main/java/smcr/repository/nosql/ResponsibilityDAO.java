package smcr.repository.nosql;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.RowMapper;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import smcr.domain.Responsibility.ResponsibilityDom;
import smcr.repository.IResponsibilityDAO;

@Repository
public class ResponsibilityDAO implements IResponsibilityDAO {
	
	 public static final Logger Log = LoggerFactory.getLogger(ResponsibilityDAO.class);
		
		@Autowired
		private CassandraOperations cassandraOperations;

	@Override
	public void addResponsibility() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ResponsibilityDom showPrescribedFunctions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResponsibilityDom> showPrescribedResponsibilities() {
	//String selectAll = "Select code,name from prescribed_functions ";
		
		Select select = QueryBuilder.select().from("individual_responsibility");
		//Select select = QueryBuilder.select().from("prescribed_functions");
		List<ResponsibilityDom> prescribedResponsibility = cassandraOperations.query(select, new RowMapper<ResponsibilityDom>() {

			@Override
			public ResponsibilityDom mapRow(Row row, int rowNum)
					throws DriverException {
				
				
				ResponsibilityDom resp = new ResponsibilityDom(row.getString("code"), row.getString("name"), row.getBool("is_prescribed"), row.getString("description"), row.getString("comments"), row.getString("reg_body"));
				
				return resp;
			}
		});
	
			
		return prescribedResponsibility;
	}

}
