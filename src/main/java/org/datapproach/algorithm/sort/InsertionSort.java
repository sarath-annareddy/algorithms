package org.datapproach.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Insertion sort is a simple sorting algorithm, a comparison sort in which the
 * sorted array (or list) is built one entry at a time. It is much less
 * efficient on large lists than the more advanced algorithms such as quick sort,
 * heap sort, or merge sort, but it has various advantages:
 * 
 * Simple to implement Efficient on (quite) small data sets Efficient on data
 * sets which are already substantially sorted More efficient in practice than
 * most other simple O(n2) algorithms such as selection sort or bubble sort: the
 * average time is n2/4 and it is linear in the best case Stable (does not
 * change the relative order of elements with equal keys) In-place (only
 * requires a constant amount O(1) of extra memory space) It is an online
 * algorithm, in that it can sort a list as it receives it. In abstract terms,
 * each iteration of an insertion sort removes an element from the input data,
 * inserting it at the correct position in the already sorted list, until no
 * elements are left in the input. The choice of which element to remove from
 * the input is arbitrary and can be made using almost any choice algorithm.
 * 
 * This is also called Bubble Sort.
 * 
 * @author sarath
 *
 */
public class InsertionSort {

	private static final Random RAND = new Random(42); // random number

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int LENGTH = 1000; // initial length of array to sort
		int RUNS = 16; // how many times to grow by 2?

		System.out.println("");

		for (int i = 1; i <= RUNS; i++) {
			Integer[] array = createRandomArray(LENGTH);

			// System.out.println("Array("+LENGTH+") elements before sort: ");
			// // Print first 10 elements
			// printIntArray(array);

			// run the algorithm and time how long it takes
			long startTime1 = System.currentTimeMillis();
			InsertionSort.insertionSort(array);
			long endTime1 = System.currentTimeMillis();

			// System.out.println("Array("+LENGTH+") elements after sort: ");
			// // Print first 10 elements
			// printIntArray(array);

			if (!isSorted(array)) {
				throw new RuntimeException("not sorted afterward: "
						+ Arrays.toString(array));
			}

			System.out.printf("Time taken for %10d elements  =>  %6d ms \n",
					LENGTH, endTime1 - startTime1);
			System.out.println("");

			LENGTH *= 2; // double size of array for next time
		}
	}

	// Returns true if the given array is in sorted ascending order.
	public static boolean isSorted(Integer[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}

	// Creates an array of the given length, fills it with random
	// non-negative integers, and returns it.
	public static Integer[] createRandomArray(int length) {
		Integer[] a = new Integer[length];
		for (int i = 0; i < a.length; i++) {
			a[i] = RAND.nextInt(1000000);
			// a[i] = RAND.nextInt(40);
		}
		return a;
	}

	// Beginning of method void printIntArray(int[])

	public static void printIntArray(int[] array) {

		System.out.print("{");
		// for (int i = 0; i < array.length - 1; i++)
		for (int i = 0; i < 10 - 1; i++)
			System.out.print(array[i] + ", ");
		System.out.println(array[array.length - 1] + "}");

	} // E
	
	/**
	 * Simple insertion sort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static void insertionSort(Comparable[] a) {
		for (int p = 1; p < a.length; p++) {
			Comparable tmp = a[p];
			int j = p;

			for (; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}
}
