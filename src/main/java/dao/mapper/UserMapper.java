package dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import dto.User;

public interface UserMapper {

	//==============================유저 리스트===================================
	@Select({"<script>",
			 "select id, pw from user",
			 "</script>" })
	List<User> userlist();
}
