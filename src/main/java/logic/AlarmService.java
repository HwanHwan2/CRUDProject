package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AlarmDao;
import dto.Alarm;

@Service
public class AlarmService {

	@Autowired
	private AlarmDao alarmDao;
	public int alarmMaxnum() {
		return alarmDao.alarmMaxnum();
	}
	
	//=========== 알람 카운트 ============
	public int alarmCount() {
		return alarmDao.alarmCount();
	}
	
	//=========== 알람 저장 ============
	public void saveAlarm(Alarm alarm) {
		alarmDao.saveAlarm(alarm);
	}

}
