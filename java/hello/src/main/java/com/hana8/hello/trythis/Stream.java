package com.hana8.hello.trythis;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Stream {

	public static void main(String[] args) {
		List<Integer> list = List.of(1, 10, 6, 3, 3, 5, 4, 2, 7, 7, 9, 8, 10);

		System.out.println("짝수의 개수 = " + list.stream().filter(i -> i % 2 == 0).count());
		System.out.println("각 숫자 제곱 = " + list.stream().map(i -> i * i).toList());
		System.out.println("중복 제거 = " + list.stream().distinct().toList());
		System.out.println("기본 정렬 = " + list.stream().sorted().toList());
		System.out.println(
				"역순(내림차순) 정렬 = " + list.stream()
						.sorted(Collections.reverseOrder()).toList());
		System.out.println("처음 5개만 출력 = " + list.stream().limit(5).toList());
		System.out.println("처음 5개 건너뛰고 출력 = " + list.stream().skip(5).toList());
		System.out.println("값이 5보다 큰 것만 출력 = " + list.stream().filter(i -> i > 5).toList());
		System.out.println("1~10의 합계 = " + IntStream.rangeClosed(1, 10).sum());

		double avg = new Random().ints(5, 1, 11).average().orElse(0);
		System.out.println("random 5개의 평균 = " + avg);
	}

}
