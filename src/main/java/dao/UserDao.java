package dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.UserMapper;
import dto.User;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<>();
	
	
	//==============================유저 리스트===================================
	public List<User> userlist(){
		param.clear();
		return template.getMapper(UserMapper.class).userlist();
	}
	
	//==============================유저 회원가입==================================
	public void entry(User user) {
		param.clear();
		param.put("id", user.getId());
		param.put("pw", user.getPw());
		param.put("name", user.getName());
		param.put("nickname", user.getNickname());
		param.put("birth", user.getBirth());
		param.put("email", user.getEmail());
		template.getMapper(UserMapper.class).entry(param);
	}
	
	//==============================회원가입 아이디, 닉네임 체크===============================
	public int entryIdCheck(String id) {
		param.clear();
		param.put("id", id);
		return template.getMapper(UserMapper.class).entryIdCheck(param);
	}
	public int entryNicknameCheck(String nickname) {
		param.clear();
		param.put("nickname", nickname);
		return template.getMapper(UserMapper.class).entryNicknameCheck(param);
	}
	
	//==============================로그인======================================
	public User selectOne(String id) {
		param.clear();
		param.put("id", id);
		return template.getMapper(UserMapper.class).selectOne(param);
	}
	
	//============================ ID 찾기 ================================
	public User getId(String name, Date birth) {
		param.clear();
		param.put("name", name);
		param.put("birth", birth);
		return template.getMapper(UserMapper.class).getId(param);
	}

	//=========================== PW 찾기 ================================
	public User getPw(String id, String name, String email) {
		param.clear();
		param.put("id", id);
		param.put("name", name);
		param.put("email", email);
		return template.getMapper(UserMapper.class).getPw(param);
	}

	//========================= PW 변경 =================================
	public void pwChange(User user) {
		param.clear();
		param.put("id", user.getId());
		param.put("pw", user.getPw());
		template.getMapper(UserMapper.class).pwChange(param);
	}

	//======================= 회원탈퇴 비밀번호 검증 =========================
	public int PwCheck(String id, String pw) {
		param.clear();
		param.put("id", id);
		param.put("pw", pw);
		return template.getMapper(UserMapper.class).PwCheck(param);
	}

	//======================= 회원 탈퇴 ===========================
	public void drop(String id) {
		param.clear();
		param.put("id", id);
		template.getMapper(UserMapper.class).drop(param);
	}

	public void changePw(String id, String newPw1) {
		param.clear();
		param.put("id", id);
		param.put("pw", newPw1);
		template.getMapper(UserMapper.class).changePw(param);
	}

	
}
