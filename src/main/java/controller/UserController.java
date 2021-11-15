package controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.User;
import logic.CRUDService;
import logic.CipherUtil;
import logic.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private CRUDService service;
	@Autowired
	private UserService userService;
	@Autowired
	private CipherUtil cipherUtil;
	
	@GetMapping("/test")
	public ModelAndView test(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		List<User> userlist = service.userlist();
		mav.addObject("userlist", userlist);
		return mav;
	}
	
	/*===================== 회원가입 POST ============================
		BindingResult는 반드시 @ModelAttribute 뒤에 추가해야함.
	*/
	@PostMapping("/entry")
	public ModelAndView entry(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		
		try {
			user.setPw(cipherUtil.makehash(user.getPw()).substring(0, 20));
			System.out.println(user.getPw());
			userService.entry(user);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		//회원가입 성공 후 alert, url 처리
		mav.setViewName("alert");
		mav.addObject("url", "test.do");
		mav.addObject("msg", "회원가입을 축하합니다.");
		
		return mav;
	}
	
	//======================아이디,닉네임 체크==================================
	//아이디 있으면 1, 없으면 0
	//@ResponseBody - 응답을 클라이언트로 전송
	@PostMapping("entryIdCheck")
	@ResponseBody
	public int entryIdCheck(@RequestParam("id") String id) {
		return userService.entryIdCheck(id);
	}
	
	@PostMapping("entryNicknameCheck")
	@ResponseBody
	public int entryNickname(@RequestParam("nickname") String nickname) {
		return userService.entryNicknameCheck(nickname);
	}
	//======================로그인 GET================================
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	//===================== 로그인 POST ==============================
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") @Valid User user, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		//아이디 비밀번호 입력 안한 경우
		if(user.getId().equals("") || user.getPw().equals("")) {
			result.reject("error.login.input");
			return mav;
		}
		//로그인
		try {
			User dbUser = userService.selectOne(user.getId());
			user.setPw(cipherUtil.makehash(user.getPw()).substring(0, 20)); //비밀번호 암호화
			
			if(dbUser.getPw().equals(user.getPw())) {
				session.setAttribute("login", dbUser);
				mav.setViewName("redirect:/user/test.do");
				return mav;
			} else { //아이디 비밀번호 틀린 경우
				System.out.println("아이디 비밀번호 틀림.");
				result.reject("error.login.pw");
				return mav;
			}
		} catch(Exception e) {
			result.reject("error.login.id");
			return mav;
		}
	}
	
	//==================== 로그아웃 =================================
	@RequestMapping("/logout")
	public ModelAndView logout(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.invalidate(); //session 제거.
		mav.setViewName("redirect:/user/login.do");
		return mav;
	}
	
	//===================== ID PW 찾기 ================================
	@GetMapping("/idsearch")
	public ModelAndView idsearch(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
