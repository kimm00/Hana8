package com.hana8.hello.trythis;

import java.time.LocalDate;

public class Time {

	public static void main(String[] args) {
		LocalDate start = LocalDate.of(2026, 3, 23);
		LocalDate end = LocalDate.of(2026, 4, 24);

		int totalHours = 0;

		for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
			// 주말 제외
			if (d.getDayOfWeek().getValue() >= 6) {
				continue;
			}

			// 3월 25일 휴일
			if (d.equals(LocalDate.of(2026, 3, 25))) {
				continue;
			}

			// 4/13 ~ 4/17: 9시간
			if (!d.isBefore(LocalDate.of(2026, 4, 13))
					&& !d.isAfter(LocalDate.of(2026, 4, 17))) {
				totalHours += 9;
				continue;
			}

			// 4/20 하루
			if (d.equals(LocalDate.of(2026, 4, 20))) {
				totalHours += 7;
				continue;
			}

			// 기본 근무
			totalHours += 8;
		}

		System.out.println("총 근무 가능 시간 = " + totalHours + " 시간");
	}

}
