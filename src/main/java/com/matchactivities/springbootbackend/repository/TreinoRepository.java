package com.matchactivities.springbootbackend.repository;

import com.matchactivities.springbootbackend.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
}
