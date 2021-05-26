package com.example.demo.business;

import com.example.demo.business.domain.EmployeeModel;

import java.util.List;

public interface DemoService {

    List<EmployeeModel> getAll();

    EmployeeModel get(String email);

    List<EmployeeModel> get(Integer id);

    List<EmployeeModel> getEntityGraph(Integer id);

    List<EmployeeModel> getEntitySubGraph(Integer id);

    EmployeeModel getQuerydsl(int id);

    EmployeeModel getJPQL(String mail);

    boolean addEmployee(EmployeeModel employee);

}
