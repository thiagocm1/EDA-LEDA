package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setRight(pivot.getRight());
		pivot.getRight().setParent(node);
		pivot.setParent(node.getParent());
		
		if(node.getParent() != null){
			if(node.getParent().getData().compareTo(pivot.getData()) > 0){
				node.getParent().setLeft(pivot);
			}
			else{
				node.getParent().setRight(pivot);
			}
		}
		node.setParent(pivot);
		pivot.setLeft(node);
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getLeft());
		pivot.getRight().setParent(node);
		node.setParent(node.getParent());
		if(node.getParent() != null){
			if(node.getParent().getData().compareTo(pivot.getData()) < 0){
				node.getParent().setRight(pivot);
			}
			else{
				node.getParent().setLeft(pivot);
			}
		}
		node.setParent(pivot);
		pivot.setRight(node);
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
