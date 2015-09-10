package smcr.controller.assign;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;





import smcr.command.FunctionsResponsibility;
import smcr.domain.functions.Applicable_FunctionsDom;
import smcr.domain.functions.Prescribed_FunctionDom;
import smcr.service.FunctionResponsibilityService;


@Controller
public class AssignmentController {
	
	
	
	public static final Logger LOG = LoggerFactory.getLogger(AssignmentController.class);
	
	@Autowired
	FunctionResponsibilityService functionResponsibilityService;
	
	
	
	
	@RequestMapping("assign/assignmentANDallocation")
	public String showAssignedResponsibility(Model model, FunctionsResponsibility funcResp, BindingResult bindingResult) throws Exception {
		
		
		 
		List<Applicable_FunctionsDom> functionsResponsibility = functionResponsibilityService.showFunctionsResponsibilities();
		funcResp.setPrescribedFunctions(functionResponsibilityService.showPrescribedFunctions()); 
		LOG.info(String.format( "Found prescribed Functions with  [%s] ",functionResponsibilityService.showPrescribedFunctions()));
		model.addAttribute("functionsResponsibility",functionsResponsibility);
		model.addAttribute("funcResp",funcResp);
		
		return "/assign/showResponsibilityAssignment";
	}

}
