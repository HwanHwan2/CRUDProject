package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDao;
import dto.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	//============ 게시물 리스트 ===============
	public List<Board> boardList() {
		return boardDao.boardList();
	}

	//============ 게시물 상세보기 =============
	public Board info(int no ,int type) {
		return boardDao.info(no, type);
	}

	//============ 게시물 글쓰기 ===============
	public void write(Board board) {
		boardDao.write(board);
	}

	//============ 게시글 번호 가져오기 ===========
	public int getNo() {
		return boardDao.getNo();
	}
	
}
