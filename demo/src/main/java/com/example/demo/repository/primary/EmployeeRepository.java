package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.TblEmployee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<TblEmployee, Integer>{

	@Query("SELECT e FROM TblEmployee e WHERE e.email = :email")
	public TblEmployee findByMail(@Param("email") String name);
	
}
