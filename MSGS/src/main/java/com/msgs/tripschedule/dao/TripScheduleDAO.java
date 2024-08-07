package com.msgs.tripschedule.dao;

import com.msgs.msgs.entity.schedule.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripScheduleDAO extends JpaRepository<Trip, Integer> {

}

