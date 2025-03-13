package com.example.gameengineservice.controller;

import com.example.gameengineservice.dto.GameResponse;
import com.example.gameengineservice.dto.MoveRequest;
import com.example.gameengineservice.mapper.GameMapper;
import com.example.gameengineservice.mapper.MoveMapper;
import com.example.gameengineservice.model.Game;
import com.example.gameengineservice.model.Move;
import com.example.gameengineservice.service.GameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Game")
@RestController
@RequestMapping(value = "api/v1/game", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameController {

    GameService gameService;
    GameMapper gameMapper;
    MoveMapper moveMapper;

    @PostMapping("/create")
    public GameResponse createGame() {
        final Game game = gameService.createNewGame();
        return gameMapper.toGameResponse(game);
    }

    @PostMapping("/{gameId}/move")
    public GameResponse makeMove(@PathVariable UUID gameId, @RequestBody @Valid MoveRequest moveRequest) {
        final Move move = moveMapper.toMove(moveRequest);
        final Game game = gameService.makeMove(gameId, move);
        return gameMapper.toGameResponse(game);
    }

    @GetMapping("/{gameId}")
    public GameResponse getGameState(@PathVariable @NotNull UUID gameId) {
        final Game game = gameService.getGame(gameId);
        return gameMapper.toGameResponse(game);
    }
}
