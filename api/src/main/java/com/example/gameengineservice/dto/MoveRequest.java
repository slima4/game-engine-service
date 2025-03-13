package com.example.gameengineservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoveRequest {

    @NotNull
    Integer row;

    @NotNull
    Integer col;

    @NotNull
    @Pattern(regexp = "[XO]", message = "Invalid symbol. Must be X or O")
    String symbol;
}
