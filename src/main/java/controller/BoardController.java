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
	@RequestMapping("list")
	   public ModelAndView list(Integer pageNum, String searchtype, String searchcontent) {
	      ModelAndView mav = new ModelAndView();
	      if(pageNum == null || pageNum.toString().equals("")) {
	         pageNum=1;
	      }
	      if(searchtype == null || searchcontent == null || searchtype.trim().equals("") || searchcontent.trim().equals("")) {
	         searchtype = null;
	         searchcontent = null;
	      }
	      int limit = 10;   //한 페이지에 보여질 게시물의 건수
	      //검색 조건에 맞도록 등록된 게시물 건수
	      int listcount = boardService.listcount(searchtype,searchcontent); //등록 게시물
	      //boardlist : 화면에 출력할 게시물 목록 (최대 10건)
	      List<Board> list = boardService.boardList(pageNum,limit,searchtype,searchcontent);
	      
	      //최대 필요한페이지 수
	      int maxpage = (int)((double)listcount/limit + 0.95);
	      //화면에 표시 할 페이지의 시작 번호
	      int startpage = (int)((pageNum/10.0 + 0.9) - 1) * 10 + 1;
	      int endpage = startpage + 9;
	      if(endpage > maxpage)
	         endpage = maxpage;
	      int boardno = listcount - (pageNum - 1) * limit +1;   //화면에 표시될 게시물 번호. 의미 없음x   
	      mav.addObject("pageNum",pageNum);
	      mav.addObject("boardno",boardno);
	      mav.addObject("maxpage",maxpage);
	      mav.addObject("startpage",startpage);
	      mav.addObject("endpage",endpage);
	      mav.addObject("listcount",listcount);
	      mav.addObject("list",list);
	      mav.addObject("today", new SimpleDateFormat("yyyyMMdd").format(new Date()));
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
	public ModelAndView info(int no, int type, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Board info = boardService.info(no,type);
		mav.addObject("info", info);
		return mav;
	}
}
