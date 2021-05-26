package com.example.demo.repository.primary.entities.singletable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("PC")
@Data
@NoArgsConstructor
public class ProductSingleTablePC extends ProductSingleTable {

	private String cpu;
	private String gpu;
	private String ram;
}