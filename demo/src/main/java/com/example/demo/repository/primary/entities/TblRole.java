package com.example.demo.repository.primary.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the tbl_role database table.
 * 
 */
@Entity
@Table(name="tbl_role")
@NamedQuery(name="TblRole.findAll", query="SELECT t FROM TblRole t")
@NamedEntityGraph(name = "TblRole.employees",
	attributeNodes = @NamedAttributeNode("employees")
)

@NamedEntityGraph(name = "TblRole.employeesAndCompany",
	attributeNodes = @NamedAttributeNode(value = "employees", subgraph = "subgraph.company"),
    subgraphs = {
            @NamedSubgraph(name = "subgraph.company", 
                           attributeNodes = @NamedAttributeNode("company")
            )
 	}
)

@NamedEntityGraph(name = "TblRole.employeesCompanyCountry",
attributeNodes = @NamedAttributeNode(value = "employees", subgraph = "subgraph.company"),
subgraphs = {
        @NamedSubgraph(name = "subgraph.company", 
                       attributeNodes = @NamedAttributeNode(value = "company", subgraph = "subgraph.countries")
        ),
        @NamedSubgraph(name = "subgraph.countries", 
		        attributeNodes = @NamedAttributeNode("countries")
		)
	}
)
public class TblRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	@JsonManagedReference
	private List<TblEmployee> employees;
	
	public TblRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TblEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<TblEmployee> employees) {
		this.employees = employees;
	}

}