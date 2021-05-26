package com.example.demo.repository.primary.entities.tableperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="product_tpc_motorcycle_table")
@NamedQuery(name="ProductTpcMotorcycleTable.findAll", query="SELECT p FROM ProductTpcMotorcycleTable p")
@Data
@NoArgsConstructor
public class ProductTpcMotorcycleTable extends ProductTpcTable {

	private String color;
	private int displacement;

	@Column(name="motorcycle_type")
	private String motorcycleType;

	private String name;

}