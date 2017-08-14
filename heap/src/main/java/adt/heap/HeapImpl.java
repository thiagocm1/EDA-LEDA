package adt.heap;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	protected static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] resp = Util.makeArrayOfComparable(index + 1);
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		if(position < 0 || position >= size()){return;}
		int left = left(position);
		int right = right(position);
		int current = position;
		if(left <= index && getComparator().compare(heap[left], heap[current]) > 0){
			current = left;
		}
		if(right < index && getComparator().compare(heap[right], heap[current]) > 0){
			current = right;
		}
		if(current != position){
			Util.swap(getHeap(), current, position);
			heapify(current);
		}
	}
	
	private void shiftUp(int position){
		if(position < 0 || position >= size()){
			return;
		}
		int parent = (int) heap[parent(position)];
		int current = (int) heap[position];
		while(position > 0  && getComparator().compare(heap[parent(position)], heap[position]) < 0){
			Util.swap(getHeap(), parent, current);
			position = parent(position);
		}
	}
	
	public void changePriority(int position, T newPosition){
		if(position < 0 || position >= size()){return;}
		if(newPosition == null ){ return;}
		T oldPosition =  heap[position];
		heap[position] = newPosition;
		if(getComparator().compare(newPosition, oldPosition) > 0){
			shiftUp(position);
		}
		else{
			heapify(position);
		}
	}
	

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		// TODO Implemente a insercao na heap aqui.
		if(element == null){return;}
		else{
			this.heap[++index] = element;
			int i = index;
			
			while(i > 0 && getComparator().compare(heap[parent(i)], heap[i]) < 0){
				Util.swap(getHeap(), i, parent(i));
				i = parent(i);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if(array == null){return;}
		else{
			this.heap = array;
			this.index = array.length - 1;
			for (int i = this.heap.length - 1; i >= 0; i--) {
				heapify(i);
			}
		}	
	}
	


	@Override
	public T extractRootElement() {
		T result;
		if(isEmpty()){result = null;}
		else{
			result = rootElement();
			Util.swap(getHeap(), 0, this.index);
			this.index --;
			heapify(0);
		}
		return result;
	}

	@Override
	public T rootElement() {
		T result;
		if(isEmpty()){result = null;}
		else{
			result = this.heap[0];
		}
		return result;
	}

	@Override
	public T[] heapsort(T[] array) {
		if(array == null){return null;}
		Comparator<T> comparator = this.comparator;
		this.comparator = (a,b) -> b.compareTo(a);
		this.index = -1;
		
		for (int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
		
		T[] sorted = (T[]) new Comparable[this.size()];
		for (int i = 0; i < sorted.length; i++) {
			sorted[i] = this.extractRootElement();
		}
		this.comparator = comparator;
		return sorted;
	}
	
	public T[] merge(T[] arrayA, T[] arrayB){
		/*Forma 1
		T[] result; 
		for (int i = 0; i < arrayA.length; i++) {
			this.insert(arrayA[i]);
		}
		for (int i = 0; i < arrayB.length; i++) {
			this.insert(arrayB[i]);
		}
		heapsort(getHeap());
		result = heapsort(getHeap());
		
		//result = heapsort(result);
		*/
		//2 FORMA;
		int size = arrayA.length + arrayB.length;
		T[] result = (T[]) new Comparable[size];
		for (int i = 0; i < arrayA.length; i++) {
			result[i] = arrayA[i];
		}
		int sizeA = arrayA.length;
		for (int i = 0; i < arrayB.length; i++) {
			result[sizeA + i] = arrayB[i];
		}
		buildHeap(result);
		heapsort(result);
		return result;
	} 
	/*
		T[] array;
		for (int i = 0; i < arrayA.length; i++) {
			this.insert(arrayA[i]);
		}
		for (int i = 0; i < arrayB.length; i++) {
			this.insert(arrayB[i]);
		}
		array = heapsort(getHeap());
		buildHeap(array);
		return array;
		
		
	}		/*T[] result; 
		if((arrayA.length < 0 && arrayA == null) && ( arrayB.length < 0 && arrayB == null)){
			result = null;
		}
		int length = arrayA.length + arrayB.length;
		result = (T[]) new Comparable[length];

		for (int i = 0; i < arrayA.length; i++) {
			result[i] = arrayA[i];
		}
		int lengthA = arrayA.length;
		for (int i = 0; i < arrayB.length; i++) {
			result[lengthA + i] = arrayB[i];
		}
		result = heapsort(result);
		
		for (int i = (length/2-1); i >= 0; i--) {
			heapify((int) result[i]);
		}
		
		return result;
	} 
	*/
	
	
	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
