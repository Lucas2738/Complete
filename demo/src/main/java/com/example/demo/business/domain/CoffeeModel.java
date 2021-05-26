package com.example.demo.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeModel {

    private String brand;
    private String origin;
    private String characteristics;

}
