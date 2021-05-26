package com.example.demo.business.impl;

import com.example.demo.business.DemoTransaction;
import com.example.demo.repository.primary.EmployeeRepository;
import com.example.demo.repository.primary.entities.TblEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DemoTransactionImpl implements DemoTransaction {

	Logger logger = LoggerFactory.getLogger(DemoTransactionImpl.class);

	private ExecutorService executor = Executors.newFixedThreadPool(2);

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private EntityManager em;
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	private TransactionTemplate transactionTemplate;

	@PostConstruct
	private void setUp() {
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	@Override
	public void readUncommitted() {
		logger.error("READ UNCOMMITTED: START");
		try {
			CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
				try {
					return t1();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			});
			CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> t2());
			cf1.get();
			cf2.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void phantomRead() {
		// TODO Auto-generated method stub
	}

	@Override
	public void nonRepeatableRead() {
		// TODO Auto-generated method stub
	}
	
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public int t1() throws Exception {
		logger.error("T1: start");

		
//		TblEmployee emp = employeeRepository.findById(3).get();
//		emp.setFirstName(emp.getFirstName() + "_MODIFIED");
//		employeeRepository.save(emp);
		
		TblEmployee emp = em.find(TblEmployee.class, 3);
		emp.setFirstName(emp.getFirstName() + "_MODIFIED");
		em.flush();
		em.persist(emp);
		logger.error("T1: modify");
		try {
			Thread.sleep(5000);
			//throw new Exception();
		} catch (InterruptedException e) {
		}
		logger.error("T1: rolledback");
		logger.error("T1: end");

		return 0;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
	public int t2() {
		logger.error("T2: start");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		TblEmployee emp = em.find(TblEmployee.class, 3);
		//TblEmployee emp = employeeRepository.findById(3).get();
		logger.error("T2: READ UNCOMMITTED {}", emp.getFirstName());
		
		logger.error("T2: end");
		return 0;
	}
}
