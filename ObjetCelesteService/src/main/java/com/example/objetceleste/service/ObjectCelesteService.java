package com.example.objetceleste.service;

import com.example.objetceleste.entity.ObjetCeleste;
import com.example.objetceleste.repository.ObjetCelesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjectCelesteService {

    @Autowired
    private ObjetCelesteRepository objetCelesteRepository;

    public ObjetCeleste createObjectCeleste(String nom, Double taille, Double distance){
        ObjetCeleste objetCeleste = ObjetCeleste.builder().nom(nom).taille(taille).distance(distance).build();
        objetCelesteRepository.save(objetCeleste);
        return objetCeleste;
    }

    public ObjetCeleste getObjetCelesteById(Long id){
        Optional<ObjetCeleste> objetCeleste = objetCelesteRepository.findById(id);
        if(objetCeleste.isPresent()){
            return objetCeleste.get();
        }
        throw new RuntimeException("Not found");
    }

    public List<ObjetCeleste> getAllObjetCeleste(){
        return (List<ObjetCeleste>) objetCelesteRepository.findAll();
    }

}
