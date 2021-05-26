package com.example.demo.repository.primary.entities.mappedsuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="product_msc_pc_table")
@NamedQuery(name="ProductMscPcTable.findAll", query="SELECT p FROM ProductMscPcTable p")
@Data
@NoArgsConstructor
public class ProductMscPcTable extends ProductMscTable {

	private String cpu;
	private String gpu;
	private String ram;

}