package org.datapproach.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Heapsort is one of the best general-purpose sorting algorithms, a comparison
 * sort and part of the selection sort family. Although somewhat slower in
 * practice on most machines than a good implementation of quicksort, it has the
 * advantages of worst-case O(n log n) runtime and being an in-place algorithm.
 * Heapsort is not a stable sort.
 * 
 * http://www.java-tips.org/java-se-tips-100019/24-java-lang/1894-heap-sort-
 * implementation-in-java.html
 * 
 * 
 * The average and worst time complexity of HeapSort is Nlog2(N).
 * 
 * This uses inplace sorting.
 * 
 * It is better to switch to insertion sort from merge sort,
 *  if number of elements is less than 12
 * 
 * @author sarath
 *
 */
public class HeapSort {

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
			HeapSort.heapsort(array);
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
	 * Standard heapsort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static void heapsort(Comparable[] a) {
		for (int i = a.length / 2; i >= 0; i--)
			/* buildHeap */
			percDown(a, i, a.length);
		for (int i = a.length - 1; i > 0; i--) {
			swapReferences(a, 0, i); /* deleteMax */
			percDown(a, 0, i);
		}
	}

	/**
	 * Internal method for heapsort.
	 * 
	 * @param i
	 *            the index of an item in the heap.
	 * @return the index of the left child.
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * Internal method for heapsort that is used in deleteMax and buildHeap.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @index i the position from which to percolate down.
	 * @int n the logical size of the binary heap.
	 */
	private static void percDown(Comparable[] a, int i, int n) {
		int child;
		Comparable tmp;

		for (tmp = a[i]; leftChild(i) < n; i = child) {
			// Get the left child
			child = leftChild(i);
			// If left child is less than right child
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
				child++; // picking right child
			// Compare the parent with highest of child
			if (tmp.compareTo(a[child]) < 0)
				a[i] = a[child];
			else
				break;
		}
		a[i] = tmp;
	}

	/**
	 * Method to swap to elements in an array.
	 * 
	 * @param a
	 *            an array of objects.
	 * @param index1
	 *            the index of the first object.
	 * @param index2
	 *            the index of the second object.
	 */
	public static final void swapReferences(Object[] a, int index1, int index2) {
		Object tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

}
