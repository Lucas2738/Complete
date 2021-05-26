package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.TblEmployee;

public interface EmployeeRepositoryCustom {

	public TblEmployee getEmployeeById(int id);
	
	public TblEmployee findByMailJpQL(String name);
}
