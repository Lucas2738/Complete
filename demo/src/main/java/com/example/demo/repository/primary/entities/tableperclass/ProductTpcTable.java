package com.example.demo.repository.primary.entities.tableperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**Parent Class is an entity but hasn't a table. All the properties of a class, are in its table, no join is required.
 * The resulting schema is similar to the one using @MappedSuperclass, but unlike it, table per class will indeed define
 * entities for parent classes, allowing associations and polymorphic queries as a result.
 * This is not very different from merely mapping each entity without inheritance. The distinction is apparent when
 * querying the base class, which will return all the sub-class records as well by using a UNION statement in the
 * background.
 *
 * The use of UNION can also lead to inferior performance when choosing this strategy. Another issue is that we can no
 * longer use identity key generation.
 * */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
public abstract class ProductTpcTable {

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
