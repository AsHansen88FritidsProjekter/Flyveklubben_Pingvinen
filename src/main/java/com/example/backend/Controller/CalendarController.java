package com.example.backend.Controller;

import com.example.backend.Model.EventModel;
import com.example.backend.Repository.EventRepo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

@RequestMapping("/api/calendar")
@RestController
public class CalendarController {

    @Autowired
    EventRepo er;


    @ResponseBody
    String home() {
        return "Welcome!";
    }

    @GetMapping("/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<EventModel> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        return er.findBetween(start, end);
    }

    @PostMapping("/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    EventModel createEvent(@RequestBody EventCreateParams params) {

        EventModel e = new EventModel();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);
        er.save(e);

        return e;
    }

    @PostMapping("/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    EventModel moveEvent(@RequestBody EventMoveParams params) {

        EventModel e = er.findById(params.id).orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
        e.setStart(params.start);
        e.setEnd(params.end);
        er.save(e);

        return e;
    }

    @PostMapping("/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    EventModel setColor(@RequestBody SetColorParams params) {

        EventModel e = er.findById(params.id).orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
        e.setColor(params.color);
        er.save(e);

        return e;
    }

    @PostMapping("/events/delete")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    EventDeleteResponse deleteEvent(@RequestBody EventDeleteParams params) {

        er.deleteById(params.id);

        return new EventDeleteResponse() {{
            message = "Deleted";
        }};
    }

    public static class EventDeleteParams {
        public Long id;
    }

    public static class EventDeleteResponse {
        public String message;
    }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }
}
