package smcr.command;

import java.util.Set;

import com.datastax.driver.mapping.annotations.UDT;

@UDT (keyspace="smcrdb", name="people_responsibility")
public class People_Responsibility {

	
	private String name;
	private Set<String> responsibility;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(Set<String> responsibility) {
		this.responsibility = responsibility;
	}
	
	
}