package com.example.observationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long objetCelesteId;
    private String commentaire;
    private LocalDate date;

    public Observation(Long userId, Long objetCelesteId, String commentaire, LocalDate date) {
        this.userId = userId;
        this.objetCelesteId = objetCelesteId;
        this.commentaire = commentaire;
        this.date = date;
    }
}
