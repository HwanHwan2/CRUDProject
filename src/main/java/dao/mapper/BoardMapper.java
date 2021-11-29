package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import dto.Board;

public interface BoardMapper {

	@Select({"<script>",
		 	 "select * from board ",
		 	 "<if test='searchtype != null and searchcontent != null'> ",
		 		"where ${searchtype} like '%${searchcontent}%'",
		 	 "</if>",
		 	 "<if test='limit != null'>",
		 	 	"order by no desc limit #{startrow}, #{limit}",
		 	 "</if>", 
		 	 "</script>" })
	List<Board> boardList(Map<String, Object> param);

	@Select({"<script>",
			 "select * from board ",
			 "where no = #{no} and type = #{type} ",
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

	@Select({ "<script>",
			  "select count(*) from board ",
			  "<if test='searchtype != null and searchcontent != null'> ",
			  		"where ${searchtype} like '%${searchcontent}%' ",
			  "</if>",
			  "</script>" })
	int listcount(Map<String, Object> param);
}
