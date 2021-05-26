package com.example.demo.business.strategy;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface Strategy {

    Class<?> family();
    String type();
}
