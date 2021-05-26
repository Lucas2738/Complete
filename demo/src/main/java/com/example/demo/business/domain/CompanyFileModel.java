package com.example.demo.business.domain;

import com.example.demo.util.validations.BasicInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyFileModel {

    @NotBlank(groups = BasicInfo.class)
    private String fileData;

    @NotBlank(groups = BasicInfo.class)
    private String fileName;

    @NotBlank(groups = BasicInfo.class)
    private String mimeType;

    @NotNull(groups = BasicInfo.class)
    private Integer stockId;
}
