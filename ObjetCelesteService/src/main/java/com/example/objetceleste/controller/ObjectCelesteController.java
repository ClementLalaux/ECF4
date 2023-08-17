package com.example.objetceleste.controller;

import com.example.objetceleste.entity.ObjetCeleste;
import com.example.objetceleste.service.ObjectCelesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/objectceleste")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ObjectCelesteController {

    @Autowired
    private ObjectCelesteService objectCelesteService;

    @PostMapping("")
    public ResponseEntity<ObjetCeleste> post(@RequestParam String nom, @RequestParam Double taille, @RequestParam Double distance) {
        ObjetCeleste objetCeleste = objectCelesteService.createObjectCeleste(nom, taille,distance);
        return ResponseEntity.ok(objetCeleste);
    }

    @GetMapping("{id}")
    public ResponseEntity<ObjetCeleste> get(@PathVariable Long id) {
        ObjetCeleste objetCeleste = objectCelesteService.getObjetCelesteById(id);
        return ResponseEntity.ok(objetCeleste);
    }
}
