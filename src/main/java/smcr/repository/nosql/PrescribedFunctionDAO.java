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

import smcr.domain.functions.Prescribed_FunctionDom;
import smcr.repository.IPrescribedFunctionDAO;

@Repository
public class PrescribedFunctionDAO implements IPrescribedFunctionDAO {

    public static final Logger Log = LoggerFactory.getLogger(PrescribedFunctionDAO.class);
	
	@Autowired
	private CassandraOperations cassandraOperations;
	
	/*@Autowired
	private Prescribed_FunctionDom prescribed_FunctionDom;*/
	
	@Override
	public List<Prescribed_FunctionDom> showPrescribedFunctions() {
		
		//String selectAll = "Select code,name from prescribed_functions ";
		
		Select select = QueryBuilder.select("code","name").from("prescribed_functions");
		//Select select = QueryBuilder.select().from("prescribed_functions");
		List<Prescribed_FunctionDom> prescribedFunctions = cassandraOperations.query(select, new RowMapper<Prescribed_FunctionDom>() {

			@Override
			public Prescribed_FunctionDom mapRow(Row row, int rowNum)
					throws DriverException {
				
				Prescribed_FunctionDom pf = new Prescribed_FunctionDom(row.getString("code"), row.getString("name"));
				
				return pf;
			}
		});
	
			
		return prescribedFunctions;
	}

	@Override
	public void addPrescribedFunction(String code, boolean is_certified_fun,
			boolean is_snr_mgr_fun, String name) throws Exception {
		
		Prescribed_FunctionDom prescribedFunction = new Prescribed_FunctionDom(code,is_certified_fun,is_snr_mgr_fun,name);
		cassandraOperations.insert(prescribedFunction);
		
	}

}
