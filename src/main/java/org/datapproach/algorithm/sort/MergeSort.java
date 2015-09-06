package org.datapproach.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * In computer science, merge sort or mergesort is a sorting algorithm for
 * rearranging lists (or any other data structure that can only be accessed
 * sequentially, e.g. file streams) into a specified order. It is a particularly
 * good example of the divide and conquer algorithmic paradigm. It is a
 * comparison sort.
 * 
 * Conceptually, merge sort works as follows:
 * 
 * Divide the unsorted list into two sublists of about half the size Sort each
 * of the two sublists Merge the two sorted sublists back into one sorted list.
 * The algorithm was invented by John von Neumann in 1945.
 * 
 * MergeSort is a divide-and-conquer algorithm.
 * 
 * The average and worst of Big-O time complexity of MergeSort is Nlog2(N).
 * 
 * This uses a additional memory of size N.
 * 
 * It is better to switch to insertion sort from merge sort,
 *  if number of elements is less than 7 0r 8
 * 
 * @author sarath
 */
public class MergeSort {
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
			MergeSort.mergesort(array);
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
	 * Mergesort algorithm.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static void mergesort(Comparable[] a) {
		Comparable[] tmpArray = new Comparable[a.length];
		mergesort(a, tmpArray, 0, a.length - 1);
	}

	
	/**
	 * Internal method that makes recursive calls.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	private static void mergesort(Comparable[] a, Comparable[] tmpArray,
			int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergesort(a, tmpArray, left, center);
			mergesort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param leftPos
	 *            the left-most index of the subarray.
	 * @param rightPos
	 *            the index of the start of the second half.
	 * @param rightEnd
	 *            the right-most index of the subarray.
	 */
	private static void merge(Comparable[] a, Comparable[] tmpArray,
			int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos].compareTo(a[rightPos]) <= 0)
				tmpArray[tmpPos++] = a[leftPos++];
			else
				tmpArray[tmpPos++] = a[rightPos++];

		while (leftPos <= leftEnd)
			// Copy rest of first half
			tmpArray[tmpPos++] = a[leftPos++];

		while (rightPos <= rightEnd)
			// Copy rest of right half
			tmpArray[tmpPos++] = a[rightPos++];

		// Copy tmpArray back
		for (int i = 0; i < numElements; i++, rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}
	
}
