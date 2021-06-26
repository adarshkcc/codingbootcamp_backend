package com.CodingBootCamp.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingBootCamp.model.ScheduleMeeting;


public interface MeetingDAO extends JpaRepository<ScheduleMeeting, Long> {
   // @Query("Select * from meeting  where date >= :d")
    //List<Meeting> findByDate(@Param("date")LocalDate d);
}
