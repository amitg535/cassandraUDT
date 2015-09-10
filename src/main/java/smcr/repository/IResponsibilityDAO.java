package smcr.repository;

import smcr.domain.Responsibility.ResponsibilityDom;


public interface IResponsibilityDAO {
	
	public void addResponsibility() throws Exception;

	public ResponsibilityDom showPrescribedFunctions();

}
