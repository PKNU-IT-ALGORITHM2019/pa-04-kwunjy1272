package pa04;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class sort {
	static int n;
	static int[] arr;
	static double[][] time = new double[3][7];

	static public void main(String[] args) {
		String[] sort = { "heap" ,"library"};
		System.out.println("           Random1000 Reverse1000 Random10000 Reverse10000 Random100000 Reverse100000");
		print();
		
		for (int i = 0; i < 2; i++) {
			System.out.print(sort[i] + "   ");
			for (int j = 0; j < 6; j++) {
				System.out.print(time[i][j] + "s 	");
			}
			System.out.println("");
		}
	}

	public static void init(int x) {
		if (x == 0 || x == 1)
			n = 1000;
		else if (x == 2 || x == 3)
			n = 10000;
		else
			n = 100000;
		Random ran = new Random();
		arr = new int[n];
		if (x % 2 == 1) {
			for (int i = 0; i < n; i++) {
				arr[i] = n - i;
			}
		} else {
			for (int i = 0; i < n; i++)
				arr[i] = ran.nextInt(n);
		}
	}

	public static void print() {
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					buildHeap();
				}
			} else {
				init(j);
				buildHeap();
			}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			time[0][j] = t;
		}
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					Arrays.sort(arr);
				}
			} else {
				init(j);
				Arrays.sort(arr);
			}
			long end = System.currentTimeMillis();
			double t = (end - start) / 1000.0;
			time[1][j] = t;
		}
		
	}

	public static void maxHeapify(int index) {
		if (index * 2 > arr.length)
			return;
		else {
			int k = index * 2 + 1;
			if (arr[index] >= arr[k])
				return;
			swap(index, k);
			maxHeapify(k);
		}
	}
	public static void buildHeap() {
		for (int i = 0; i < (n / 2); i++) {
			maxHeapify(i);
		}
	}
	public static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
