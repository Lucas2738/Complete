package com.example.demo.repository.primary.entities.mappedsuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="product_msc_motorcycle_table")
@NamedQuery(name="ProductMscMotorcycleTable.findAll", query="SELECT p FROM ProductMscMotorcycleTable p")
@Data
@NoArgsConstructor
public class ProductMscMotorcycleTable extends ProductMscTable {

	private String color;
	private int displacement;

	@Column(name="motorcycle_type")
	private String motorcycleType;

	private String name;

}