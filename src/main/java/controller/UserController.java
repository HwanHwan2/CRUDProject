package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dto.User;
import logic.CRUDService;
import logic.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private CRUDService service;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/test")
	public ModelAndView test(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		List<User> userlist = service.userlist();
		mav.addObject("userlist", userlist);
		return mav;
	}
	
	//===================== 회원가입 GET ============================
	@RequestMapping(value = "entry.do", method = RequestMethod.GET)
	public String entry(@ModelAttribute("user") User user, Model model) {
		return null;
	} 
	
	/*===================== 회원가입 POST ============================
		BindingResult는 반드시 @ModelAttribute 뒤에 추가해야함.
		
	*/
	@RequestMapping(value = "entry.do", method = RequestMethod.POST)
	public ModelAndView entry(@ModelAttribute("user") User user, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			bindingResult.reject("error.user.entry");
		} else {
			userService.entry(user);
			mav.setViewName("redirect:/user/test.do");
		}
		
		return mav;
	}
	//======================로그인 GET================================
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	//===================== 로그인 POST ==============================
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User dbUser = userService.selectOne(user.getId());
		if(dbUser.getPw() == user.getPw()) {
			session.setAttribute("login", dbUser);
			mav.setViewName("redirect:/main/main.do");
			return mav;
		}
		return mav;
	}
	
	//==================== 로그아웃 =================================
	@RequestMapping(value = "logout.do", method = RequestMethod.POST)
	public ModelAndView logout(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.invalidate(); //session 제거.
		mav.setViewName("main/main.do");
		return mav;
	}
}
