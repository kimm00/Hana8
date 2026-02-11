package com.hana8.hello.trythis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Collection {

	public static class Transfer {

		int id;
		String receiver;
		String sender;
		int amount;

		// 생성자
		Transfer(int id, String receiver, String sender, int amount) {
			this.id = id;
			this.receiver = receiver;
			this.sender = sender;
			this.amount = amount;
		}
	}

	public static void main(String[] args) {
		List<Transfer> list = new ArrayList<>();
		list.add(new Transfer(1001, "Hong", "Choi", 5000));
		list.add(new Transfer(1002, "Lee", "Park", 20000));
		list.add(new Transfer(1003, "Hong", "Jade", 10000));
		list.add(new Transfer(1004, "Kim", "Park", 20000));
		list.add(new Transfer(1005, "Lee", "Choi", 5000));
		list.add(new Transfer(1006, "Hong", "Choi", 5000));

		// 1. 받는사람 기준으로 보낸사람 목록 출력
		// (중복제거, 이체된 순서대로 출력)
		Map<String, LinkedHashSet<String>> map = new HashMap<>();
		for (Transfer t : list) {
			map.putIfAbsent(t.receiver, new LinkedHashSet<>());
			map.get(t.receiver).add(t.sender);
		}

		System.out.println(" 1. 받는사람 기준으로 보낸사람 목록 출력 ");
		// String.join(구분자, 컬렉션): 배열[] 없이 출력
		// [Choi, Jade] -> "Choi, Jade"
		for (String receiver : map.keySet()) {
			System.out.print(receiver + " : ");
			System.out.println(String.join(", ", map.get(receiver)));
		}

		// 2. 가장 자주 보낸 사람과 가장 많은 금액을 보낸 사람을 출력하시오.
		Map<String, Integer> sendCount = new HashMap<>();
		Map<String, Integer> sendSum = new HashMap<>();

		// getOrDefault = 없으면 0부터 시작
		for (Transfer t : list) {
			//횟수 누적
			sendCount.put(t.sender, sendCount.getOrDefault(t.sender, 0) + 1);
			sendSum.put(t.sender, sendSum.getOrDefault(t.sender, 0) + t.amount);
		}

		System.out.println(" 2. 가장 자주 보낸 사람과 가장 많은 금액을 보낸 사람을 출력하시오. ");
		// 가장 자주 보낸 사람 찾기 - sendCount의 key들 반복
		String maxCountPerson = null;
		int maxCount = 0;

		for (String receiver : sendCount.keySet()) {
			int cnt = sendCount.get(receiver);
			if (cnt > maxCount) {
				maxCount = cnt;
				maxCountPerson = receiver;
			}
		}

		// 가장 많은 금액을 보낸 사람 찾기
		String maxSumPerson = null;
		int maxSum = 0;

		for (String sender : sendSum.keySet()) {
			int sum = sendSum.get(sender);
			if (sum > maxSum) {
				maxSum = sum;
				maxSumPerson = sender;
			}
		}

		System.out.print("자주 : " + maxCountPerson + " (" + maxCount + "회), ");
		System.out.println("최고금액: " + maxSumPerson + " (" + String.format("%,d", maxSum) + "원)");

		// 3. 가장 많은 금액을 받은 사람을 출력하시오.
		Map<String, Integer> receiveSum = new HashMap<>();

		// Hong → 15000
		// Lee -> 25000
		// Kim -> 20000
		for (Transfer t : list) {
			receiveSum.put(t.receiver, receiveSum.getOrDefault(t.receiver, 0) + t.amount);
		}

		String maxReceivePerson = null;
		int maxReceive = 0;

		// .get(key)는 value를 반환 -> amount를 반환
		System.out.println(" 3. 가장 많은 금액을 받은 사람을 출력하시오. ");
		for (String receiver : receiveSum.keySet()) {
			int sum = receiveSum.get(receiver);
			if (sum > maxReceive) {
				maxReceive = sum;
				maxReceivePerson = receiver;
			}
		}

		System.out.println(maxReceivePerson + " (" + String.format("%,d", maxReceive) + "원)");
	}
}
