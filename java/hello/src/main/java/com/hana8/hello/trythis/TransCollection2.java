package com.hana8.hello.trythis;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TransCollection2 {

	public static void main(String[] args) {
		String fullLog = """
				001,Hong,Choi,5000
				1002,Lee,Park,20000
				1003,Hong,Jade,10000
				1004,Kim,Park,20000
				1005,Lee,Choi,5000
				1006,Hong,Choi,5000""";

		Map<String, Set<String>> sendersByReceiver = new HashMap<>();
		Map<String, Integer> senderCnt = new HashMap<>();
		Map<String, Integer> senderAmt = new HashMap<>();
		Map<String, Integer> receiverAmt = new HashMap<>();

		for (String log : fullLog.split("\n")) {
			String[] row = log.split(",");
			String receiver = row[1];
			String sender = row[2];
			int amt = Integer.parseInt(row[3]);

			// value가 없다고 가정하고 시작 => 따라서 k만 존재
			sendersByReceiver.computeIfAbsent(receiver, k -> new LinkedHashSet<>()).add(sender);

			senderCnt.compute(sender, (k, v) -> (v == null ? 0 : v) + 1);
			senderAmt.compute(sender, (k, v) -> (v == null ? 0 : v) + amt);

			receiverAmt.compute(receiver, (k, v) -> (v == null ? 0 : v) + amt);

		}

		// entrySet(): Map을 key-value 쌍 묶음으로 바꿔주는 것
		// (Hong, [Choi, Jade])
		// (Lee, [Park, Choi])
		System.out.print("1. ");
		for (Map.Entry<String, Set<String>> entry : sendersByReceiver.entrySet()) {
			System.out.printf("%s: %s\t", entry.getKey(), entry.getValue());
		}
		System.out.println();

		// entrySet() 이용
		printMax(senderCnt, "2. 자주: %s (%d회) , ");
		printMax(senderAmt, "최고금액: %s (%,d원)%n");
		printMax(receiverAmt, "3. %s (%,d원)%n");

		// keySet() 이용
		pMax(senderCnt, "2. 자주: %s (%d회) , ");
		pMax(senderAmt, "최고금액: %s (%,d원)%n");
		pMax(receiverAmt, "3. %s (%,d원)%n");
	}

	// 분석할 Map을 받고, 달라지는 출력 형식 받기
	private static void printMax(Map<String, Integer> map, String fmtStr) {
		String key = "";
		int max = 0;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > max) {
				key = entry.getKey();
				max = entry.getValue();
			}
		}
		System.out.printf(fmtStr, key, max);
	}

	private static void pMax(Map<String, Integer> map, String fmtStr) {
		String resultKey = "";
		int max = 0;
		for (String key : map.keySet()) {
			int value = map.get(key);

			if (value > max) {
				max = value;
				resultKey = key;
			}
		}
		System.out.printf(fmtStr, resultKey, max);
	}
}
