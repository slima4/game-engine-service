package com.example.gameengineservice.dto;

import com.example.gameengineservice.model.Move;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameResponse {

    UUID id;

    @Default
    List<Move> moveHistory = new ArrayList<>();

    @Default
    char[][] board = new char[3][3];

    String status;

}
