package smcr.controller.assign;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;










import smcr.command.Applicable_Functions;
import smcr.command.FunctionsResponsibility;
import smcr.command.Responsibility;
import smcr.domain.functions.Applicable_FunctionsDom;
import smcr.domain.functions.Prescribed_FunctionDom;
import smcr.service.FunctionResponsibilityService;


@Controller
public class AssignmentController {
	
	
	
	public static final Logger LOG = LoggerFactory.getLogger(AssignmentController.class);
	
	@Autowired
	FunctionResponsibilityService functionResponsibilityService;
	
	@ModelAttribute("prescribedFunctions")
	public List<String> showAllFunctionCode() {
		List<String> allFunctionCodes = new ArrayList<String> ();
		List<Prescribed_FunctionDom> allFunctionDetail =functionResponsibilityService.showPrescribedFunctions();
		
		for(Prescribed_FunctionDom pf : allFunctionDetail) {
			allFunctionCodes.add(pf.getCode());
		}
		
		return allFunctionCodes;
	}
	
	@ModelAttribute("functionsResponsibility")
	public List<Applicable_FunctionsDom> showAllFunctionsResponsibilities () {
		
		List<Applicable_FunctionsDom> functionsResponsibility = functionResponsibilityService.showFunctionsResponsibilities();
		
		return functionsResponsibility;
	}
	
	@ModelAttribute("responsibilities")
	public List<Responsibility> showAllPrescribedResponsibilities() {
		
		List<Responsibility> allResponsibilities = functionResponsibilityService.showPrescribedResponsibilities();
		
		return allResponsibilities;
	}
	
	
	
	
	@RequestMapping("assign/assignmentANDallocation")
	public String showAssignedResponsibility(Model model,@ModelAttribute("applicableFunc") Applicable_Functions applicableFunc) throws Exception {
		
		
		 
		//List<Applicable_FunctionsDom> functionsResponsibility = functionResponsibilityService.showFunctionsResponsibilities();
		//funcResp.setPrescribedFunctions(functionResponsibilityService.showPrescribedFunctions()); 
		//LOG.info(String.format( "Found prescribed Functions with  [%s] ",functionResponsibilityService.showPrescribedFunctions()));
		//model.addAttribute("functionsResponsibility",functionsResponsibility);
	/*	for(Prescribed_FunctionDom pf : funcResp.getPrescribedFunctions()) {
			LOG.info(String.format("Code: [%s]", pf.getCode()));
		}
		model.addAttribute("funcResp",funcResp); */
		//applicableFunc.setFunction_code("Select");
		
		return "/assign/showResponsibilityAssignment";
	}
	
	

}
