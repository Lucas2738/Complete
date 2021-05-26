package com.example.demo.util.mappers;

import com.example.demo.business.domain.CompanyFileModel;
import com.example.demo.repository.rx.entities.CompanyFiles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface CompanyFileMapper {

    @Mappings({
            @Mapping(target="fileData", source="fileData", qualifiedByName = "bytesToBase64"),
            @Mapping(target="fileName", source="fileName"),
            @Mapping(target="mimeType", source="mimeType"),
            @Mapping(target="stockId", source="stockId")
    })
    CompanyFileModel entityToModel(CompanyFiles entity);

    @Mappings({
            @Mapping(target="fileData", source="fileData", qualifiedByName = "base64ToBytes"),
            @Mapping(target="fileName", source="fileName"),
            @Mapping(target="mimeType", source="mimeType"),
            @Mapping(target="stockId", source="stockId")
    })
    CompanyFiles modelToEntity(CompanyFileModel dto);

    @Named("bytesToBase64")
    public static String bytesToBase64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Named("base64ToBytes")
    public static byte[] bytesToBase64(String base64){
        return Base64.getDecoder().decode(base64);
    }
}
