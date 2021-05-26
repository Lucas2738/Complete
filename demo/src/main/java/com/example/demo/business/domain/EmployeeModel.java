package com.example.demo.business.domain;

import com.example.demo.util.validations.AdvanceInfo;
import com.example.demo.util.validations.BasicInfo;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
    @NotBlank(groups = BasicInfo.class, message = "Name is mandatory")
    private String name;
    @NotBlank(groups = BasicInfo.class, message = "Surname is mandatory")
    private String surname;
    @Email(groups = BasicInfo.class, regexp = "^(.+)@(.+)$",message = "Email is mandatory")
    private String email;
    @NotBlank@NotBlank(groups = AdvanceInfo.class)
    private String role;

}
