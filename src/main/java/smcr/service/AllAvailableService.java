package smcr.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smcr.command.AvailableFunctions;
import smcr.command.Responsibility;
import smcr.domain.functions.Applicable_FunctionsDom;
import smcr.domain.functions.People_Responsibility;
import smcr.domain.functions.Prescribed_FunctionDom;
import smcr.domain.person.PersonDom;
import smcr.repository.nosql.ApplicableFunctionsDAO;
import smcr.repository.nosql.PersonDAO;
import smcr.repository.nosql.PrescribedFunctionDAO;

@Service
public class AllAvailableService implements IAllAvailableService {
	
	public static final Logger LOG = LoggerFactory.getLogger(AllAvailableService.class);
	
	@Autowired
	ApplicableFunctionsDAO applicableFunctionsDAO;
	
	@Autowired
	PrescribedFunctionDAO prescribedFunctionDAO;
	
	@Autowired
	PersonDAO personDAO;
	
	@Autowired
	FunctionResponsibilityService responsibilityService;

	@Override
	public List<AvailableFunctions> allAvailableFunctions() {
		
	List<Prescribed_FunctionDom> allPrescribedFunctions = 	prescribedFunctionDAO.showPrescribedFunctions();
	List<Responsibility> allResponsibilities = responsibilityService.showPrescribedResponsibilities();
	List<Applicable_FunctionsDom> assignedFunctions =  applicableFunctionsDAO.showFunctionsResponsibilities();
	List<PersonDom> allPersons = personDAO.showAllPersons();
	List<String> allFunctionCode = new ArrayList<String>(); 
	List<String> allResponsibilityCode = new ArrayList<String>();
	List<String> allUserName = new ArrayList<String>();
	List<String> assignedFunctionCode = new ArrayList<String>();
	List<String> assignedResponsibilityCodes = new ArrayList<String>();
	List<String> assignedUserNames = new ArrayList<String>();
	List<AvailableFunctions> allAvailableFunc = new ArrayList<AvailableFunctions>(); 
	for(Prescribed_FunctionDom pf : allPrescribedFunctions) {
		LOG.info("all function code: " + pf.getCode());
		allFunctionCode.add(pf.getCode());
	}
	for(Applicable_FunctionsDom af : assignedFunctions) {
		assignedFunctionCode.add(af.getFunction_code());
	}	
	AvailableFunctions allAvailable = new AvailableFunctions();
	List<String> availableFunctionCode = new ArrayList<String>();
	for(String code : allFunctionCode) {
		//
		LOG.info("assigned function code: " + code);	
	if(!assignedFunctionCode.contains(code)) {
		LOG.info("available function code: " + code);
	
		//AvailableFunctions allAvailable = new AvailableFunctions();
			availableFunctionCode.add(code);
		//	allAvailable.setAvailabeFunction(availableFunctionCode);
		
		}
	allAvailable.setAvailabeFunction(availableFunctionCode);	
	}
	
	for(Responsibility resp : allResponsibilities ) {
		allResponsibilityCode.add(resp.getCode());	
	}
	
	for(Applicable_FunctionsDom af : assignedFunctions) {
	   for(String eachResp:	af.getFunctions_responsibility()) {
		   assignedResponsibilityCodes.add(eachResp);
	   }
	}
    List<String> availableResponsibilityCode = new ArrayList<String>();
    for(String resCode : allResponsibilityCode) {
    	if(!assignedResponsibilityCodes.contains(resCode)) {
    		availableResponsibilityCode.add(resCode);
    	}
    	allAvailable.setAvailableResponsibility(availableResponsibilityCode);
    }
    
    for(PersonDom person : allPersons) {
    	allUserName.add(person.getUsername());
    }
    
    for(Applicable_FunctionsDom af : assignedFunctions) {
    	for(People_Responsibility pr :af.getAssigned_responsibilities()) {
    		assignedUserNames.add(pr.getName()); 
    	}
    }
     
    List<String> availableUserName = new ArrayList<String>();
    for(String userName : allUserName) {
    	if(!assignedUserNames.contains(userName)) {
    		availableUserName.add(userName);
    	}
    	allAvailable.setAvailablePeople(availableUserName);
    }
    
    
    
	
	allAvailableFunc.add(allAvailable);
	
	return allAvailableFunc;
}
}
