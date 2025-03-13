package com.example.gameengineservice.mapper;

import com.example.gameengineservice.dto.GameResponse;
import com.example.gameengineservice.model.Game;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {

    GameResponse toGameResponse(Game game);

}
