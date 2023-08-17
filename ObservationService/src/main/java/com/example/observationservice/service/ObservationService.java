package com.example.observationservice.service;

import com.example.observationservice.dto.ObjetCelesteDTO;
import com.example.observationservice.dto.ObservationResponseDTO;
import com.example.observationservice.dto.UserDTO;
import com.example.observationservice.entity.Observation;
import com.example.observationservice.repository.ObservationRepository;
import com.example.observationservice.tool.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    public Observation createObservation(Long userId, Long objetCelesteId, String commentaire){
        Observation observation = Observation.builder().userId(userId).objetCelesteId(objetCelesteId).commentaire(commentaire).build();
        observationRepository.save(observation);
        return observation;
    }

    public List<Observation> getAllObservations(){
        return (List<Observation>) observationRepository.findAll();
    }

    public Observation getById(Long id){
        if(observationRepository.findById(id).isPresent()){
            Observation observation = observationRepository.findById(id).get();
            return observation;
        }
        throw new RuntimeException("Not found");
    }

    public ObservationResponseDTO getObservationByUserId(Long userId){
        RestClient<UserDTO,String> restClient = new RestClient<>();
        ObservationResponseDTO observationUserResponseDTO = ObservationResponseDTO
                .builder()
                .observations(observationRepository.findAllByUserId(userId))
                .userDTO(restClient.get("user/"+userId, UserDTO.class))
                .build();
        return observationUserResponseDTO;
    }

    public ObservationResponseDTO getObservationByObjetCelesteId(Long objetCelesteId){
        RestClient<ObjetCelesteDTO,String> restClient = new RestClient<>();
        ObservationResponseDTO observationObjetResponseDTO = ObservationResponseDTO
                .builder()
                .observations(observationRepository.findAllByObjetCelesteId(objetCelesteId))
                .objetCelesteDTO(restClient.get("objetceleste/"+objetCelesteId, ObjetCelesteDTO.class))
                .build();
        return observationObjetResponseDTO;
    }

    public ObservationResponseDTO getObservationByObjetCelesteIdAndUserId(Long userId,Long objetCelesteId){
        RestClient<UserDTO,String> restClientUser = new RestClient<>();
        RestClient<ObjetCelesteDTO,String> restClientObjet = new RestClient<>();
        ObservationResponseDTO observationObjetAndUserResponseDTO = ObservationResponseDTO
                .builder()
                .observations(observationRepository.findAllByUserIdAndObjetCelesteId(userId,objetCelesteId))
                .userDTO(restClientUser.get("user/"+userId, UserDTO.class))
                .objetCelesteDTO(restClientObjet.get("objetceleste/"+objetCelesteId, ObjetCelesteDTO.class))
                .build();
        return observationObjetAndUserResponseDTO;
    }

}
