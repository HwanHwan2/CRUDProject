package dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.AlarmMapper;
import dto.Alarm;

@Repository
public class AlarmDao {
	
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<>();
	
	
	public int alarmMaxnum() {
		return template.getMapper(AlarmMapper.class).alarmMaxnum();
	}

	//=========== 알람 카운트 ============
	public int alarmCount() {
		return template.getMapper(AlarmMapper.class).alarmCount();
	}

	//============ 알람 저장 =============
	public void saveAlarm(Alarm alarm) {
		param.clear();
		param.put("no", alarm.getNo());
		param.put("sentNickname", alarm.getSentNickname());
		param.put("recvNickname", alarm.getRecvNickname());
		param.put("title", alarm.getTitle());
		param.put("content", alarm.getContent());
		param.put("href", alarm.getHref());
		param.put("date", alarm.getDate());
		template.getMapper(AlarmMapper.class).saveAlarm(param);
	}
	
	
}
