package com.example.demo.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ScheduleDAO;
import com.example.demo.entity.ScheduleDetails;

@Service
@Transactional
public class ScheduleServiceImplementation implements ScheduleService {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	ScheduleDAO scheduleDAO;

	@Override
	public void save(ScheduleDetails scheduleDetails) {
		scheduleDAO.save(scheduleDetails);
		
	}

	@Override
	public List<ScheduleDetails> findAll() {
		// TODO Auto-generated method stub
		return scheduleDAO.findAll();
	}

	@Override
	public String timePrinter() {
		
		return "the time now is - " + dateFormat.format(new Date());
		
	}

	@Override
	public List<Time> findAllTime() {
		return scheduleDAO.findAllTime();
	}

	@Override
	public void setStatusDone(Time time) {
		
		scheduleDAO.setStatusDone(time);
		
	}

	@Override
	public void seStatusInactive(Time time) {
		
		scheduleDAO.setStatusInactive(time);
		
	}

	@Override
	public void setStatusActive(Time time) {
		
		scheduleDAO.setStatusActive(time);
		
	}

}
