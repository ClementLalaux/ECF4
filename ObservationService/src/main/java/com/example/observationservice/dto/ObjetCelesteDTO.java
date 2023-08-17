package com.example.observationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjetCelesteDTO {

    private Long id;
    private String nom;
    private Long taille;
    private Long distance;
}
