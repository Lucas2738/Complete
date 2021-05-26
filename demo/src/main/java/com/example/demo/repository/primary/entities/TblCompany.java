package com.example.demo.repository.primary.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the tbl_company database table.
 * 
 */
@Entity
@Table(name="tbl_company")
@NamedQuery(name="TblCompany.findAll", query="SELECT t FROM TblCompany t")
public class TblCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	@ManyToMany
	@JoinTable(
			  name = "REL_COUNTRY_COMPANY", 
			  joinColumns = @JoinColumn(name = "company"), 
			  inverseJoinColumns = @JoinColumn(name = "country"))
	private Set<TblCountry> countries; 
	
	public TblCompany() {
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

	public Set<TblCountry> getCountries() {
		return countries;
	}

	public void setCountries(Set<TblCountry> countries) {
		this.countries = countries;
	}

}