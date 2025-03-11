package com.capgemini.backend.SpringBoot.infraestructure.games.mapper;

import com.capgemini.backend.SpringBoot.domain.games.model.dto.GameDTO;
import com.capgemini.backend.SpringBoot.domain.games.model.Game;

public class GameMapper {

    public static GameDTO toDTO(Game game) {
        return new GameDTO(game.getId(), game.getTitulo());
    }

    public static Game toEntity(GameDTO gameDTO) {
        return new Game(gameDTO.getId(), gameDTO.getTitulo());
    }
}