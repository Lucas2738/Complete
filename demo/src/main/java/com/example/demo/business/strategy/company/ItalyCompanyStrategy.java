package com.example.demo.business.strategy.company;

import com.example.demo.business.strategy.Strategy;

@Strategy(family = CompanyStrategy.class, type = "ITA")
public class ItalyCompanyStrategy implements CompanyStrategy{
    @Override
    public float calculateTaxes() {
        return 21.00f;
    }
}
