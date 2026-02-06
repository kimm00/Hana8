package com.hana8.hello.trythis;

import java.util.Arrays;

public class Snail {

	public static void main(String[] args) {
//		int n = 5;
//		int[][] a = new int[n][n];
//
//		int num = 1;
//		int top = 0, bottom = n - 1;
//		int left = 0, right = n - 1;
//
//		while (top <= bottom && left <= right) {
//			// 오른쪽으로 이동
//			for (int i = left; i <= right; i++) {
//				a[top][i] = num++;
//			}
//			top++;
//
//			// 아래로 이동
//			for (int i = top; i <= bottom; i++) {
//				a[i][right] = num++;
//			}
//			right--;
//
//			// 왼쪽으로 이동
//			for (int i = right; i >= left; i--) {
//				a[bottom][i] = num++;
//			}
//			bottom--;
//
//			// 위로 이동
//			for (int i = bottom; i >= top; i--) {
//				a[i][left] = num++;
//			}
//			left++;
//		}
//
//		for (int[] row : a) {
//			for (int v : row) {
//				System.out.printf("%3d ", v);
//			}
//			System.out.println();
//		}
		// -----------------------
		makeSnail(5);
		makeSnail(6);
		makeSnail(7);
	}

	public static int[] makeSnail(int N) {
		int[][] snails = new int[N][N];

		int val = 0;
		int row = -1;
		int col = 0;
		int flag = 1;

		// w: 9 -> 7 -> 5 -> 3 -> 1
		for (int w = N + N - 1; w > 0; w -= 2) {
			for (int i = 0; i < w; i++) {
				if (i <= w / 2) {
					row += flag;
				} else {
					col += flag;
				}

				// System.out.printf("col,row = %d,%d (%d)%n", col, row, val + 1);
				snails[col][row] = ++val;
			}

			flag *= -1;
		}

		int[] results = new int[N * N];
		int idx = 0;
		for (int[] _arr : snails) {
			for (int n : _arr) {
				System.out.printf("%3d", n);
				results[idx++] = n;
			}
			System.out.println();
		}
		System.out.println("results = " + Arrays.toString(results));
		return results;
	}

	public static int[] makeTriangleSnail(int N) {
		int[] results = new int[N * (N + 1) / 2];
		int[][] snails = new int[N][N];
		int garo = 0;
		int sero = -1;
		int val = 0;
		// 0 -> 1 -> 2 -> 3
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (i % 3 == 0) {
					sero++;
				} else if (i % 3 == 1) {
					garo++;
				} else {
					sero--;
					garo--;
				}

				// System.out.printf("sero,garo = %d,%d (%d)%n", sero, garo, val + 1);
				snails[sero][garo] = ++val;
			}
		}
		// System.out.println("Arrays.deepToString(snails) = " + Arrays.deepToString(snails));

		int idx = 0;
		for (int i = 0; i < N; i++) {
			System.out.print(" ".repeat(N - i - 1));
			for (int j = 0; j <= i; j++) {
				System.out.printf("%3d", snails[i][j]);
				results[idx++] = snails[i][j];
			}
			System.out.println();
		}

		return results;
	}
}

