package com.example.demo.repository.primary.entities.joinedtable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**Using this strategy, each class in the hierarchy is mapped to its table. The only column which repeatedly appears in
 * all the tables is the identifier, which will be used for joining them when needed.
 * The disadvantage of this inheritance mapping method is that retrieving entities requires joins between tables, which
 * can result in lower performance for large numbers of records.
 *
 * The number of joins is higher when querying the parent class as it will join with every single related child – so
 * performance is more likely to be affected the higher up the hierarchy we want to retrieve records.*/
@Entity
@Table(name = "product_jt_parent_table")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public class ProductJtTable {

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