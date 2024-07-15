package com.example.backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "events")
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String text;

    @Column(name = "event_start")
    LocalDateTime start;

    @Column(name = "event_end")
    LocalDateTime end;

    String color;

}