package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dto.Board;
import logic.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	//===================== 게시물 리스트 ===========================
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView boardList() {
		ModelAndView mav = new ModelAndView();
		List<Board> list = boardService.boardList();
		mav.addObject("list",list);
		mav.addObject("today", new SimpleDateFormat("yyyyMMdd").format(new Date())); //시간 계산 
		return mav;
	}
	
	//==================== 게시물 작성 ==============================
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView write(@ModelAttribute("board") Board board, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView write(@ModelAttribute("board") Board board, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(board.getTitle().equals("") || board.getContent().equals("")) {
			result.reject("board.write");
			return mav;
		}
		
		try {
			int maxNo = boardService.getNo(); // 게시글 번호 가져오기.
			++maxNo;
			board.setNo(maxNo);			
			boardService.write(board);
			mav.setViewName("alert");
			mav.addObject("msg","게시물을 작성했습니다.");
			mav.addObject("url","info.do?type=" + board.getType() + "&no=" + board.getNo());
			return mav;
		} catch(Exception e) {
			e.printStackTrace();
			mav.setViewName("alert");
			mav.addObject("msg","등록 실패");
			mav.addObject("url","list.do");
			return mav;
		}
	}
	
	//======================= 게시물 상세보기 =========================
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(int no, int type) {
		ModelAndView mav = new ModelAndView();
		Board info = boardService.info(no,type);
		mav.addObject("info", info);
		return mav;
	}
}
