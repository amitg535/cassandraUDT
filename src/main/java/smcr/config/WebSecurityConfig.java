package smcr.config;


import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(99)
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/telephoneInterview").hasAuthority("manager")
                .antMatchers("/technicalInterview").hasAuthority("management")
                .antMatchers("/financialNegotiation").hasAuthority("finance")
                .antMatchers("/").authenticated()
                .and()
                .csrf().disable()
                .httpBasic();
    }
	
	@Bean
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
    }
    
         
  
    
   
}