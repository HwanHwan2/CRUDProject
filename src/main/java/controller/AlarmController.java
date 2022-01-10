package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Alarm;
import logic.AlarmService;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

	@Autowired
	private AlarmService alarmService;
	
	//================= 알림 저장 =======================
	@RequestMapping(value="/saveAlarm.do", method = RequestMethod.POST)
	@ResponseBody
	public String saveAlarm(@RequestBody Alarm alarm) {

		// 알람이 1개도 없으면 no = 1
		// 1개라도 있으면 maxno ++
		int alarmCount = alarmService.alarmCount();
		if(alarmCount == 0) {
			alarm.setNo(1);
		} else {
			int maxnum = alarmService.alarmMaxnum(); //알람 maxnum 가져와서
			alarm.setNo(++maxnum); // +1 해준다.
		}
		System.out.println(alarm);
		alarmService.saveAlarm(alarm);
		return null;
	}
}
