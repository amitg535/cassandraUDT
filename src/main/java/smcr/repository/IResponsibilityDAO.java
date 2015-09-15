package smcr.repository;

import java.util.List;

import smcr.domain.Responsibility.ResponsibilityDom;


public interface IResponsibilityDAO {
	
	public void addResponsibility() throws Exception;

	public ResponsibilityDom showPrescribedFunctions();
	
	public List<ResponsibilityDom> showPrescribedResponsibilities();

}
