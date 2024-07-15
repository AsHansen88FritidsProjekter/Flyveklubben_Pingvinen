package com.example.backend.Repository;

import com.example.backend.Model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepo extends JpaRepository<EventModel, Long> {

    @Query("SELECT e FROM EventModel e WHERE e.start >= :start AND e.end <= :end")
    List<EventModel> findBetween(LocalDateTime start, LocalDateTime end);
}

