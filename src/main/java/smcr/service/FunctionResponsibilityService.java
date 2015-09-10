package smcr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smcr.domain.functions.Applicable_FunctionsDom;
import smcr.domain.functions.Prescribed_FunctionDom;
import smcr.repository.nosql.ApplicableFunctionsDAO;
import smcr.repository.nosql.PrescribedFunctionDAO;


@Service
public class FunctionResponsibilityService implements IFunctionResponsibilityService {
	
	@Autowired
	ApplicableFunctionsDAO applicableFunctionsDAO;
	
	@Autowired
	PrescribedFunctionDAO prescribedFunctionDAO;

	@Override
	public List<Applicable_FunctionsDom> showFunctionsResponsibilities() {
		
		//UDTMapper<People_Responsibility> mapper = new MappingManager(cassandraOperations.getSession()).udtMapper(People_Responsibility.class);
		
		List<Applicable_FunctionsDom> functionsResponsibility = applicableFunctionsDAO.showFunctionsResponsibilities();
		
		
		return functionsResponsibility;
	}

	@Override
	public List<Prescribed_FunctionDom> showPrescribedFunctions() {
		
		List<Prescribed_FunctionDom> prescribedFunctions = prescribedFunctionDAO.showPrescribedFunctions();
		return prescribedFunctions;
	}

}
