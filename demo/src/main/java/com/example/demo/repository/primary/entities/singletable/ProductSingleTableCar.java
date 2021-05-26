package com.example.demo.repository.primary.entities.singletable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("CAR")
@Data
@NoArgsConstructor
public class ProductSingleTableCar extends ProductSingleTable {

	private String color;
	private int displacement;

	@Column(name="door_num")
	private int doorNum;

	@Column(name="wheels_num")
	private int wheelsNum;

}