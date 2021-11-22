package dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import dto.Board;

public interface BoardMapper {

	@Select({"<script>",
			 "select * from board",
			 "</script>"})
	List<Board> boardList();
}
