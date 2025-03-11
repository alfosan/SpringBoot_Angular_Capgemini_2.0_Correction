package com.capgemini.backend.SpringBoot.application.games.usecase;

import com.capgemini.backend.SpringBoot.domain.games.model.Game;
import com.capgemini.backend.SpringBoot.domain.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetGameById {

    @Autowired
    private GameRepository gameRepository;

    public Optional<Game> execute(Long id) {
        return gameRepository.findById(id);
    }
}