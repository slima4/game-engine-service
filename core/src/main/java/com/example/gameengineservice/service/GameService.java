package com.example.gameengineservice.service;

import com.example.gameengineservice.exception.InvalidMoveException;
import com.example.gameengineservice.model.Game;
import com.example.gameengineservice.model.Move;
import java.util.HashMap;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameService {

    private final HashMap<UUID, Game> games = new HashMap<>();

    public Game createNewGame() {
        final UUID gameId = UUID.randomUUID();
        final Game game = new Game(gameId);
        games.put(gameId, game);
        return game;
    }

    public Game getGame(UUID gameId) {
        return games.getOrDefault(gameId, null);
    }

    public Game makeMove(UUID gameId, Move move) {
        final Game game = games.get(gameId);
        if (game == null)
            throw new InvalidMoveException("Game not found!");

        game.applyMove(move);
        return game;
    }
}
