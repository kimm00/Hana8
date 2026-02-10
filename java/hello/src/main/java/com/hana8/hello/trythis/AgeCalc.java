package com.hana8.hello.trythis;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class AgeCalc {

	public static void main(String[] args) {
		LocalDateTime birth = LocalDateTime.of(2005, 2, 14, 11, 26, 0);

		LocalDateTime now = LocalDateTime.now();

		// 날짜 차이 (년/월/일)
		Period p = Period.between(birth.toLocalDate(), now.toLocalDate());

		// 날짜를  뺀 뒤 남은 시간 차이
		LocalDateTime temp = birth.plusYears(p.getYears())
				.plusMonths(p.getMonths()).plusDays(p.getDays());

		Duration d = Duration.between(temp, now);

		System.out.printf(
				"%d년 %d개월 %d일 %d시간 %d분 %d초%n",
				p.getYears(),
				p.getMonths(),
				p.getDays(),
				d.toHours(),
				d.toMinutes() % 60,
				d.getSeconds() % 60
		);

		// 총 일 수 / 총 시간 수
		Duration dDate = Duration.between(birth, now);
		System.out.println("총 일 수 = " + dDate.toDays());
		System.out.println("총 시간 수 = " + dDate.toHours());

	}
}
