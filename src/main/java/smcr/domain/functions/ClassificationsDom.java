package smcr.domain.functions;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="classification")
public class ClassificationsDom {
	@PrimaryKey(value="code")
	private String code ;
	private String classification_name;
	
	public ClassificationsDom(String code, String classification_name) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.classification_name = classification_name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getClassification_name() {
		return classification_name;
	}
	public void setClassification_name(String classification_name) {
		this.classification_name = classification_name;
	}

}
