package com.example.demo;

import com.example.demo.business.strategy.StrategyFactory;
import com.example.demo.business.strategy.company.CompanyStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = DemoApplication.class)
public class StrategyTest {

    @Autowired
    StrategyFactory<CompanyStrategy> strategyStrategyFactory;

    @Test
    public void getStrategy() throws Exception {
        CompanyStrategy strategy = strategyStrategyFactory.getStrategy(CompanyStrategy.class, "ITA");
        assertThat(strategy).isNotNull();

        assertThat(strategy.calculateTaxes() == 20.00f);
    }
}
