package smcr.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smcr.command.Responsibility;
import smcr.domain.Responsibility.ResponsibilityDom;
import smcr.domain.functions.Applicable_FunctionsDom;
import smcr.domain.functions.Prescribed_FunctionDom;
import smcr.repository.nosql.ApplicableFunctionsDAO;
import smcr.repository.nosql.PrescribedFunctionDAO;
import smcr.repository.nosql.ResponsibilityDAO;


@Service
public class FunctionResponsibilityService implements IFunctionResponsibilityService {
	
	public static final Logger LOG = LoggerFactory.getLogger(FunctionResponsibilityService.class);
	
	@Autowired
	ApplicableFunctionsDAO applicableFunctionsDAO;
	
	@Autowired
	PrescribedFunctionDAO prescribedFunctionDAO;
	
	@Autowired
	ResponsibilityDAO responsibilityDAO;

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

	@Override
	public List<Responsibility> showPrescribedResponsibilities() {
		
		List<Responsibility> responsibilitiesCom = new ArrayList<Responsibility> ();
		
		List<ResponsibilityDom> responsibilitiesDom = responsibilityDAO.showPrescribedResponsibilities();
		
		for(ResponsibilityDom responsibility : responsibilitiesDom) {
			
			Responsibility resp = new Responsibility();
			
			resp.setCode(responsibility.getCode());
			resp.setName(responsibility.getName());
			resp.setIs_prescribed(responsibility.isIs_prescribed());
			resp.setDescription(responsibility.getDescription());
			resp.setComments(responsibility.getComments());
			resp.setReg_body(responsibility.getReg_body());
			
			responsibilitiesCom.add(resp);
		}
		
		return responsibilitiesCom;
	}

	@Override
	public void updateFunctionsResponsibility(String functionCode,
			String responsibility) {
		// TODO Auto-generated method stub
		applicableFunctionsDAO.updateFunctionsResponsibility(functionCode, responsibility);
		
	}

}
