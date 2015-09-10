package smcr.service;

import java.util.List;

import smcr.domain.functions.ClassificationsDom;

public interface IClassificationService {
	
	public void addClassification() throws Exception;
	
	public void addClassification(String code, String name) throws Exception;
	
	public ClassificationsDom showClassification();
	
	public List<ClassificationsDom> showClassifications();

}
