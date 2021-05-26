package com.example.demo;

import com.example.demo.business.domain.EmployeeModel;
import com.example.demo.repository.primary.entities.TblEmployee;
import com.example.demo.util.mappers.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = DemoApplication.class)
public class MapperTest {

    @Autowired
    public EmployeeMapper mapper;

    @Test
    void test(){
        EmployeeModel model = EmployeeModel
                .builder()
                .name("Luca")
                .surname("Cirillo")
                .email("luca.cirillo.2738@gmail.com")
                .role("IT BackEnd Specialist")
                .build();

        TblEmployee entity = mapper.modelToEntity(model);

        assertThat(entity).isNotNull();
    }
}
