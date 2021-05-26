package com.example.demo.business.batch;

import com.example.demo.business.domain.CoffeeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CoffeeItemProcessor implements ItemProcessor<CoffeeModel, CoffeeModel> {


    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeItemProcessor.class);

    @Override
    public CoffeeModel process(final CoffeeModel coffee) throws Exception {
        String brand = coffee.getBrand().toUpperCase();
        String origin = coffee.getOrigin().toUpperCase();
        String chracteristics = coffee.getCharacteristics().toUpperCase();

        CoffeeModel transformedCoffee = new CoffeeModel(brand, origin, chracteristics);
        LOGGER.info("Converting ( {} ) into ( {} )", coffee, transformedCoffee);

        return transformedCoffee;
    }
}
