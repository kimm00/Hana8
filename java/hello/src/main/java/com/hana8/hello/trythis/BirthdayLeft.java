package com.hana8.hello.trythis;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BirthdayLeft {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();

		LocalDate birthday = LocalDate.of(today.getYear(), 2, 14);

		// 이미 생일이 지났으면 내년
		if (birthday.isBefore(today)) {
			birthday = birthday.plusYears(1);
		}

		long days = ChronoUnit.DAYS.between(today, birthday);

		System.out.println("다음 생일까지 남은 일 수 = " + days);
	}

}
