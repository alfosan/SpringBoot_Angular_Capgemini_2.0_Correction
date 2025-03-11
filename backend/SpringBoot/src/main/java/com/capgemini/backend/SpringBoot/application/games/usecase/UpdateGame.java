package com.capgemini.backend.SpringBoot.application.games.usecase;

import com.capgemini.backend.SpringBoot.application.exceptions.games.GameNotFoundException;
import com.capgemini.backend.SpringBoot.application.exceptions.games.DuplicateGameTitleException;
import com.capgemini.backend.SpringBoot.domain.games.model.Game;
import com.capgemini.backend.SpringBoot.domain.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateGame {

    @Autowired
    private GameRepository gameRepository;

    public Game execute(Long id, Game game) {
        return gameRepository.findById(id)
                .map(existingGame -> {
                    if (!existingGame.getTitulo().equals(game.getTitulo()) && gameRepository.existsByTitulo(game.getTitulo())) {
                        throw new DuplicateGameTitleException("Ya existe un juego con el título: " + game.getTitulo());
                    }
                    existingGame.setTitulo(game.getTitulo());
                    return gameRepository.save(existingGame);
                })
                .orElseThrow(() -> new GameNotFoundException("Juego no encontrado con ID: " + id));
    }
}