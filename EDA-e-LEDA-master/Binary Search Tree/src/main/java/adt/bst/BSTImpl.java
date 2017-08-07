package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.getRoot());
	}

	private int height(BSTNode<T> node) {
		if(isEmpty() || node.isEmpty()){ return -1;}
		return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if(node.isEmpty()){
			return node;
		}
		else if(node.getData().equals(element)){return node;}
		else{
			if(element.compareTo(node.getData()) < 0){
				return search((BSTNode<T>) node.getLeft(), element);
			}
			else{
				return search((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public void insert(T element) {
		insert(this.getRoot(), element);
	}

	private void insert(BSTNode<T> node, T element) {
		if(node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}
		else{
			if(element.compareTo(node.getData()) < 0){
				insert((BSTNode<T>) node.getLeft(), element);
			}
			else{
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
		
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if(node.isEmpty()){
			return null;
		}
		else if(node.getRight().isEmpty()){
			return node;
		}
		else{
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if(node.isEmpty()){
			return null;
		}
		else if(node.getLeft().isEmpty()){
			return node;
		}
		else{
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if(element == null){return null;}
		BSTNode<T> node = this.search(element);
		if(node.isEmpty()){ return null; }
		return sucessor(node);
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> result = minimum((BSTNode<T>) node.getRight());
		if(result != null){
			return result;
		}
		else{
			result = (BSTNode<T>) node.getParent();
			while(result != null && result.getData().compareTo(node.getData()) < 0){
				result = (BSTNode<T>) result.getParent();
			}
			return result;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if(element == null){return null;}
		BSTNode<T> node = this.search(element);
		if(node.isEmpty()){return null;}
		return predecessor(node);
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> result = maximum((BSTNode<T>) node.getLeft());

		if (result != null) {
			return result;
		} else {
			result = (BSTNode<T>) node.getParent();
			while (result != null && result.getData().compareTo(node.getData()) > 0) {
				result = (BSTNode<T>) result.getParent();
			}
			return result;
		}
	}

	@Override
	public void remove(T element) {
		if(element == null){ return;}
		BSTNode<T> node = search(element);
		if(!node.isEmpty()){remove(node);}
	}



	private void remove(BSTNode<T> node) {
		if(node.getLeft().isEmpty() && node.getRight().isEmpty()){
			node.setData(null);
		}
		else if(node.getRight().isEmpty() || node.getLeft().isEmpty()){ /*se tem filho na esquerda ou na direita e sao vazios*/
			if(node.equals(getRoot())){
				if(!node.getRight().isEmpty()){
					root = (BSTNode<T>) node.getRight();
				}
				else{
					root = (BSTNode<T>) node.getLeft();
				}
			}
			else{
				if(node.equals(node.getParent().getLeft())){
					if(!node.getRight().isEmpty()){
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
					}
					else{
						node.getParent().setLeft(node.getLeft());
						node.getLeft().setParent(node.getParent());
					}
				}
				else{
					if(!node.getRight().isEmpty()){
						node.getParent().setRight(node.getRight());
						node.getRight().setParent(node.getParent());
					}
					else{
						node.getParent().setRight(node.getLeft());
						node.getLeft().setParent(node.getParent());
					}
				}
			}
		}
		else{
			BSTNode<T> sucessor = sucessor(node.getData());
			T sucessorElement = sucessor.getData();
			remove(sucessor);
			node.setData(sucessorElement);
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(getRoot(), array, 0);
		return array;
	}

	private int preOrder(BSTNode<T> node, T[] array, int index) {
		if(!node.isEmpty()){
			array[index++] = node.getData();
			index = preOrder((BSTNode<T>) node.getLeft(),array, index);
			index = preOrder((BSTNode<T>) node.getRight(),array, index);
		}
		return index;
		
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(getRoot(), array, 0);
		return array;
	}

	private int order(BSTNode<T> node, T[] array, int index) {
		if(!node.isEmpty()){
			index = order((BSTNode<T>) node.getLeft(), array, index);
			array[index++] = node.getData();
			index = order((BSTNode<T>) node.getRight(), array, index);
		}
		return index;
		
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(getRoot(), array, 0);
		return array;
	}

	private int postOrder(BSTNode<T> node, T[] array, int index) {
		if(!node.isEmpty()){
			postOrder((BSTNode<T>) node.getLeft(), array, index);
			postOrder((BSTNode<T>) node.getRight(), array, index);
			array[index++] = node.getData();
		}
		return index;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
