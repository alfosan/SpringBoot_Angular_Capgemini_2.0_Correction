package com.capgemini.backend.SpringBoot.infraestructure.games.controller;

import com.capgemini.backend.SpringBoot.domain.games.model.dto.GameDTO;
import com.capgemini.backend.SpringBoot.application.games.service.GameService;
import com.capgemini.backend.SpringBoot.domain.games.model.Game;
import com.capgemini.backend.SpringBoot.infraestructure.games.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames().stream()
                .map(GameMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.getGameById(id);
        return game.map(g -> ResponseEntity.ok(GameMapper.toDTO(g)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO) {
        Game game = GameMapper.toEntity(gameDTO);
        Game nuevoGame = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(GameMapper.toDTO(nuevoGame));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        Game game = GameMapper.toEntity(gameDTO);
        Game gameActualizado = gameService.updateGame(id, game);
        return gameActualizado != null ? ResponseEntity.ok(GameMapper.toDTO(gameActualizado)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        return gameService.deleteGame(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}