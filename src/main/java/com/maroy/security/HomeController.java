package com.maroy.security;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maroy.entity.UserEntity;
import com.maroy.service.UserService;
import com.maroy.ssh.DevToolSSHOperations;

/**
 * Handles requests for the application home page.
 */
@Controller
@PropertySource("classpath:environment.properties")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private DevToolSSHOperations devToolSSHOPerations;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "index";
	}
	
    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", "true");
        return "accessdenied";
    }
 
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "logout";
    }
    
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(UserEntity user){
    	
    	
    	userService.addUser(user);
    	
    	return "success";
    }
    
    @RequestMapping(value = "/devtools", method = RequestMethod.GET)
    public String displayDevTools(Model model){
    	return "devtools";
    }
    
    @ResponseBody
    @RequestMapping(value = "/tailLog", method = RequestMethod.POST)
    public String tailLogs(@RequestBody String machineName){
    	
    	String command = "tail -f "+environment.getProperty(machineName+"_log");
    	String hostname = environment.getProperty(machineName+"_hostname");
    	
    	devToolSSHOPerations.sendCommand(command, hostname);
    	
    	return devToolSSHOPerations.getOutputBuffer().toString();
    }
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
