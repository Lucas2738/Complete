package com.example.demo;

import com.example.demo.business.DemoTransaction;
import com.example.demo.repository.primary.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = DemoApplication.class)
public class EmployeeEntityRepositoryTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DemoTransaction demoTransaction;
	
	@Test
	void injectedComponentsAreNotNull() {
	    assertThat(dataSource).isNotNull();
	    assertThat(jdbcTemplate).isNotNull();
	    assertThat(entityManager).isNotNull();
		assertThat(employeeRepository.findAll()).hasSize(3);
	}
	
	@Test
	void readUncommitted() {
		demoTransaction.readUncommitted();
	}
}
