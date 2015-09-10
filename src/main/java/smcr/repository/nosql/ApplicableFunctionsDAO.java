package smcr.repository.nosql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.UDTMapper;

import smcr.domain.functions.Applicable_FunctionsDom;
import smcr.domain.functions.People_Responsibility;
import smcr.repository.IApplicableFunctionsDAO;


@Repository
public class ApplicableFunctionsDAO implements IApplicableFunctionsDAO {
	
public static final Logger Log = LoggerFactory.getLogger(ApplicableFunctionsDAO.class);
	
	@Autowired
	private CassandraOperations cassandraOperations;
	
	/*@Autowired
	private Applicable_FunctionsDom applicableFunctions; */

	@Override
	public void addApplicableFunction() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Applicable_FunctionsDom showPrescribedFunctions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Applicable_FunctionsDom> showFunctionsResponsibilities() {
		
		String functionsResponsibility = "Select function_code,functions_responsibility,assigned_responsibilities from functions";
		
		UDTMapper<People_Responsibility> mapper = new MappingManager(cassandraOperations.getSession()).udtMapper(People_Responsibility.class);
		ResultSet results = cassandraOperations.getSession().execute(functionsResponsibility);
		List<Applicable_FunctionsDom> allApplicableFunctions = new ArrayList<Applicable_FunctionsDom>();
		//List<Row> appliedResponsibility =results.all();
		for (Row row : results) {
			Applicable_FunctionsDom applicableFunctions = new Applicable_FunctionsDom();
			   System.out.println(row.getString("function_code"));
			   applicableFunctions.setFunction_code(row.getString("function_code"));
			   applicableFunctions.setFunctions_responsibility(row.getSet("functions_responsibility", String.class));
			   Set<UDTValue> assignedResponsibilities = row.getSet("assigned_responsibilities", UDTValue.class);
			   Set<People_Responsibility> peopleResponsibilities = new HashSet<People_Responsibility>();
			   for (UDTValue aResp : assignedResponsibilities) {
				  
				   People_Responsibility people_responsibility = new People_Responsibility();
				   people_responsibility = mapper.fromUDT(aResp);
				   peopleResponsibilities.add(people_responsibility);
				   System.out.println( " people_responsibility: " + people_responsibility);
			   }
			applicableFunctions.setAssigned_responsibilities(peopleResponsibilities);
			
			System.out.println(applicableFunctions.getFunction_code() +applicableFunctions.getFunctions_responsibility() + applicableFunctions.getAssigned_responsibilities() );
			
			allApplicableFunctions.add(applicableFunctions);
		} 

		
		
		
		return allApplicableFunctions;
	}

}
