package com.example.demo.repository.primary.impl;

import com.example.demo.repository.primary.EmployeeRepositoryCustom;
import com.example.demo.repository.primary.entities.QTblEmployee;
import com.example.demo.repository.primary.entities.TblEmployee;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{

	@Autowired
	@Qualifier("mysqlEntityManager")
	EntityManager mysqlEntityManager;

	@Bean
	public JPAQueryFactory mysqlJpa() {
		return new JPAQueryFactory(mysqlEntityManager);
	}
	
	@Autowired
	private EntityManager em;
	
	private QTblEmployee employee = QTblEmployee.tblEmployee;
	
	@Override
	public TblEmployee getEmployeeById(int id) {
		return mysqlJpa().selectFrom(employee).where(employee.id.eq(id)).fetchOne();
	}

	@Override
	public TblEmployee findByMailJpQL(String mail) {
		String jpqlQuery = "SELECT e FROM TblEmployee e WHERE e.email = :mail";
		Query query = em.createQuery(jpqlQuery);
		query.setParameter("mail", mail);

		return (TblEmployee) query.getSingleResult();
	}

}
