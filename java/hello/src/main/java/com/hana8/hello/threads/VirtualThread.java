package com.hana8.hello.threads;

public class VirtualThread {

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			final int ii = i;
			Thread.ofVirtual().start(() -> {
				System.out.println("Thread" + ii);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}
}
