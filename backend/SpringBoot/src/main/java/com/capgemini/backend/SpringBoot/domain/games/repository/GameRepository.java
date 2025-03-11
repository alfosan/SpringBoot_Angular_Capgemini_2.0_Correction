package com.capgemini.backend.SpringBoot.domain.games.repository;

import com.capgemini.backend.SpringBoot.domain.games.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    boolean existsByTitulo(String titulo);
}