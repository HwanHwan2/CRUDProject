package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.User;

@Controller
@RequestMapping
public class MainController {

	@RequestMapping(value = "/main")
	public ModelAndView main(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
}
