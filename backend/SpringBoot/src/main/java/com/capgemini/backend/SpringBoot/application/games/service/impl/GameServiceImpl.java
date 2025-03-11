package com.capgemini.backend.SpringBoot.application.games.service.impl;

import com.capgemini.backend.SpringBoot.application.games.service.GameService;
import com.capgemini.backend.SpringBoot.application.games.usecase.*;
import com.capgemini.backend.SpringBoot.domain.games.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GetAllGames getAllGames;

    @Autowired
    private GetGameById getGameById;

    @Autowired
    private CreateGame createGame;

    @Autowired
    private UpdateGame updateGame;

    @Autowired
    private DeleteGame deleteGame;

    @Override
    public List<Game> getAllGames() {
        return getAllGames.execute();
    }

    @Override
    public Optional<Game> getGameById(Long id) {
        return getGameById.execute(id);
    }

    @Override
    public Game createGame(Game game) {
        return createGame.execute(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        return updateGame.execute(id, game);
    }

    @Override
    public boolean deleteGame(Long id) {
        return deleteGame.execute(id);
    }
}