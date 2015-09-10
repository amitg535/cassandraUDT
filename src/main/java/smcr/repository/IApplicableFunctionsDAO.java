package smcr.repository;


import java.util.List;


import smcr.domain.functions.Applicable_FunctionsDom;

public interface IApplicableFunctionsDAO {
	
	public void addApplicableFunction() throws Exception;

	public Applicable_FunctionsDom showPrescribedFunctions();
	
	public List<Applicable_FunctionsDom> showFunctionsResponsibilities();

}
