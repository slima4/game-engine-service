package com.example.gameengineservice.mapper;

import com.example.gameengineservice.dto.MoveRequest;
import com.example.gameengineservice.model.Move;
import org.mapstruct.Mapper;

@Mapper
public interface MoveMapper {

    Move toMove(MoveRequest moveRequest);
}
