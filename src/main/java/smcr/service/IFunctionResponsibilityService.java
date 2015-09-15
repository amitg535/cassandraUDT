package smcr.service;

import java.util.List;

import smcr.command.Responsibility;
import smcr.domain.functions.Applicable_FunctionsDom;
import smcr.domain.functions.Prescribed_FunctionDom;



//import smcr.domain.functions.Applicable_FunctionsDom;

public interface IFunctionResponsibilityService {
	
	public List<Applicable_FunctionsDom> showFunctionsResponsibilities();

	public List<Prescribed_FunctionDom> showPrescribedFunctions();
	
	public List<Responsibility> showPrescribedResponsibilities();
}
