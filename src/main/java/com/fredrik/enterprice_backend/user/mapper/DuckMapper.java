package com.fredrik.enterprice_backend.user.mapper;

import com.fredrik.enterprice_backend.user.dto.createDuckDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;
import com.fredrik.enterprice_backend.user.model.Duck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    responseDuckDTO toResponseDTO(Duck duck);
}
