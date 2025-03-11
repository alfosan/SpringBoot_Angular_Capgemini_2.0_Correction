package com.capgemini.backend.SpringBoot.application.games.usecase;

import com.capgemini.backend.SpringBoot.application.exceptions.games.GameNotFoundException;
import com.capgemini.backend.SpringBoot.domain.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteGame {

    @Autowired
    private GameRepository gameRepository;

    public boolean execute(Long id) {
        return gameRepository.findById(id)
                .map(game -> {
                    gameRepository.delete(game);
                    return true;
                })
                .orElseThrow(() -> new GameNotFoundException("Juego no encontrado con ID: " + id));
    }
}