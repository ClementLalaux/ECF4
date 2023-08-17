package com.example.observationservice.dto;

import com.example.observationservice.entity.Observation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ObservationResponseDTO {
    private List<Observation> observations;
    private UserDTO userDTO;
    private ObjetCelesteDTO objetCelesteDTO;
}
