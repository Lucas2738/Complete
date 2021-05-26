package com.example.demo.repository.primary.entities.singletable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("MTR")
@Data
@NoArgsConstructor
public class ProductSingleTableMotorcyle extends  ProductSingleTable{

	private String color;
	private int displacement;

	@Column(name="motorcycle_type")
	private String motorcycleType;

}