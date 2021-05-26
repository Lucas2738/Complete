package com.example.demo.repository.primary.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the tbl_country database table.
 * 
 */
@Entity
@Table(name="tbl_country")
@NamedQuery(name="TblCountry.findAll", query="SELECT t FROM TblCountry t")
public class TblCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@ManyToMany
	private Set<TblCompany> companies;
	
	public TblCountry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TblCompany> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<TblCompany> companies) {
		this.companies = companies;
	}

}