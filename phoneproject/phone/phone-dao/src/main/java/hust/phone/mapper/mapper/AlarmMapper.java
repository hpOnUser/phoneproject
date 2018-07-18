package hust.phone.mapper.mapper;

import java.util.List;

import hust.phone.mapper.pojo.Alarm;



public interface AlarmMapper {

	List<Alarm> selectALLAlarm();
	List<Alarm> selectAllAlarmByCreateTimeDesc();
	int alarmCount(Alarm alarm);
    Alarm selectInfoById(String id);
	void updateByAlarmId(String alarmid);
}
