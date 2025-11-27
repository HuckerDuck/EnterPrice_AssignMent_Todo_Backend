package com.fredrik.enterprice_backend.user.mapper;

import com.fredrik.enterprice_backend.user.dto.createDuckDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;
import com.fredrik.enterprice_backend.user.dto.updateDuckDTO;
import com.fredrik.enterprice_backend.user.model.Duck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper (componentModel = "spring")
public interface DuckMapper {

    //? Trying to learn MapStruct, might be unnessaery for this project
    //? But it's a good practice to learn new thing i guess
    @Mapping (target = "password", ignore = true)
    @Mapping (target = "duckRoles", ignore = true)
    @Mapping (target = "enabled", ignore = true)
    @Mapping (target = "createdAt", ignore = true)
    @Mapping (target = "updatedAt", ignore = true)
    Duck toEntity(createDuckDTO createDuckDTO);

    //? This is the same as above but with the Duck as the target
    //? This will convert it to a responseDTO
    @Mapping(source = "enabled", target = "enabled")
    responseDuckDTO toResponseDTO(Duck duck);

    //? This is really the same almost as above but with the Duck as the target
    //? Note that we are ignoring updating the id
    //? Good practice to ignore things we don't want to update
    //! The set on the ID is removed anyway so it won't work either way.
    @Mapping (target = "id", ignore = true)
    @Mapping (target = "password", ignore = true)
    @Mapping (target = "duckRoles", ignore = true)
    @Mapping (target = "enabled", ignore = true)
    @Mapping (target = "createdAt", ignore = true)
    @Mapping (target = "updatedAt", ignore = true)
    void updateDuckFromDTO(updateDuckDTO updateDuckDTo, @MappingTarget Duck duck);
}
