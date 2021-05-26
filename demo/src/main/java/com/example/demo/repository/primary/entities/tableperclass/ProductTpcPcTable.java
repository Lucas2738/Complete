package com.example.demo.repository.primary.entities.tableperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="product_tpc_pc_table")
@NamedQuery(name="ProductTpcPcTable.findAll", query="SELECT p FROM ProductTpcPcTable p")
@Data
@NoArgsConstructor
public class ProductTpcPcTable extends ProductTpcTable {

	private String cpu;
	private String gpu;
	private String ram;

}