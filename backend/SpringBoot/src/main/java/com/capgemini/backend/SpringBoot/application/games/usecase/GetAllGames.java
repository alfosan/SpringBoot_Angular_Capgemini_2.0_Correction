package com.capgemini.backend.SpringBoot.application.games.usecase;

import com.capgemini.backend.SpringBoot.domain.games.model.Game;
import com.capgemini.backend.SpringBoot.domain.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllGames {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> execute() {
        return gameRepository.findAll();
    }
}