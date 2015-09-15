package smcr.command;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.datastax.driver.core.UDTValue;


public class Applicable_Functions {
	
	
	private String function_code; 
    private Set<String> functions_responsibility;
    private Set<UDTValue> assigned_responsibilities; // set<frozen<people_responsibility>>
    
    public Applicable_Functions() {
    	super();
    }
	
    public String getFunction_code() {
		return function_code;
	}
	public void setFunction_code(String function_code) {
		this.function_code = function_code;
	}
	public Set<String> getFunctions_responsibility() {
		return functions_responsibility;
	}
	public void setFunctions_responsibility(Set<String> functions_responsibility) {
		this.functions_responsibility = functions_responsibility;
	}
	public Set<UDTValue> getAssigned_responsibilities() {
		return assigned_responsibilities;
	}
	public void setAssigned_responsibilities(
			Set<UDTValue> asignResp) {
		this.assigned_responsibilities = asignResp;
	}

}
