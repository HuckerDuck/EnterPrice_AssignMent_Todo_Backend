package com.fredrik.enterprice_backend.todo_item.mapper;

import com.fredrik.enterprice_backend.todo_item.dto.CreateDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.dto.ResponseDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.model.DuckTask;
import com.fredrik.enterprice_backend.user.model.Duck;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface DuckTaskMapper {
    //? From the DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DuckTask toEntity(CreateDuckTaskDTO createDuckTaskDTO, @Context Duck duck);

    //? Entity to the ResponseDTO
    ResponseDuckTaskDTO toResponseDTO(DuckTask duckTask);

    //? Below this is for the AfterMapping
    //? This is to make sure that it's saved to the database and to
    //? The correct Duck
    @AfterMapping
    default void setDuck(DuckTask duckTask, @Context Duck duck){
        duckTask.setDuck(duck);
    }
}
