package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.Board;
import dto.Comment;
import logic.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//파일 저장 경로
	private String PATH = "C:\\Users\\wlghk\\CRUD\\files";
	
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
	public ModelAndView write(@ModelAttribute("board") Board board, BindingResult result, 
			HttpSession session, @RequestParam("files") MultipartFile files) throws IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();

		//files.getOriginalName : 파일 이름+확장자명
		if(!files.getOriginalFilename().isEmpty()) { //등록한 파일이 있으면
			File dir = new File(PATH); //폴더 없으면 생성
	        if (!dir.isDirectory()) {
	            dir.mkdirs();
	        }
	        String uuid = UUID.randomUUID().toString(); //파일 중복을 위한 UUID 생성
	        String realFileName = files.getOriginalFilename();
	        String saveFileName = uuid + "." + realFileName;
	        long fileSize = files.getSize();
	        board.setRealFileName(realFileName);
	        board.setSaveFileName(saveFileName);
	        board.setFileSize(fileSize);
			files.transferTo(new File(PATH,saveFileName));
		}
		
		
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
	public ModelAndView info(@ModelAttribute("board") Board board, int no, int type, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		boardService.increaseHits(no,type); // 조회수
		Board info = boardService.info(no,type);
		List<Comment> comment1 = boardService.comment(no,type);
		int commentCount = boardService.commentCount(no,type);
		mav.addObject("info", info);
		mav.addObject("comment",comment1);
		mav.addObject("commentCount",commentCount);
		return mav;
	}
	
	//====================== 파일 다운로드 ===========================
	@RequestMapping("/download")
	@ResponseBody
	public void download(HttpServletResponse response, @RequestParam("type") int type, @RequestParam("no") int no) throws IOException {
		List<Board> files = boardService.downloadFileName(type,no);
		
		String realFileName = new String(files.get(0).getRealFileName().getBytes("UTF-8"), "ISO-8859-1"); //한글 파일은 깨지기 때문에 인코딩
		String saveFileName = files.get(0).getSaveFileName();
		long fileSize = files.get(0).getFileSize();
		
		File file = new File(PATH + "\\" + saveFileName);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + realFileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", "application/octer-stream");
        response.setHeader("Content-Length", "" + fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        FileInputStream fis = new FileInputStream(file);
        ServletOutputStream sos = response.getOutputStream();
        byte b [] = new byte[4096];
        int data = 0;
        while((data=(fis.read(b, 0, b.length))) != -1){    
            sos.write(b, 0, data);        
        }
        sos.flush();
        sos.close();
        fis.close();
	}
	
	//===================== 게시물 삭제 ===============================
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("board") Board board, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		boardService.delete(board.getType(),board.getNo());
		mav.setViewName("alert");
		mav.addObject("url","list.do");
		mav.addObject("msg","게시물을 삭제했습니다.");
		return mav;
	}
	//===================== 게시물 수정 ===============================
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@ModelAttribute("board") Board board) {
		ModelAndView mav = new ModelAndView();
		Board info = boardService.info(board.getNo(), board.getType()); //전 게시물 다시 가져오기.
		mav.addObject("info",info);
		return mav;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update2(@ModelAttribute("board") Board board) {
		ModelAndView mav = new ModelAndView();
	
		try {
			boardService.update(board);
			mav.setViewName("alert");
			mav.addObject("url","info.do?type=" + board.getType() + "&no=" + board.getNo());
			mav.addObject("msg","수정에 성공했습니다.");
			return mav;
		} catch (Exception e) {
			Board info = boardService.info(board.getNo(), board.getType()); //전 게시물 다시 가져오기.
			mav.setViewName("alert");
			mav.addObject("url","update.do?type=" + board.getType() + "&no=" + board.getNo());
			mav.addObject("msg","수정에 실패했습니다." + e);
			mav.addObject("info",info);
		}
		return mav;
	}
	//================= 댓글 카운트 ==========================
	@GetMapping(value = "/commentCount")
	@ResponseBody
	public int commentCount(@RequestParam("type") int type, @RequestParam("no") int no) {
		return boardService.commentCount(no, type);
	}
	//================= 댓글 작성 ============================
	@PostMapping("/commentWrite")
	public ModelAndView commentWrite(Comment comment) {
		ModelAndView mav = new ModelAndView();
		
		int commentCount = boardService.commentCount(comment.getNo(), comment.getType()); //댓글 있는지 없는지
		try {
			//댓글이 없는 경우
			if(commentCount == 0) {
				comment.setC_no(1);
			} else {
				int commentMaxNo = boardService.commentMaxNo(comment);
				commentMaxNo++;
				comment.setC_no(commentMaxNo);
			}
			boardService.commentWrite(comment);
			mav.setViewName("alert");
			mav.addObject("msg","등록 성공");
			mav.addObject("url","info.do?type="+comment.getType()+"&no="+comment.getNo());
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("alert");
			mav.addObject("msg","등록 실패");
			mav.addObject("url","info.do?type="+comment.getType()+"&no="+comment.getNo());
			return mav;
		}
		
	}
	//================= 댓글 삭제 ============================
	//=========== 0 -> 실패   1 -> 성공 ======================
	@PostMapping(value = "/commentDelete")
	@ResponseBody
	public int commentDelete(@RequestBody Comment comment) {
		return boardService.commentDelete(comment);
	}
}
