package smcr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {
	
public static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	
	
	@RequestMapping("/home")
	public String dashBoard() throws Exception {
		
		
		
	return "home";	
	}

}
