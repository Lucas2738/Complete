package com.example.demo.util.functionals;

import java.util.List;

@FunctionalInterface
public interface MyFunctionalInterface<T> {

	public T getMax(List<T> list);
	
}
