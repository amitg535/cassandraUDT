package smcr.repository;


import java.util.List;


import smcr.domain.functions.Applicable_FunctionsDom;

public interface IApplicableFunctionsDAO {
	
	public void addApplicableFunction() throws Exception;
	
	public void updateFunctionsResponsibility(String function_code, String responsibility);

	public Applicable_FunctionsDom showPrescribedFunctions();
	
	public List<Applicable_FunctionsDom> showFunctionsResponsibilities();

}
