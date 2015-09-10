package smcr.domain.functions;

import java.util.Set;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;





@UDT(name="people_responsibility", keyspace="smcrdb")
public class People_Responsibility {

	@Field(name="name")
	private String name;
	@Field(name="responsibility")
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