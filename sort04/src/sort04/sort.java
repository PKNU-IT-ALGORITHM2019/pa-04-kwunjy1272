package sort04;

import java.util.Arrays;
import java.util.Random;

public class sort {
	static int n;
	static int[] arr;
	static int[] A;
	static double[][] time = new double[2][7];

	static public void main(String[] args) {
		String[] sort = { "Heap   ", "Library" };
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
		arr = new int[n + 1];

		if (x % 2 == 1) {
			for (int i = 1; i <= n; i++) {
				arr[i] = n - i + 1;
			}
		} else if (x % 2 == 0) {
			for (int i = 1; i <= n; i++) {
				arr[i] = ran.nextInt(n) + 1;
			}
		}
	}

	public static void print() {
		for (int j = 0; j < 6; j++) {
			long start = System.currentTimeMillis();
			if (j % 2 == 0) {
				for (int k = 0; k < 10; k++) {
					init(j);
					heapSort();
					for (int q = 1; q < 10; q++) {
						System.out.print(arr[q] + " ");
					}
					System.out.println("");
				}
			} else {
				init(j);
				heapSort();
				for (int q = 1; q < 10; q++) {
					System.out.print(arr[q] + " ");
				}
				System.out.println("");
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

	public static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static void maxHeapify(int i, int heapSize) {

		int k = 2 * i;
		if (2 * i > heapSize)
			return;

		if (heapSize >= i * 2 + 1) {
			if (arr[2 * i] < arr[2 * i + 1])
				k = 2 * i + 1;
		}
		if (arr[i] >= arr[k])
			return;
		swap(i, k);
		maxHeapify(k, heapSize);
	}

	public static void buildHeap(int heapSize) {

		for (int i = (heapSize / 2); i > 0; i--) {
			maxHeapify(i, heapSize);
		}
	}

	public static void heapSort() {
		int heapSize = arr.length - 1;
		buildHeap(heapSize);
		for (int i = heapSize; i > 1; i--) {
			swap(i, 1);
			heapSize -= 1;
			maxHeapify(1, heapSize);
		}
	}
}
