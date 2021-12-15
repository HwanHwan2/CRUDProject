package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	
	//========================= ID 찾기 ================================
	@Select({"<script>",
			 "select * from user ",
			 "where name = #{name} and birth = #{birth}",
			 "</script>"})
	User getId(Map<String, Object> param);
	
	//======================== PW 찾기 ================================
	@Select({"<script>",
			 "select * from user ",
			 "where id = #{id} and name = #{name} and email = #{email}",
			 "</script>"})
	User getPw(Map<String, Object> param);
	
	//======================= PW 변경 ==============================
	@Update({"<script>",
			 "update user ",
			 "set pw = #{pw} ",
			 "where id = #{id}",
			 "</script>"})
	void pwChange(Map<String, Object> param);
	
	//===================== 회원탈퇴 비밀번호 검증 ========================
	@Select({"<script>",
			 "select count(*) from user ",
			 "where id = #{id} and pw = #{pw}",
			 "</script>"})
	int dropPwCheck(Map<String, Object> param);
	
	//=================== 회원 탈퇴 ===========================
	@Delete({"<script>",
			 "delete from user ",
			 "where id = #{id}",
			 "</script>"})
	void drop(Map<String, Object> param);
	
	
}
