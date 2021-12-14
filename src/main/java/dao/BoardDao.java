package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import dto.Board;
import dto.Comment;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<>();
	
	//==================== 게시물 리스트 ===========================
	public List<Board> boardList(Integer pageNum, int limit, String searchtype, String searchcontent) {
		param.clear();
		if (searchtype != null && searchcontent != null) { // 검색 요청
			param.put("searchtype", searchtype);
			param.put("searchcontent", searchcontent);
		}
		param.put("startrow", (pageNum - 1) * limit);
		param.put("pageNum",pageNum);
		param.put("limit", limit);
		return template.getMapper(BoardMapper.class).boardList(param);
	}

	//==================== 게시물 상세보기 ========================
	public Board info(int no, int type) {
		param.clear();
		param.put("no",no);
		param.put("type", type);
		return template.getMapper(BoardMapper.class).info(param);
	}
	//==================== 게시글 쓰기 =====================
	public void write(Board board) {
		param.clear();
		param.put("no", board.getNo());
		param.put("title", board.getTitle());
		param.put("nickname", board.getNickname());
		param.put("content", board.getContent());
		param.put("b_date", board.getB_date());
		param.put("files", board.getFiles());
		param.put("type", board.getType());
		template.getMapper(BoardMapper.class).write(param);
		
	}
	//==================== 게시글 번호 가져오기 =====================
	public int getNo() {
		return template.getMapper(BoardMapper.class).getNo();
	}

	//==================== 게시물 수 ==========================
	public int listcount(String searchtype, String searchcontent) {
		param.clear();
		param.put("searchcontent", searchcontent);
		param.put("searchtype", searchtype);
		return template.getMapper(BoardMapper.class).listcount(param);
	}

	//===================== 댓글 가져오기 =======================
	public List<Comment> comment(int no, int type) {
		param.clear();
		param.put("no", no);
		param.put("type", type);
		return template.getMapper(BoardMapper.class).comment(param);
	}

	public int commentCount(int no, int type) {
		param.clear();
		param.put("no", no);
		param.put("type", type);
		return template.getMapper(BoardMapper.class).commentCount(param);
	}

	//================== 게시물 삭제 =================================
	public void delete(int type, int no) {
		param.clear();
		param.put("type", type);
		param.put("no", no);
		template.getMapper(BoardMapper.class).delete(param);
	}

	//================= 게시물 수정 ============================
	public void update(Board board) {
		param.clear();
		param.put("no", board.getNo());
		param.put("type", board.getType());
		param.put("title", board.getTitle());
		param.put("content", board.getContent());
		param.put("files", board.getFiles());
		template.getMapper(BoardMapper.class).update(param);
	}

	//================= 댓글 삭제 =============================
	public int commentDelete(Comment comment) {
		param.clear();
		param.put("type", comment.getType());
		param.put("no", comment.getNo());
		param.put("c_no", comment.getC_no());
		return template.getMapper(BoardMapper.class).commentDelete(param);
	}

	//=============== 댓글 등록 ===============================
	public void commentWrite(Comment comment) {
		param.clear();
		param.put("type", comment.getType());
		param.put("no", comment.getNo());
		param.put("c_no", comment.getC_no());
		param.put("content", comment.getContent());
		param.put("nickname", comment.getNickname());
		template.getMapper(BoardMapper.class).commentWrite(param);
	}
	
	//=============== 댓글 번호 가져오기 ============================
	public int commentMaxNo(Comment comment) {
		param.clear();
		param.put("type", comment.getType());
		param.put("no", comment.getNo());
		return template.getMapper(BoardMapper.class).commentMaxNo(param);
	}

	//=============== 내 게시물 가져오기 =============================
	public List<Board> myBoardList(String nickname) {
		param.clear();
		param.put("nickname", nickname);
		return template.getMapper(BoardMapper.class).myBoardList(param);
	}

	//=============== 조회수 증rk ====================
	public void increaseHits(int no, int type) {
		param.clear();
		param.put("no", no);
		param.put("type", type);
		template.getMapper(BoardMapper.class).increaseHits(param);
	}
	
	
}
