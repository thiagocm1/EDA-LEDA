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

	private static final int INITIAL_SIZE = 20;
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
		if(position < 0 || position >= index){return;}
		int current = position;
		if(left(position) <= index 	&& getComparator().compare(heap[left(position)], heap[current]) > 0){ /*Se os filhos da esquerda sao maiores 
		que a posicao atual*/
			current = left(position);
		}
		if(right(position) <= index && getComparator().compare(heap[right(position)], heap[current]) > 0){
			/*
			 * Se os filhos da diretia sao maiores q a posicao atual;*/
			current = right(position);
		}
		/*Vai sobindo o elemento*/
		if(current != position){
			Util.swap(getHeap(), current, position);
			heapify(current);
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		if(element == null){return;}
		else{
			heap[++index] = element; /*Incrementa no array*/
			int i = index;
			/*Sobe ate achar sua pos*/
			while( i > 0 && getComparator().compare(heap[i], heap[parent(i)]) > 0){
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
	/*Pega o elemento do topo, troca e vai descendo ate ser removido*/
	@Override
	public T extractRootElement() {
		T result;
		if(isEmpty()){ result = null;}
		result = rootElement();
		Util.swap(getHeap(),0, this.index);
		this.index --;
		heapify(0);
		return result;
	}

	@Override
	public T rootElement() {
		T result;
		if(isEmpty()){result = null;}
		else{
			result = heap[0];
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
