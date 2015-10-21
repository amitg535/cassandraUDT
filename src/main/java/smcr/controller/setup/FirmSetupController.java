package smcr.controller.setup;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.datastax.driver.core.exceptions.NoHostAvailableException;

import smcr.command.Classification;
import smcr.domain.functions.ClassificationsDom;
import smcr.service.ClassificationService;
import smcr.service.PersonService;

@Controller
public class FirmSetupController {

	public static final Logger LOG = LoggerFactory.getLogger(FirmSetupController.class);
	
		
	@Autowired
	ClassificationService classificationService;
	
	@Autowired
	PersonService personService;
	
	
	@RequestMapping("/firmSetup")
	public String firmSetup() throws Exception {
		
	return "/setup/firmSetup";
	}
	
	@RequestMapping("/addClassification")
	public String addClassification(Classification classification) throws Exception {
		
		
		
		
	return "setup/addClassification";	
	}
	
	@RequestMapping("/classificationAdded")
	public String classificationAdded(@Valid Classification classification,BindingResult bindingResult, Model model) throws Exception {
		
		if(bindingResult.hasErrors()) {
			return "setup/addClassification";
		}

		String code = classification.getCode();
		String classification_name = classification.getClassification_name();
		
		if(classification_name.equals("amit") ) throw new Exception();
		
		classificationService.addClassification(code,classification_name);
		List<ClassificationsDom> allClassifications =classificationService.showClassifications();
		for(ClassificationsDom c : allClassifications) {
			LOG.info(String.format("Found Classification within controller Code [%s] and name [%s]",c.getCode(),c.getClassification_name()));
					}
		model.addAttribute("allClassifications", allClassifications);
		
	return "setup/classificationAdded";	
	}
	
	@RequestMapping ("/personAdded")
	public String addPerson() throws Exception {
		
		/*try {
		personService.addPerson();
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}*/
		
		personService.addPerson();
		
	 return "personAdded";	
	}
	
	/**
	 * No handler is needed for this exception since it is annotated with
	 * <tt>@ResponseStatus</tt>.
	 * 
	 * @return Nothing - it always throws the exception.
	 * @throws OrderNotFoundException
	 *             Always thrown.
	 */
/*	@RequestMapping("/orderNotFound")
	String throwOrderNotFoundException() {
		LOG.info("Throw OrderNotFoundException for unknown order 12345");
		throw new OrderNotFoundException("12345");
	} */
	
	
	/**
	 * Throws an unannotated <tt>DataIntegrityViolationException</tt>. Must be
	 * caught by an exception handler.
	 * 
	 * @return Nothing - it always throws the exception.
	 * @throws DataIntegrityViolationException
	 *             Always thrown.
	 */
	/*@RequestMapping("/dataIntegrityViolation")
	String throwDataIntegrityViolationException() throws SQLException {
		LOG.info("Throw DataIntegrityViolationException");
		throw new DataIntegrityViolationException("Duplicate id");
	}*/
	
	/**
	 * Simulates a database exception by always throwing <tt>SQLException</tt>.
	 * Must be caught by an exception handler.
	 * 
	 * @return Nothing - it always throws the exception.
	 * @throws SQLException
	 *             Always thrown.
	 */
	/*@RequestMapping("/databaseError1")
	String throwDatabaseException1() throws SQLException {
		LOG.info("Throw SQLException");
		throw new SQLException();
	}*/

	/**
	 * Simulates a database exception by always throwing
	 * <tt>DataAccessException</tt>. Must be caught by an exception handler.
	 * 
	 * @return Nothing - it always throws the exception.
	 * @throws DataAccessException
	 *             Always thrown.
	 */
	/*@RequestMapping("/databaseError2")
	String throwDatabaseException2() throws QueryTimeoutException {
		LOG.info("Throw DataAccessException");
		throw new QueryTimeoutException("Error accessing database");
	}*/

	/**
	 * Always throws a <tt>SupportInfoException</tt>. Must be caught by an
	 * exception handler.
	 * 
	 * @return Nothing - it always throws the exception.
	 * @throws SupportInfoException
	 *             Always thrown.
	 */
/*	@RequestMapping("/supportInfoException")
	String throwCustomException() throws Exception {
		LOG.info("Throw SupportInfoException");
		throw new SupportInfoException("Custom exception occurred");
	} */
	
	
	
	
	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
	/* . . . . . . . . . . . . . EXCEPTION HANDLERS . . . . . . . . . . . . .. */
	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
	
	
	@ExceptionHandler(NoHostAvailableException.class)
	public ModelAndView dataBaseError(HttpServletRequest req, Exception exception) {
		 LOG.error("Request: " + req.getRequestURL() + " raised " + exception);
		 ModelAndView mav = new ModelAndView();
		    mav.addObject("exception", exception);
		    mav.addObject("url", req.getRequestURL());
		    mav.setViewName("databaseError");
		    return mav;

		
	 	
	}
	
	@ExceptionHandler(Exception.class)
	  public ModelAndView handleError(HttpServletRequest req, Exception exception) {
	    LOG.error("Request: " + req.getRequestURL() + " raised " + exception);

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("error");
	    return mav;
	  }


}	
