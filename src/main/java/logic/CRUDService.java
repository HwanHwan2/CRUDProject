package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import dto.User;

@Service
public class CRUDService {
	
	@Autowired
	private UserDao userDao;
	
	//==============================유저 리스트===================================
	public List<User> userlist(){
		return userDao.userlist();
	}
}
