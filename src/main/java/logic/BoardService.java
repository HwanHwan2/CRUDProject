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

	public List<Board> boardList() {
		return boardDao.boardList();
	}
	
	
}
