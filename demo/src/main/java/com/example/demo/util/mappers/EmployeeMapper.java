package com.example.demo.util.mappers;

import com.example.demo.business.domain.EmployeeModel;
import com.example.demo.repository.primary.entities.TblEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(target="name", source="firstName"),
            @Mapping(target="surname", source="lastName"),
            @Mapping(target="email", source="email"),
            @Mapping(target="role", source="role.description")
    })
    EmployeeModel entityToModel(TblEmployee entity);

    @Mappings({
            @Mapping(target="firstName", source="name"),
            @Mapping(target="lastName", source="surname"),
            @Mapping(target="email", source="email"),
            @Mapping(target="role.description", source="role")
    })
    TblEmployee modelToEntity(EmployeeModel dto);
}
