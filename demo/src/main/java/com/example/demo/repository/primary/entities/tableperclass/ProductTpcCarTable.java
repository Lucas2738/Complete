package com.example.demo.repository.primary.entities.tableperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="product_tpc_car_table")
@NamedQuery(name="ProductTpcCarTable.findAll", query="SELECT p FROM ProductTpcCarTable p")
@Data
@NoArgsConstructor
public class ProductTpcCarTable extends ProductTpcTable {

	private String color;
	private int displacement;

	@Column(name="door_num")
	private int doorNum;

	@Column(name="wheels_num")
	private int wheelsNum;


}