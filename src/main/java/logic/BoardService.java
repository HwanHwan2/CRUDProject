package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDao;
import dto.Board;
import dto.Comment;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	//============ 게시물 리스트 ===============
	public List<Board> boardList(Integer pageNum, int limit, String searchtype, String searchcontent) {
		return boardDao.boardList(pageNum,limit, searchtype, searchcontent);
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

	//=========== 게시물 수 ===================
	public int listcount(String searchtype, String searchcontent) {
		return boardDao.listcount(searchtype,searchcontent);
	}
	
	//========== 댓글 가져오기 ==================
	public List<Comment> comment(int no, int type) {
		return boardDao.comment(no,type);
	}

	public int commentCount(int no, int type) {
		return boardDao.commentCount(no,type);
	}

	//============ 게시물 삭제 ==================
	public void delete(int type, int no) {
		boardDao.delete(type,no);
	}

	//============ 게시물 수정 ====================
	public void update(Board board) {		
		boardDao.update(board);
	}

	//============ 댓글 삭제 ====================
	public int commentDelete(Comment comment) {
		return boardDao.commentDelete(comment);
	}

	//=========== 댓글 등록 ====================
	public void commentWrite(Comment comment) {
		boardDao.commentWrite(comment);
	}
	//=========== 댓글 제일 큰 번호 ==============
	public int commentMaxNo(Comment comment) {
		return boardDao.commentMaxNo(comment);
	}
	
	//=========== 내 게시물 가져오기 ===============
	public List<Board> myBoardList(String nickname) {
		return boardDao.myBoardList(nickname);
	}

	//=========== 조회수 증가 =====================
	public void increaseHits(int no, int type) {
		boardDao.increaseHits(no,type);
	}

	//============ 다운로드 파일 링크 ==============
	public List<Board> downloadFileName(int type, int no) {
		return boardDao.downloadFileName(type,no);
	}
	
}
