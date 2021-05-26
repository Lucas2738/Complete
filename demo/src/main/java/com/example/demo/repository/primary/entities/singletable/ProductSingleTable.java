package com.example.demo.repository.primary.entities.singletable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**All entities are in the same unique table. Create a parent class with SINGLE_TANLE inheritance type used
 * for share commons field between entities. Each entity are distinguished from others by a discriminator column
 * defined in the common superclass*/
@Entity(name="product_single_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public class ProductSingleTable {

	@Id
	@Column(name="product_id")
	private int productId;

	private String address;
	private String city;
	private String description;
	private String manufacture;
	private String name;
	private float price;
	private int quantity;
}