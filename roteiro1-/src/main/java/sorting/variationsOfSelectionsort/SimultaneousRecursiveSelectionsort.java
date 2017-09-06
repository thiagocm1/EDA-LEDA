package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;
import util.Util;
/**
 * Este algoritmo eh RECURSIVO e aplica o selectionsort na entrada selectionando
 * o menor e o maior elemento a cada iteração e colocando eles nas posições
 * corretas. Nas proximas iterações o espaço de trabalho do algoritmo deve
 * excluir as posiçoes dos elementos das iterações anteriores.
 */
public class SimultaneousRecursiveSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		// TODO Auto-generated method stub
		// CORE
		if (leftIndex >= rightIndex) {
			return;
		}
		
		int minIndex = leftIndex;
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i].compareTo(array[minIndex]) < 0) {
				minIndex = i;
			}

		}
		
	Util.swap(array, leftIndex, minIndex);
	sort(array, leftIndex + 1, rightIndex );	
	}
}
