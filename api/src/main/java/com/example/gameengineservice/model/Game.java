package com.example.gameengineservice.model;

import com.example.gameengineservice.constant.GameStatus;
import com.example.gameengineservice.exception.InvalidMoveException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Game {

    List<Move> moveHistory = new ArrayList<>();

    @Getter
    UUID id;
    @Getter
    char[][] board = new char[3][3];
    @Getter
    @NonFinal
    String status = "IN_PROGRESS";
    @NonFinal
    char lastPlayer = '-';


    @NonFinal
    boolean isGameOver = false;

    public Game(UUID id) {
        this.id = id;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void applyMove(Move move) {
        if (isGameOver) {
            throw new InvalidMoveException("Game is already over!");
        }

        char symbol = move.getSymbol();

        if (symbol == lastPlayer) {
            throw new InvalidMoveException("Invalid move! Same player cannot move twice in a row.");
        }

        int row = move.getRow();
        int col = move.getCol();

        if (board[row][col] != '-') {
            throw new InvalidMoveException("Invalid move! Cell already occupied.");
        }

        board[row][col] = symbol;

        if (checkWinner(symbol)) {
            isGameOver = true;
            status = symbol == 'X' ? "X_WON" : "O_WON";
        } else if (isBoardFull()) {
            isGameOver = true;
            status = "DRAW";
        }

        moveHistory.add(move);
    }

    private boolean checkWinner(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
            (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '-') {
                    return false;
                }
            }
        }
        return true;
    }

}
