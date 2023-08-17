package com.example.objetceleste.repository;

import com.example.objetceleste.entity.ObjetCeleste;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetCelesteRepository extends CrudRepository<ObjetCeleste,Long> {
}
