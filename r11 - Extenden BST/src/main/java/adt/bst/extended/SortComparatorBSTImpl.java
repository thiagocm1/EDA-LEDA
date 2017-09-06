package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		while(!this.getRoot().isEmpty()){
			this.remove(this.getRoot().getData());
		}
		for (int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
		return this.order();
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		int index = 0;
		reverseOrder(getRoot(), array, index);
		return array;
	}

	private int reverseOrder(BSTNode<T> node, T[] array, int index) {
		if(!node.isEmpty()){
			index = reverseOrder((BSTNode<T>) node.getRight(), array, index);
			array[index++] = node.getData();
			index = reverseOrder((BSTNode<T>) node.getLeft(), array, index);
		}
		return index;
		
	}
	
	@Override 
	public BSTNode<T> search(T element){
		return search(this.getRoot(), element);
	}
	
	public BSTNode<T> search(BSTNode<T> node, T element){
		if(node.isEmpty() || element == null) {
			return null;
		}
		else if(node.isEmpty() || getComparator().compare(node.getData(), element) == 0){
			return node;
		}
		else if(getComparator().compare(node.getData(), element) > 0){
			return search((BSTNode<T>) node.getLeft(), element);
		}
		else{
			return search((BSTNode<T>) node.getRight(), element);
		}
	}
	
	@Override
	public void insert(T element){
		if(element == null){return;}
		else{
			insert(this.getRoot(), element);
		}
	}
	
	private void insert(BSTNode<T> node,T element){
		if(node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}
		else{
			if(getComparator().compare(node.getData(), element) > 0){
				insert((BSTNode<T>) node.getRight(), element);
			}
			else if(getComparator().compare(node.getData(), element) < 0){
				insert((BSTNode<T>) node.getLeft(), element);
			}
		}
	}
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
