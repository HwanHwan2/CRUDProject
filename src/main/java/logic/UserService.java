package logic;

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
	
	//========================로그인===========================
	public User selectOne(String id) {
		return userDao.selectOne(id);
	}
}
