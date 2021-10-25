package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.User;
import logic.CRUDService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private CRUDService service;
	
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
	
	@GetMapping("*")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();
		List<User> userlist = service.userlist();
		mav.addObject("userlist", userlist);
		return mav;
	}
	
}
