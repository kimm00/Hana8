package com.hana8.hello;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class CollectionPlay {

	public static void main(String[] args) {
//		var alist = new ArrayList<>();
		List<Integer> alist = new ArrayList<>();
		alist.add(4);
		alist.add(5);
		alist.add(1);
		System.out.println("alist = " + alist);

		Queue<Integer> ll = new LinkedList<>();
		ll.offer(4);
		ll.offer(5);
		ll.offer(1);
		System.out.println("ll = " + ll);

		Queue<Integer> pq = new PriorityQueue<>();
		pq.offer(4);
		pq.offer(5);
		pq.offer(1);
		System.out.println("pq = " + pq);
		pq.offer(2);
		System.out.println("pq = " + pq);
		pq.offer(3);
		System.out.println("pq = " + pq);
		pq.offer(3);
		System.out.println("pq = " + pq);
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());

		Set<Integer> ts = new TreeSet<>();
		ts.add(4);
		ts.add(5);
		ts.add(1);
		System.out.println("ts = " + ts);
		ts.add(2);
		System.out.println("ts = " + ts);
		ts.add(3);
		System.out.println("ts = " + ts);

//		Deque<Integer> dq = new ArrayDeque<>();
		Deque<Integer> dq = new LinkedList<>();
		dq.offer(4);
		dq.offer(5);
		dq.offer(1);
		System.out.println("dq = " + dq);
		dq.offer(2);
		System.out.println("dq = " + dq);
		dq.offer(3);
		System.out.println("dq = " + dq);
		dq.offer(3);
		System.out.println("dq = " + dq);
		System.out.println(dq.poll());
		System.out.println(dq.poll());
		System.out.println(dq.poll());
		System.out.println(dq.poll());
		System.out.println(dq.poll());
		System.out.println(dq.poll());
		dq.push(6); // Queue는 push 없어서 Deque만 가능
		System.out.println("dq = " + dq);
		System.out.println(dq.poll());
//		System.out.println(dq.pop());

		Map<Integer, String> map = new HashMap<>(); // key-value 쌍 & 순서 x
		map.put(4, "Kim");
		map.put(11, "Choi");
		map.put(100, "Choi");
		map.put(5, "Hong");
		map.put(1, "Lee");
		System.out.println("map = " + map);

//		Map<Integer, String> map = new TreeMap<>(); // 순서 o
//		map.put(4, "Kim");
//		map.put(11, "Choi");
//		map.put(100, "Choi");
//		map.put(5, "Hong");
//		map.put(1, "Lee");
//		System.out.println("map = " + map);

		Set<Integer> keys = map.keySet();
		System.out.println("keys = " + keys);
		for (int ii : keys) {
			System.out.println("ii = " + ii);
		}
	}
}
