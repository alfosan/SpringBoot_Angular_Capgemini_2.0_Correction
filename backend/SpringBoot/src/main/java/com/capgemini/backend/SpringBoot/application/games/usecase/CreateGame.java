package com.capgemini.backend.SpringBoot.application.games.usecase;

import com.capgemini.backend.SpringBoot.application.exceptions.games.DuplicateGameTitleException;
import com.capgemini.backend.SpringBoot.domain.games.model.Game;
import com.capgemini.backend.SpringBoot.domain.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateGame {

    @Autowired
    private GameRepository gameRepository;

    public Game execute(Game game) {
        if (gameRepository.existsByTitulo(game.getTitulo())) {
            throw new DuplicateGameTitleException("Ya existe un juego con el título: " + game.getTitulo());
        }
        return gameRepository.save(game);
    }
}