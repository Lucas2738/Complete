package com.example.demo.repository.primary.entities.joinedtable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table(name="product_jt_motorcycle_table")
@PrimaryKeyJoinColumn(name = "mtr_id")
@Data
@NoArgsConstructor
public class ProductJtMotorcycleTable extends ProductJtTable {

	private String color;

	private int displacement;

	@Column(name="motorcycle_type")
	private String motorcycleType;


}