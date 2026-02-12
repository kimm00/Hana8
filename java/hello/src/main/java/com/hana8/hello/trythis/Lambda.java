package com.hana8.hello.trythis;

import java.util.ArrayList;
import java.util.List;

public class Lambda {

	static List<Integer> filter(List<Integer> list, MyPredicate<Integer> predicate) {
		List<Integer> result = new ArrayList<>();

		for (Integer value : list) {
			if (predicate.test(value)) {
				result.add(value);
			}
		}
		return result;
	}

	static List<Integer> map(List<Integer> list, MyFunction<Integer, Integer> function) {
		return list.stream().map(function::apply).toList();
	}

	static Integer find(List<Integer> list, MyPredicate<Integer> predicate) {
		for (Integer value : list) {
			if (predicate.test(value)) {
				return value;
			}
		}
		return -1;

	}


	static Integer reducer(List<Integer> list, int initValue, MyReducer<Integer, Integer> reducer) {
		int acc = initValue;

		for (Integer value : list) {
			acc = reducer.reduce(acc, value);
		}
		return acc;
	}

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

		List<Integer> evens = filter(numbers, value -> value % 2 == 0); // [2,4,6,8]
		System.out.println("evens = " + evens);

		List<Integer> squares = map(numbers, value -> value * value);   // [1, 4, 9, …]
		System.out.println("squares = " + squares);

		Integer bigger3 = find(numbers, value -> value > 3);            // 4
		System.out.println("bigger3 = " + bigger3);

		int sum1 = reducer(numbers, 100, Integer::sum);                 // 145
		System.out.println("sum1 = " + sum1);

		int sum2 = reducer(numbers, 0, (a, b) -> a * b);                // 0
		System.out.println("sum2 = " + sum2);

		int sum3 = reducer(numbers, 10, (a, b) -> a * b);               // 3628800
		System.out.println("sum3 = " + sum3);

	}

}

@FunctionalInterface
interface MyPredicate<T> {

	boolean test(T t);
}

@FunctionalInterface
interface MyFunction<T, R> {

	R apply(T t);
}

@FunctionalInterface
interface MyReducer<E, R> {

	R reduce(R acc, E e);
}
