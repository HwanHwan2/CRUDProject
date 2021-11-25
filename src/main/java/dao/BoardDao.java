package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import dto.Board;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<>();
	
	//==================== 게시물 리스트 ===========================
	public List<Board> boardList() {
		return template.getMapper(BoardMapper.class).boardList();
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
	
	
}
