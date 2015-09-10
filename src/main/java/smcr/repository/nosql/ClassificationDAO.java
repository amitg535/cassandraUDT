package smcr.repository.nosql;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import smcr.domain.functions.ClassificationsDom;
import smcr.repository.IClassificationDAO;

@Repository
public class ClassificationDAO implements IClassificationDAO {
	
	public static final Logger Log = LoggerFactory.getLogger(ClassificationDAO.class);
	
	@Autowired
	private CassandraOperations cassandraOperations;

	@Override
	public void addClassifications() throws Exception {
		// TODO Auto-generated method stub
		//insert into classification (code,classification_name)values ('ned', 'Non-Executive Director');
		cassandraOperations.insert(new ClassificationsDom("aux","Auxillary"));
		Log.info(" Classification added");

	}

	@Override
	public ClassificationsDom showClassification() {
		
		return null;
	}

	@Override
	public void addClassifications(String code, String name) throws Exception {
		// TODO Auto-generated method stub
		
		cassandraOperations.insert(new ClassificationsDom(code,name));
		Log.info(" Classification added via web application");
		
	}

	@Override
	public List<ClassificationsDom> showAllClassifications() {
		String classificationAll = "select * from classification";
	List<ClassificationsDom> classifications =	cassandraOperations.select(classificationAll, ClassificationsDom.class);
	
	for(ClassificationsDom c : classifications) {
		Log.info(String.format("Found Classification with Code [%s] and name [%s]",c.getCode(),c.getClassification_name()));
		
	}
	return classifications;
}
}
