package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// TODO Auto-generated method stub
		for (int i = leftIndex; i <= rightIndex; i++) {
			int lowerIndex = i;
			int j = i;

			while ((j > 0) && (array[j - 1].compareTo(array[j]) > 0)) {
				Util.swap(array, j - 1, j);
				j--;
			}

		}
	}
}