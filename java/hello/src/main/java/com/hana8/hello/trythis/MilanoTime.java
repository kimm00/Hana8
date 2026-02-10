package com.hana8.hello.trythis;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MilanoTime {

	public static void main(String[] args) {
		ZoneId milano = ZoneId.of("Europe/Rome");

		ZonedDateTime nowMilano = ZonedDateTime.now(milano);

		System.out.println(nowMilano.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
}
