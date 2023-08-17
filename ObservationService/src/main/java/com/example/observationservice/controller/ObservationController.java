package com.example.observationservice.controller;

import com.example.observationservice.dto.ObservationResponseDTO;
import com.example.observationservice.entity.Observation;
import com.example.observationservice.service.ObservationService;
import com.example.observationservice.tool.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observation")
public class ObservationController {

    @Autowired
    ObservationService observationService;

    @GetMapping("")
    public ResponseEntity<List<Observation>> get(){
        List<Observation> observations = observationService.getAllObservations();
        return ResponseEntity.ok(observations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Observation> getById(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            RestClient<String, String> restClient = new RestClient<>();
            if(restClient.testToken(token, String.class)) {
                return ResponseEntity.ok(observationService.getById(id));
            }
            return ResponseEntity.status(401).body(null);
        }catch (Exception ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ObservationResponseDTO> getByUser(@PathVariable Long userId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            RestClient<String, String> restClient = new RestClient<>();
            if(restClient.testToken(token, String.class)) {
                return ResponseEntity.ok(observationService.getObservationByUserId(userId));
            }
            return ResponseEntity.status(401).body(null);
        }catch (Exception ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/objetceleste/{objetCelesteId}")
    public ResponseEntity<ObservationResponseDTO> getByObjetCeleste(@PathVariable Long objetCelesteId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            RestClient<String, String> restClient = new RestClient<>();
            if(restClient.testToken(token, String.class)) {
                return ResponseEntity.ok(observationService.getObservationByObjetCelesteId(objetCelesteId));
            }
            return ResponseEntity.status(401).body(null);
        }catch (Exception ex) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/objetceleste/{objetCelesteId}/user/{userId}")
    public ResponseEntity<ObservationResponseDTO> getByObjetCeleste(@PathVariable Long userId,@PathVariable Long objetCelesteId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        try {
            RestClient<String, String> restClient = new RestClient<>();
            if(restClient.testToken(token, String.class)) {
                return ResponseEntity.ok(observationService.getObservationByObjetCelesteIdAndUserId(userId,objetCelesteId));
            }
            return ResponseEntity.status(401).body(null);
        }catch (Exception ex) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
