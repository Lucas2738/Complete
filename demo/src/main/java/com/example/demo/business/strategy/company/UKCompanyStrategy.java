package com.example.demo.business.strategy.company;

import com.example.demo.business.strategy.Strategy;

@Strategy(family = CompanyStrategy.class, type = "UK")
public class UKCompanyStrategy implements CompanyStrategy{
    @Override
    public float calculateTaxes() {
        return 0;
    }
}
