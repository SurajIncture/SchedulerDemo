package com.example.demo.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ScheduleDetails;
import com.example.demo.service.ScheduleService;

@RestController
@RequestMapping("/api")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	public ScheduleController(ScheduleService theScheduleService){
		scheduleService = theScheduleService;
	}
	
	public List<Time> x;
	
	@GetMapping("/trial")
	public String trial(){
		return "trial";
	}
	
	@GetMapping("/allSchedules")
	public List<ScheduleDetails> allSchedules(){
		return scheduleService.findAll();
	}
	
	@GetMapping("/time")
	public String timeprt(){
		return scheduleService.timePrinter();
	}
	
	@GetMapping("/allActiveTime")
	public List<Time> activeTime(){
		return x;
	}
	
	@PutMapping("/setInactive/{time}")
	public String setInactive(@PathVariable Time time){
		try{
			scheduleService.seStatusInactive(time);
			return "SUCCESS";
		}
		catch(Exception e){
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	@PutMapping("/setActive/{time}")
	public String setActive(@PathVariable Time time){
		try{
			scheduleService.setStatusActive(time);
			return "SUCCESS";
		}
		catch(Exception e){
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	
	@PostMapping("/newSchedule")
	public String newSchedule(@RequestBody ScheduleDetails theScheduleDetails){
		try{
			theScheduleDetails.setId(0);
			scheduleService.save(theScheduleDetails);
			return "SUCCESS";
		}
		catch(Exception e){
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	@Scheduled(cron = "10 * * * * ?")
	public void findTime(){
			x=scheduleService.findAllTime();
			Date now = new Date();
			Time curr_time = new Time(now.getTime());
			for(Time time:x){
				if(time.compareTo(curr_time) <= 0){
					
					scheduleService.setStatusDone(time);
					
				}
			}
			
	}
	
	@Scheduled(cron = "0/10 * * * * ?")
	public void insertTime(){
		try{
			Date today = new Date();
			ScheduleDetails obj = new ScheduleDetails();
			obj.setId(0);
			obj.setStatus("ACTIVE");
			obj.setTime(new Time(today.getTime()));
			scheduleService.save(obj);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
