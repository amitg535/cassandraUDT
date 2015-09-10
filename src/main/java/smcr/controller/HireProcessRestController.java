package smcr.controller;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import smcr.domain.Applicant;
import smcr.repository.rdbms.ApplicantRepository;

import java.util.HashMap;
import java.util.Map;

@Controller
//@RestController
public class HireProcessRestController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ApplicantRepository applicantRepository;

    @ResponseStatus(value = HttpStatus.OK)
   //@RequestMapping(value = "/start-hire-process", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/start-hire-process", method = RequestMethod.POST)
    public String HireProcess(@ModelAttribute("name") String name, @ModelAttribute("email") String email, @ModelAttribute("phoneNumber") String phoneNumber) {

        Applicant applicant = new Applicant(name, email,phoneNumber);
      //  Applicant applicant = new Applicant(data.get("name"));//, data.get("email"), data.get("phoneNumber"));
        applicantRepository.save(applicant);

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applicant", applicant);
        runtimeService.startProcessInstanceByKey("hireProcessWithJpa", variables);
        
        return "resumeStored";
    }

}