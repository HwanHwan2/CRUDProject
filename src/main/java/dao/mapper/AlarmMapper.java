package dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface AlarmMapper {

	@Select({"<script>",
			 "select max(no) from alarm",
			 "</script>"})
	int alarmMaxnum();

	
	@Select({"<script>",
			 "select count(*) from alarm",
			 "</script>"})
	int alarmCount();

	@Insert({"<script>",
			 "insert into alarm ",
			 "values(#{no}, #{sentNickname}, #{recvNickname}, #{title}, #{content}, #{href}, 0, #{date})",
			 "</script>"})
	void saveAlarm(Map<String, Object> param);
}
