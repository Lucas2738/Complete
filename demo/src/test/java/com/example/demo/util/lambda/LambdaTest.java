package com.example.demo.util.lambda;

import com.example.demo.util.functionals.MyFunctionalInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class LambdaTest {

	@Test
	void testLambda() {
		List<Integer> longList = new ArrayList<Integer>();
		longList.add(1);
		longList.add(3);
		longList.add(5);
		longList.add(7);
		
		print(longList, (List<Integer> s ) -> {return s.get(0);});
		print(longList, s -> longList.get(1));
		
		testConsumer(Integer.valueOf(3), x -> { x = x + 6; System.out.println("After: "+ x);});
		assumeTrue(true);
	}

	@Test
	void testPassingValue() {
		List<Integer> longList = new ArrayList<Integer>();
		longList.add(1);
		longList.add(3);
		longList.add(5);
		longList.add(7);
		
		Integer i = 220;
		System.out.println(i);
		System.out.println(add(i));
		System.out.println(i);

		String s = "PROVA";
		System.out.println(s);
		System.out.println(concat(s));
		System.out.println(s);
		
		My m = new My("prova");
		System.out.println(m.getValue());
		System.out.println(modify(m).getValue());
		System.out.println(m.getValue());
		
		List<Integer> res = longList.stream().collect(Collectors.toList());
		Map<Integer, Integer> map = longList.stream().collect(Collectors.toMap(Function.identity(), e -> e));
		
		assumingThat(() -> res.size() > 0, () -> System.out.println("OK"));
	}
	
	public class My{
		private String value;
		
		public My(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public static <T> void print(List<T> list, MyFunctionalInterface<T> f) {
		T max = f.getMax(list);
		
		System.out.println(max);
	}
	
	public static void testConsumer(Integer i, Consumer<Integer> consumer) {
		System.out.println("Before: "+ i);
		consumer.accept(i);
	}
	
	public static Integer add(Integer i) {
		i = i + 10;
		return i;
	}
	
	public static String concat(String i) {
		i = i.concat("ADDED");
		return i;
	}
	
	public static My modify(My i) {
		i.setValue("modified");
		return i;
	}
}
