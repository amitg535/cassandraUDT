package smcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmcrApplication {

   
	public static void main(String[] args) throws Exception {
  	
    	
        SpringApplication.run(SmcrApplication.class, args);
    }
    
   /* @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {

                               
                // install groups & users
                Group manager = group("manager");
                Group management = group("management");
                Group finance = group("finance");

                User amit = user("agupta", "Amit", "Gupta");
                identityService.createMembership(amit.getId(), manager.getId());
                
                User sameer = user("sdeshmukh", "Sameer", "Deshmukh");
                identityService.createMembership(sameer.getId(), manager.getId());
                identityService.createMembership(sameer.getId(), management.getId());
                
                
                User pranesh = user("pshah", "Pranesh", "Shah");
                identityService.createMembership(pranesh.getId(), finance.getId());
                
                
            }

            private User user(String userName, String f, String l) {
                User u = identityService.newUser(userName);
                u.setFirstName(f);
                u.setLastName(l);
                u.setPassword("password");
                identityService.saveUser(u);
                return u;
            }

            private Group group(String groupName) {
                Group group = identityService.newGroup(groupName);
                group.setName(groupName);
                group.setType("security-role");
                identityService.saveGroup(group);
                return group;
            }
       

           
        };
    } */
   
}
