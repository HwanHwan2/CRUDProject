package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import dto.Board;

public interface BoardMapper {

	@Select({"<script>",
			 "select * from board",
			 "</script>"})
	List<Board> boardList();

	@Select({"<script>",
			 "select * from board ",
			 "where no = #{no} and type = #{type}",
			 "</script>"})
	Board info(Map<String, Object> param);

	@Select({"<script>",
			 "select MAX(no) from board ",
			 "</script>"})
	int getNo();

	@Insert({"<script>",
			 "insert into board ",
			 "values (#{no},#{title},#{nickname},#{content},NOW(),0,#{files},#{type})",
			 "</script>"})
	void write(Map<String, Object> param);
}
