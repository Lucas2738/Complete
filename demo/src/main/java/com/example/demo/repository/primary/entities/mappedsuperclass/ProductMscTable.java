package com.example.demo.repository.primary.entities.mappedsuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * this parent classes is not an entity. Entities that extends this @MappedSuperClass class match with table and have
 * common fields in common. If we're using this strategy, ancestors cannot contain associations with other entities.*/
@MappedSuperclass
@Data
@NoArgsConstructor
public class ProductMscTable {

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
