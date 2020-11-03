package com.example.demo.dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ScheduleDetails;

@Repository
public interface ScheduleDAO extends JpaRepository<ScheduleDetails, Integer> {
	
	@Query(value = "Select time from ScheduleDetails  where status like 'ACTIVE' ")
	public  List<Time> findAllTime();
	
	@Modifying(flushAutomatically=true)
	@Query(value = "update ScheduleDetails set status = 'DONE' where time = :time ")
	public void setStatusDone(@Param("time")Time time);
	
	@Modifying(flushAutomatically=true)
	@Query(value = "update ScheduleDetails set status = 'INACTIVE' where time = :time ")
	public void setStatusInactive(@Param("time")Time time);
	
	@Modifying(flushAutomatically=true)
	@Query(value = "update ScheduleDetails set status = 'ACTIVE' where time = :time ")
	public void setStatusActive(@Param("time")Time time);

}
