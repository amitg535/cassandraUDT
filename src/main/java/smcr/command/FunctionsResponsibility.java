package smcr.command;

import java.util.List;

import smcr.domain.functions.Prescribed_FunctionDom;

public class FunctionsResponsibility {
	
	private List<Prescribed_FunctionDom> prescribedFunctions;

	public List<Prescribed_FunctionDom> getPrescribedFunctions() {
		return prescribedFunctions;
	}

	public void setPrescribedFunctions(List<Prescribed_FunctionDom> prescribedFunctions) {
		this.prescribedFunctions = prescribedFunctions;
	}

}
