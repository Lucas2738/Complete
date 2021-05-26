package com.example.demo.repository.primary.entities.joinedtable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="product_jt_pc_table")
@PrimaryKeyJoinColumn(name = "pc_id")
@Data
@NoArgsConstructor
public class ProductJtPcTable extends ProductJtTable {

	private String cpu;
	private String gpu;
	private String ram;
}