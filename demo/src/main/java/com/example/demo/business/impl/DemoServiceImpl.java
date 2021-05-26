package com.example.demo.business.impl;

import com.example.demo.business.DemoService;
import com.example.demo.business.domain.EmployeeModel;
import com.example.demo.repository.primary.EmployeeRepository;
import com.example.demo.repository.primary.EmployeeRepositoryCustom;
import com.example.demo.repository.primary.RoleRepository;
import com.example.demo.repository.primary.entities.TblEmployee;
import com.example.demo.util.mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRepositoryCustom employeeRepositoryCustom;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeMapper mapper;

    @Override
    @Transactional
    public List<EmployeeModel> getAll() {
        List<EmployeeModel> list = new ArrayList<>();
        employeeRepository.findAll().forEach(c -> list.add(mapper.entityToModel(c)));
        return list;
    }

    @Override
    @Transactional
    public EmployeeModel get(String email) {
        return mapper.entityToModel(employeeRepository.findByMail(email));
    }

    @Override
    public List<EmployeeModel> get(Integer id) {
        List<EmployeeModel> list = new ArrayList<>();
        roleRepository.findById(id).get().getEmployees().forEach(c -> list.add(mapper.entityToModel(c)));
        return list;
    }

    @Override
    public List<EmployeeModel> getEntityGraph(Integer id) {
        List<EmployeeModel> list = new ArrayList<>();
        roleRepository.findEmployeesAndCompanyById(id).get().getEmployees().forEach(c -> list.add(mapper.entityToModel(c)));
        return list;
    }

    @Override
    @Transactional
    public List<EmployeeModel> getEntitySubGraph(Integer id) {
        List<EmployeeModel> list = new ArrayList<>();
        roleRepository.findEmployeesCompanyCountryById(id).get().getEmployees().forEach(c -> list.add(mapper.entityToModel(c)));
        return list;
    }

    @Override
    @Transactional
    public EmployeeModel getQuerydsl(int id) {
        return mapper.entityToModel(employeeRepositoryCustom.getEmployeeById(id));
    }

    @Override
    @Transactional
    public EmployeeModel getJPQL(String mail) {
        return mapper.entityToModel(employeeRepositoryCustom.findByMailJpQL(mail));
    }

    @Override
    public boolean addEmployee(EmployeeModel employee){
        boolean result = false;
        TblEmployee entity = mapper.modelToEntity(employee);
        entity.setRole(null);
        result = employeeRepository.save(entity) != null;
        return result;
    }
}
