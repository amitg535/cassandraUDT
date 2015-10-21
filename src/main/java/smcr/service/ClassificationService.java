package smcr.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smcr.command.Classification;
import smcr.controller.setup.FirmSetupController;
import smcr.domain.functions.ClassificationsDom;
import smcr.repository.nosql.ClassificationDAO;

@Service
public class ClassificationService implements IClassificationService {
	
	public static final Logger LOG = LoggerFactory.getLogger(ClassificationService.class);
	
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
	
	public synchronized List<Classification> findAll(String stringFilter) {
		
		List<Classification> classifications = new ArrayList<Classification>();
		
		List<ClassificationsDom> allClassifications = classificationDAO.showAllClassifications();
		
		for (ClassificationsDom classificationDom : allClassifications) {

			
			Classification classification = new Classification();
			
			classification.setCode(classificationDom.getCode());
			classification.setClassification_name(classificationDom.getClassification_name());
			try {
			boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                    || classification.toString().toLowerCase()
                    .contains(stringFilter.toLowerCase());
			System.out.println(classification.toString().toLowerCase());
			if(passesFilter) {
			classifications.add(classification.clone());
			
			}
			}catch (CloneNotSupportedException ex) {
                LOG.error("Clone Exception");
            } 
		}
		
		/*Collections.sort(classifications, new Comparator<Classification>() {

            @Override
            public int compare(Classification o1, Classification o2) {
                return (int) (o2.getCode() - o1.getCode());
            }
        });*/
		
		return classifications;
	}

}
