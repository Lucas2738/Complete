package com.example.demo.repository.primary.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the tbl_employees database table.
 * 
 */
@Entity
@Table(name="tbl_employees")
@NamedQuery(name="TblEmployee.findAll", query="SELECT t FROM TblEmployee t")
public class TblEmployee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    @JsonBackReference
	private TblRole role;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    @JsonBackReference
    private TblCompany company;
    
	public TblEmployee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public TblRole getRole() {
		return role;
	}

	public void setRole(TblRole role) {
		this.role = role;
	}

	public TblCompany getCompany() {
		return company;
	}

	public void setCompany(TblCompany company) {
		this.company = company;
	}

}