package com.example.demo.repository.primary.entities.mappedsuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="product_msc_car_table")
@NamedQuery(name="ProductMscCarTable.findAll", query="SELECT p FROM ProductMscCarTable p")
@Data
@NoArgsConstructor
public class ProductMscCarTable extends ProductMscTable {

	private String color;
	private int displacement;

	@Column(name="door_num")
	private int doorNum;

	@Column(name="wheels_num")
	private int wheelsNum;


}