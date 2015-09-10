package smcr.domain.functions;

import java.util.Set;

import org.springframework.stereotype.Component;

//@Table(value="functions")
@Component
public class Applicable_FunctionsDom {
	
	//@PrimaryKey(value="function_code")
	private String function_code; 
    private Set<String> functions_responsibility;
    private Set<People_Responsibility> assigned_responsibilities; // set<frozen<people_responsibility>>
	
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
	public Set<People_Responsibility> getAssigned_responsibilities() {
		return assigned_responsibilities;
	}
	public void setAssigned_responsibilities(
			Set<People_Responsibility> assigned_responsibilities) {
		this.assigned_responsibilities = assigned_responsibilities;
	}

}
