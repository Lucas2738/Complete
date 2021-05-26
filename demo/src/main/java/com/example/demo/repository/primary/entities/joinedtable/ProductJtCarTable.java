package com.example.demo.repository.primary.entities.joinedtable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="product_jt_car_table")
@PrimaryKeyJoinColumn(name = "car_id")
@Data
@NoArgsConstructor
public class ProductJtCarTable extends ProductJtTable {

	private String color;
	private int displacement;

	@Column(name="door_num")
	private int doorNum;

	@Column(name="wheels_num")
	private int wheelsNum;

}