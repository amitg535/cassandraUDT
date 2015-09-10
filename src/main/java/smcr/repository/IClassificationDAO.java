package smcr.repository;

import java.util.List;

import smcr.domain.functions.ClassificationsDom;


public interface IClassificationDAO {
	
	public void addClassifications() throws Exception;
	
	public void addClassifications(String code,String name) throws Exception;

	//public ClassificationsDom showPrescribedFunctions();om
	
	List<ClassificationsDom> showAllClassifications();

	ClassificationsDom showClassification();

}
