package smcr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smcr.domain.functions.ClassificationsDom;
import smcr.repository.nosql.ClassificationDAO;

@Service
public class ClassificationService implements IClassificationService {
	
	@Autowired
    ClassificationDAO classificationDAO;
	
	@Override
	public void addClassification() throws Exception {
		classificationDAO.addClassifications();
		
	}

	@Override
	public ClassificationsDom showClassification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addClassification(String code, String name) throws Exception {
		
		classificationDAO.addClassifications(code, name);
		
	}

	@Override
	public List<ClassificationsDom> showClassifications() {
		
		List<ClassificationsDom> allClassifications = classificationDAO.showAllClassifications();
		return allClassifications;
	}

}
