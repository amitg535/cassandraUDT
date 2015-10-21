package smcr.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.driver.core.exceptions.NoHostAvailableException;

import smcr.controller.setup.FirmSetupController;
import smcr.exceptions.resolver.SmcrExceptionHandlerExceptionResolver;




@Configuration
public class ExceptionConfiguration {

	protected Logger logger;

	public ExceptionConfiguration() {
		logger = LoggerFactory.getLogger(getClass());
		logger.info("Creating ExceptionConfiguration");
	}
	
	
	
/*	@Bean(name = "smcrExceptionHandlerExceptionResolver")
	public SmcrExceptionHandlerExceptionResolver createExceptionHandlerExceptionResolver() {
			logger.info("Creating ExceptionHandlerExceptionResolver");
		SmcrExceptionHandlerExceptionResolver resolver = new SmcrExceptionHandlerExceptionResolver();
		//Properties mappings = new Properties();
		//mappings.setProperty("NoHostAvailableException", "noHostAvailableException");
		//mappings.setProperty("InvalidCreditCardException", "creditCardError");
		
		resolver.setMappedHandlerClasses(new Class[] {NoHostAvailableException.class});

		//resolver.setExceptionMappings(mappings);

	return resolver;
	}*/
}
