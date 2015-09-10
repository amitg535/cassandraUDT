package smcr.repository;

import java.util.List;

import smcr.domain.functions.Prescribed_FunctionDom;


public interface IPrescribedFunctionDAO {
	
	public void addPrescribedFunction(String code,boolean is_certified_fun, boolean is_snr_mgr_fun, String name) throws Exception;

	public List<Prescribed_FunctionDom> showPrescribedFunctions();

	

}
