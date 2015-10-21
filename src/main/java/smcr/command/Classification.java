package smcr.command;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.beanutils.BeanUtils;

import org.hibernate.validator.constraints.NotEmpty;






public class Classification implements Serializable, Cloneable  {
	
	@NotNull
	@Size(min=3, max=6)
	private String code ;
	
	@NotEmpty
	@Size(max=30)
	private String classification_name;
	
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
	
	 @Override
	    public Classification clone() throws CloneNotSupportedException {
	        try {
	            return (Classification) BeanUtils.cloneBean(this);
	        } catch (Exception ex) {
	            throw new CloneNotSupportedException();
	        }
	    } 

	 @Override
	    public String toString() {
	        return "Classification{" + "code=" + code + ", classification_name=" + classification_name + '}';
	    }

}
