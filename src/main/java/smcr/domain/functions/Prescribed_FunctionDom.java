package smcr.domain.functions;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="prescribed_functions")
public class Prescribed_FunctionDom {
	@PrimaryKey(value="code")
	private String code;    
	private String name;
	private boolean is_senior_mgr_function;
	private boolean is_certified_staff_function;
	
	public Prescribed_FunctionDom(String code, boolean is_certified_fun,
			boolean is_snr_mgr_fun, String name) {
		
		this.code = code;
		is_certified_staff_function = is_certified_fun;
		is_senior_mgr_function = is_snr_mgr_fun;
		this.name = name;
		
	}
	
	public Prescribed_FunctionDom(String code,String name) {
		
		this.code = code;
		this.name = name;
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIs_senior_mgr_function() {
		return is_senior_mgr_function;
	}
	public void setIs_senior_mgr_function(boolean is_senior_mgr_function) {
		this.is_senior_mgr_function = is_senior_mgr_function;
	}
	public boolean isIs_certified_staff_function() {
		return is_certified_staff_function;
	}
	public void setIs_certified_staff_function(boolean is_certified_staff_function) {
		this.is_certified_staff_function = is_certified_staff_function;
	}

}
