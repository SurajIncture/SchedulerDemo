package com.example.demo.service;

import java.sql.Time;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.entity.ScheduleDetails;

@Component
public interface ScheduleService {
	
	public void save(ScheduleDetails scheduleDetails);
	
	public List<ScheduleDetails> findAll();
	
	public List<Time>findAllTime();
	
	public void setStatusDone(Time time);
	
	public void seStatusInactive(Time time);
	
	public void setStatusActive(Time time);
	
	@Scheduled(fixedDelay = 5000)
	public String timePrinter();

}
