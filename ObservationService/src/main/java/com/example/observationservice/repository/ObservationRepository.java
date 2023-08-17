package com.example.observationservice.repository;

import com.example.observationservice.entity.Observation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObservationRepository extends CrudRepository<Observation, Long> {

    List<Observation> findAllByUserId(Long userId);

    List<Observation> findAllByObjetCelesteId(Long objetCelesteId);

    List<Observation> findAllByUserIdAndObjetCelesteId(Long userId,Long objetCelesteId);

    Optional<Observation> findById(Long id);

}
