package com.example.demo.business.strategy;

import com.example.demo.business.impl.DemoTransactionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.metamodel.Type;
import java.util.HashMap;
import java.util.Map;

@Component
public class StrategyFactory<T> {

    Logger logger = LoggerFactory.getLogger(DemoTransactionImpl.class);

    @Autowired
    private ApplicationContext applicationContext;

    private Map<Class, Map<String, Object>> strategies;

    @PostConstruct
    private void init() throws Exception{
        Map<String, Object> annotatedBeanClasses = applicationContext.getBeansWithAnnotation(Strategy.class);

        strategies = new HashMap<>();
        annotatedBeanClasses.forEach((k,v) -> {
            Strategy annotation = v.getClass().getAnnotation(Strategy.class);
            Class family = annotation.family();
            String type = annotation.type();
            if(strategies.containsKey(family)){
                if(strategies.get(family).containsKey(type)){
                    logger.error("Strategy family {} contains strategy type {} yet", family, type);
                }else{
                    strategies.get(family).put(type,v);
                }
            }else{
                Map<String, Object> types = new HashMap<>();
                types.put(type, v);
                strategies.put(family, types);
            }
        });
    }

    public T getStrategy(Class<T> family, String type) throws Exception {
        T strategy = (T) strategies.get(family).get(type);
        if(strategy == null){
            throw new Exception("Strategy not found");
        }
        return strategy;
    }
}
