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

import dto.Board;
import dto.User;
import logic.BoardService;
import logic.CipherUtil;
import logic.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private CipherUtil cipherUtil;
	
	@GetMapping("/myinfo")
	public ModelAndView test(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = (User)session.getAttribute("login");
		List<Board> myBoardList = boardService.myBoardList(user.getNickname());
		mav.addObject("myBoardList", myBoardList);
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
			result.reject("login.input");
			return mav;
		}
		//로그인
		try {
			User dbUser = userService.selectOne(user.getId());
			user.setPw(cipherUtil.makehash(user.getPw()).substring(0, 20)); //비밀번호 암호화

			if(dbUser.getPw().equals(user.getPw())) {
				session.setAttribute("login", dbUser);
				mav.setViewName("redirect:/user/myinfo.do");
				return mav;
			} else { //아이디 비밀번호 틀린 경우
				System.out.println("아이디 비밀번호 틀림.");
				result.reject("login.pw");
				return mav;
			}
		} catch(Exception e) {
			result.reject("login.id");
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
	
	//===================== ID 찾기 ================================
	@RequestMapping(value = "idsearch.do", method = RequestMethod.GET)
	public ModelAndView idsearch(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value = "idsearch.do", method = RequestMethod.POST)
	public ModelAndView idsearch(@ModelAttribute("user") User user, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(user.getName().equals("") || user.getBirth() == null) { //Date 타입은 == null로
			result.reject("search.id.input");
			return mav;
		}
		
		User dbUser = userService.getId(user.getName(),user.getBirth());
		
		if(dbUser == null) {
			result.reject("search.id.IncorrectNameBirth");
			return mav;
		} else {
			mav.addObject("id", dbUser.getId());
			mav.setViewName("user/idresult");
			return mav;
		}
	}
	
	@GetMapping("/idresult")
	public ModelAndView idresult() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	//======================== PW 찾기 ===============================
	@RequestMapping(value = "/pwsearch", method = RequestMethod.GET)
	public ModelAndView pwsearch(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	@RequestMapping(value = "/pwsearch", method = RequestMethod.POST)
	public ModelAndView pwsearch(@ModelAttribute("user") User user, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(user.getId().equals("") || user.getName().equals("") || user.getEmail().equals("")) {
			result.reject("search.pw.input");
			return mav;
		}
		
		User dbUser = userService.getPw(user.getId(),user.getName(), user.getEmail());
		
		if(dbUser == null) { //일치하지 않는 경우
			result.reject("search.pw.IncorrectIdNameEmail");
			return mav;
		} else { //일치하는 경우
			mav.addObject("id",dbUser.getId()); //히든값으로 아이디 넘겨줌.
			mav.setViewName("user/pwchange");
			return mav;
		}
	}
	
	@RequestMapping(value = "/pwchange", method = RequestMethod.GET)
	public ModelAndView pwchange(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value = "/pwchange", method = RequestMethod.POST)
	public ModelAndView pwchange(@ModelAttribute("user") User user, BindingResult result) throws NoSuchAlgorithmException {
		ModelAndView mav = new ModelAndView();
		if(user.getPw().equals("")) {
			result.reject("change.pw.input");
			return mav;
		}
		user.setPw(cipherUtil.makehash(user.getPw()).substring(0, 20)); //비밀번호 암호화
		userService.pwChange(user);
		
		mav.setViewName("alert");
		mav.addObject("url", "login.do");
		mav.addObject("msg", "비밀번호 변경에 성공했습니다.");
		return mav;
	}
	
}