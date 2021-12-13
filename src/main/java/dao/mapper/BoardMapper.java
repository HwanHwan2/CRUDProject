package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Board;
import dto.Comment;

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

	@Select({"<script>",
			 "select * from comment ",
			 "where no = #{no} and type = #{type} ",
			 "order by c_date desc",
			 "</script>"})
	List<Comment> comment(Map<String, Object> param);

	@Select({"<script>",
			 "select count(*) from comment ",
			 "where no = #{no} and type = #{type} ",
			 "</script>"})
	int commentCount(Map<String, Object> param);

	@Delete({"<script>",
			 "delete from board ",
			 "where type = #{type} and no = #{no}",
			 "</script>"})
	void delete(Map<String, Object> param);

	@Update({"<script>",
			 "update board set ",
			 "title = #{title}, content = #{content}, files = #{files} ",
			 "where type = #{type} and no = #{no}",
			 "</script>"})
	void update(Map<String, Object> param);

	@Delete({"<script>",
			 "delete from comment ",
			 "where type = #{type} and no = #{no} and c_no = #{c_no}",
			 "</script>"})
	int commentDelete(Map<String, Object> param);

	@Select({"<script>",
			 "select Max(c_no) from comment ",
			 "where type = #{type} and no = #{no}",
			 "</script>"})
	int commentMaxNo(Map<String, Object> param);

	@Insert({"<script>",
			 "insert into comment ",
			 "values (#{no},#{c_no},#{type},#{nickname},#{content},NOW())",
			 "</script>"})
	void commentWrite(Map<String, Object> param);
}
