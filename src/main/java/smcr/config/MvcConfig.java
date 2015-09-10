package smcr.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/storeResume").setViewName("storeResume");
		//registry.addViewController("/start-hire-process").setViewName("resumeStored");
				
	}
	
	@Bean (name= "messageSource")
	public MessageSource messageSource() {
		String[] messageLocations = { "templates/home","templates/assign/assignmentANDallocation","templates/setup/addClassification",
				"templates/setup/firmSetup","templates/reports/displayReports"};
		//ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(messageLocations);
		//
		//messageSource.setBasenames(arg0);
		//messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setFallbackToSystemLocale(false);
		
		return messageSource;
	}
	
	/*	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setCacheable(false);
	   // templateResolver.setPrefix("/classes/templates/");
	    
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("XHTML");
	    return templateResolver; 
	} */

 /*	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    return templateEngine;
	}


	
	@Bean
	public ViewResolver viewResolver() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setCharacterEncoding("UTF-8");
	    viewResolver.setOrder(1);
	   // viewResolver.setViewNames("*.html,*.xhtml");
	    return viewResolver; 
	} */
	

	
	
}
