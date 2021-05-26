package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.TblRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<TblRole, Integer> {

	@EntityGraph(value = "TblRole.employees")
	Optional<TblRole> findById(Integer id);
	
	
	@EntityGraph(value = "TblRole.employeesAndCompany")
	Optional<TblRole> findEmployeesAndCompanyById(Integer id);
	
	@EntityGraph(value = "TblRole.employeesCompanyCountry")
	Optional<TblRole> findEmployeesCompanyCountryById(Integer id);
	
}
