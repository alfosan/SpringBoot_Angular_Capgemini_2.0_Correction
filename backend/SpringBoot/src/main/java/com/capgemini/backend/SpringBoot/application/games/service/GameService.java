package com.capgemini.backend.SpringBoot.application.games.service;

import com.capgemini.backend.SpringBoot.domain.games.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> getAllGames();
    Optional<Game> getGameById(Long id);
    Game createGame(Game game);
    Game updateGame(Long id, Game game);
    boolean deleteGame(Long id);
}