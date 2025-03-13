package com.example.gameengineservice.api;

import com.example.gameengineservice.dto.GameResponse;
import com.example.gameengineservice.dto.MoveRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    value = "game-integration-service",
    url = "${integration.game-engine.host:0.0.0.0}",
    path = "api/v1/game"
)
public interface GameAPI {

    @PostMapping("/create")
    GameResponse createGame();

    @PostMapping("/{gameId}/move")
    GameResponse makeMove(@PathVariable UUID gameId, @RequestBody @Valid MoveRequest move);

    @GetMapping("/{gameId}")
    GameResponse getGameState(@PathVariable @NotNull UUID gameId);
}
