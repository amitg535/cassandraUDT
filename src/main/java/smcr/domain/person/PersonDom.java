package smcr.domain.person;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="user")
public class PersonDom {
	@PrimaryKey(value="username")
	private String username;
    private String authority;
	private boolean enabled;   
	private String first_name;
    private boolean is_admin;
    private String last_name;
	private String password;
	
	public PersonDom(String username, String authority, boolean enabled, String first_name, boolean is_admin, String last_name, String password) {
		this.username = username;
		this.authority = authority;
		this.enabled = enabled;
		this.first_name = first_name;
		this.is_admin = is_admin;
		this.last_name = last_name;
		this.password = password;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
