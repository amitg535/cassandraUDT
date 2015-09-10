package smcr.domain.Responsibility;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="individual_responsibility")
public class ResponsibilityDom {
	@PrimaryKey(value="code")
	private String code;
    private String name;
	private boolean is_prescribed;
	private String description;   
	private String comments;
	private String reg_body;

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
	public boolean isIs_prescribed() {
		return is_prescribed;
	}
	public void setIs_prescribed(boolean is_prescribed) {
		this.is_prescribed = is_prescribed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getReg_body() {
		return reg_body;
	}
	public void setReg_body(String reg_body) {
		this.reg_body = reg_body;
	}

}
