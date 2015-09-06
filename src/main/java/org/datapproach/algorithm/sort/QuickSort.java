package org.datapproach.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Quicksort is a well-known sorting algorithm developed by C. A. R. Hoare that,
 * on average, makes Θ(n log n) comparisons to sort n items. However, in the
 * worst case, it makes Θ(n^2) comparisons. Typically, quicksort is
 * significantly faster in practice than other Θ(n log n) algorithms, because
 * its inner loop can be efficiently implemented on most architectures, and in
 * most real-world data it is possible to make design choices which minimize the
 * possibility of requiring quadratic time. Quicksort is a comparison sort and,
 * in efficient implementations, is not a stable sort.
 * 
 * Quicksort sorts by employing a divide and conquer strategy to divide a list
 * into two sub-lists.
 * 
 * The steps are:
 * 
 * Pick an element, called a pivot, from the list. Reorder the list so that all
 * elements which are less than the pivot come before the pivot and so that all
 * elements greater than the pivot come after it (equal values can go either
 * way). After this partitioning, the pivot is in its final position. This is
 * called the partition operation. Recursively sort the sub-list of lesser
 * elements and the sub-list of greater elements. The base case of the recursion
 * are lists of size zero or one, which are always sorted. The algorithm always
 * terminates because it puts at least one element in its final place on each
 * iteration.
 * 
 * Quicksort with median-of-three partitioning functions nearly the same as
 * normal quicksort with the only difference being how the pivot item is
 * selected. In normal quicksort the first element is automatically the pivot
 * item. This causes normal quicksort to function very inefficiently when
 * presented with an already sorted list. The divison will always end up
 * producing one sub-array with no elements and one with all the elements (minus
 * of course the pivot item). In quicksort with median-of-three partitioning the
 * pivot item is selected as the median between the first element, the last
 * element, and the middle element (decided using integer division of n/2). In
 * the cases of already sorted lists this should take the middle element as the
 * pivot thereby reducing the inefficency found in normal quicksort.
 * 
 * * QuickSort is a divide-and-conquer algorithm.
 * 
 * The average time complexity of QuickSort is Nlog2(N) and worst time complexity of QuickSort is N^2.
 * 
 * This uses inplace sorting.
 * 
 * It is better to switch to insertion sort from merge sort,
 *  if number of elements is less than 12
 * 
 * @author sarath
 *
 */
public class QuickSort {
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
			QuickSort.quicksort(array);
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
	 * Quicksort algorithm.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static void quicksort(Comparable[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static final int CUTOFF = 10;

	/**
	 * Internal quicksort method that makes recursive calls. Uses
	 * median-of-three partitioning and a cutoff of 10.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param low
	 *            the left-most index of the subarray.
	 * @param high
	 *            the right-most index of the subarray.
	 */
	private static void quicksort(Comparable[] a, int low, int high) {
		if (low + CUTOFF > high)
			insertionSort(a, low, high);
		else {
			// Sort low, middle, high
			int middle = (low + high) / 2;
			if (a[middle].compareTo(a[low]) < 0)
				swapReferences(a, low, middle);
			if (a[high].compareTo(a[low]) < 0)
				swapReferences(a, low, high);
			if (a[high].compareTo(a[middle]) < 0)
				swapReferences(a, middle, high);

			// Place pivot at position high - 1
			swapReferences(a, middle, high - 1);
			Comparable pivot = a[high - 1];

			// Begin partitioning
			int i, j;
			for (i = low, j = high - 1;;) {
				while (a[++i].compareTo(pivot) < 0)
					;
				while (pivot.compareTo(a[--j]) < 0)
					;
				if (i >= j)
					break;
				swapReferences(a, i, j);
			}

			// Restore pivot
			swapReferences(a, i, high - 1);

			quicksort(a, low, i - 1); // Sort small elements
			quicksort(a, i + 1, high); // Sort large elements
		}
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

	/**
	 * Internal insertion sort routine for subarrays that is used by quicksort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param low
	 *            the left-most index of the subarray.
	 * @param n
	 *            the number of items to sort.
	 */
	private static void insertionSort(Comparable[] a, int low, int high) {
		for (int p = low + 1; p <= high; p++) {
			Comparable tmp = a[p];
			int j;

			for (j = p; j > low && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}
}
