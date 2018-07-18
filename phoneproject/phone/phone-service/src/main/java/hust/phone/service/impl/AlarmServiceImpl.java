package hust.phone.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.phone.mapper.mapper.AlarmMapper;
import hust.phone.mapper.pojo.Alarm;
import hust.phone.service.interFace.AlarmService;


@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    public AlarmMapper alarmMapper;

    @Override
    public List<Alarm> getAllAlarm() {

        List<Alarm> alarmList = alarmMapper.selectALLAlarm();
        return alarmList;
    }

    @Override
    public Alarm selectAlarmById(String id) {
        if (StringUtils.isNotBlank(id)) {
            return alarmMapper.selectInfoById(id);
        }
        return null;
    }

	@Override
	public void updateAlarmStatus(String alarmid) {
		alarmMapper.updateByAlarmId(alarmid);
		
	}

}
