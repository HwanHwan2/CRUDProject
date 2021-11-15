package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import dto.User;

public interface UserMapper {

	//==============================유저 리스트===================================
	@Select({"<script>",
			 "select id, pw from user",
			 "</script>" })
	List<User> userlist();
	//=============================회원가입========================================
	@Insert({"<script>",
			 "insert into user values(#{id}, #{pw}, #{name}, #{nickname}, #{birth}, #{email})",
			 "</script>" })
	void entry(Map<String, Object> param);

	//==============================회원가입 아이디,닉네임 체크==================================
	@Select({"<script>",
			 "select count(*) from user ",
			 "where id = #{id}",
			 "</script>"})
	int entryIdCheck(Map<String, Object> param);
	@Select({"<script>",
			 "select count(*) from user ",
			 "where nickname = #{nickname}",
			 "</script>"})
	int entryNicknameCheck(Map<String, Object> param);
	
	//==============================로그인==========================================
	@Select({"<script>",
			 "select * from user ",
			 "where id = #{id}",
			 "</script>"})
	User selectOne(Map<String, Object> param);
	
}
