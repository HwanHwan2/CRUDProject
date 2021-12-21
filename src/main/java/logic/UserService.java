package logic;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import dto.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//========================회원가입==========================
	public void entry(User user) {
		userDao.entry(user);
	}
	
	//========================회원가입 아이디, 닉네임 체크=====================
	public int entryIdCheck(String id) {
		return userDao.entryIdCheck(id);
	}
	public int entryNicknameCheck(String nickname) {
		return userDao.entryNicknameCheck(nickname);
	}
	
	//========================로그인===========================
	public User selectOne(String id) {
		return userDao.selectOne(id);
	}
	
	//====================== ID 찾기 =========================
	public User getId(String name, Date birth) {
		return userDao.getId(name,birth);
	}
	
	//====================== PW 찾기 =========================
	public User getPw(String id, String name, String email) {
		return userDao.getPw(id,name,email);
	}

	//====================== PW 변경 =========================
	public void pwChange(User user) {
		userDao.pwChange(user);
	}

	//===================== 회원 탈퇴 비밀번호 검증 =========================
	public int PwCheck(String id, String pw) {
		return userDao.PwCheck(id,pw);
	}

	
	//==================== 회원 탈퇴 ============================
	public void drop(String id) {
		userDao.drop(id);
	}

	//================== info창 비밀번호 변경 ====================
	public void changePw(String id, String newPw1) {
		userDao.changePw(id, newPw1);
	}


	
}
