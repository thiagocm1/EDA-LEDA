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
		int result = 0;
		if (node.isEmpty()) {
			result = -1;
		} else {
			result = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null) {
			return null;
		} else {
			return search(this.getRoot(), element);
		}
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			return node;
		} else if (node.getData().equals(element)) {
			return node;
		} else {
			int compare = element.compareTo(node.getData());
			if (compare < 0) {
				return search((BSTNode<T>) node.getLeft(), element);
			} else {
				return search((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element == null) {
			return;
		} else {
			insert(this.getRoot(), element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			int compare = element.compareTo(node.getData());

			if (compare < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}

	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (element == null) {
			return null;
		}
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) {
			return null;
		} else {
			return sucessor(node);
		}

	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> result = this.minimum((BSTNode<T>) node.getRight());
		if (result != null) {
			return result;
		} else {
			result = (BSTNode<T>) node.getParent();
			while (result != null && result.getData().compareTo(node.getData()) < 0) {
				result = (BSTNode<T>) result.getParent();
			}
			return result;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element == null) {
			return null;
		}
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) {
			return null;
		} else {
			return predecessor(node);
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> result = this.maximum((BSTNode<T>) node.getLeft());
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
		if (element == null) {
			return;
		}
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
		} else if (node.getRight().isEmpty()
				|| node.getLeft().isEmpty()) { /*
												 * se tem filho na esquerda ou na
												 * direita e sao vazios
												 */
			if (node.equals(getRoot())) {
				if (!node.getRight().isEmpty()) {
					root = (BSTNode<T>) node.getRight();
				} else {
					root = (BSTNode<T>) node.getLeft();
				}
			} else {
				if (node.equals(node.getParent().getLeft())) {
					if (!node.getRight().isEmpty()) {
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
					} else {
						node.getParent().setLeft(node.getLeft());
						node.getLeft().setParent(node.getParent());
					}
				} else {
					if (!node.getRight().isEmpty()) {
						node.getParent().setRight(node.getRight());
						node.getRight().setParent(node.getParent());
					} else {
						node.getParent().setRight(node.getLeft());
						node.getLeft().setParent(node.getParent());
					}
				}
			}
		} else {
			BSTNode<T> sucessor = sucessor(node.getData());
			T sucessorElement = sucessor.getData();
			remove(sucessor);
			node.setData(sucessorElement);
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		int index = 0;
		preOrder(getRoot(), array, index);
		return array;
	}

	private int preOrder(BSTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			array[index++] = node.getData();
			index = preOrder((BSTNode<T>) node.getLeft(), array, index);
			index = preOrder((BSTNode<T>) node.getRight(), array, index);
		}
		return index;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		int index = 0;
		order(getRoot(), array, index);
		return array;
	}

	private int order(BSTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {

			index = order((BSTNode<T>) node.getLeft(), array, index);
			array[index++] = node.getData();
			index = order((BSTNode<T>) node.getRight(), array, index);
		}
		return index;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		int index = 0;
		postOrder(getRoot(), array, index);
		return array;
	}

	private int postOrder(BSTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			index = postOrder((BSTNode<T>) node.getLeft(), array, index);
			index = postOrder((BSTNode<T>) node.getRight(), array, index);
			array[index++] = node.getData();
		}
		return index;
	}
	
	public boolean isDecedent(T parent, T descent){
		return isDecedent(this.search(parent), descent);
	}
	
	private boolean isDecedent(BTNode<T> node, T descent) {
		if (node.isEmpty()) {
			return false;
		} else if (node.getData().compareTo(descent) > 0) {
			return isDecedent(node.getLeft(), descent);
		} else if (node.getData().compareTo(descent) < 0) {
			return isDecedent(node.getRight(), descent);
		}
		else{
			return true;
		}
	}
		
		/*
		if(node.isEmpty()){ return false;}
		
		else if(descent.compareTo(node.getData()) > 0){
			return isDecedent( node.getRight(), descent);
		}
		else if(descent.compareTo(node.getData()) < 0){
			return isDecedent(node.getLeft(), descent);
		}
		else{
			return true;
		}
		*/
	}
	
		public boolean isBST(){
		BSTNode<T> leftNode = null;
		
		return isBST(this.getRoot(), leftNode);
	}

	
	private boolean isBST(BSTNode<T> node, BSTNode<T> previous) {
		if(node.isEmpty()){return true;}
		else if(!isBST((BSTNode<T>) node.getLeft(), previous)){return false;}
		else if(previous != null && previous.getData().compareTo(node.getData()) > 0){return false;}
		previous = node;
		return isBST((BSTNode<T>) node.getRight(), previous);
	}

	

 
	public	T orderStatistic(int k){
		if(k < 0 || k > size()){ return null; }
		return orderStatistic(getRoot(), k - 1);
	}


	private T orderStatistic(BSTNode<T> node, int k) {
			if(node.isEmpty()){return null;}
			int count = size((BSTNode<T>) node.getLeft());
			if(count > k){ return orderStatistic((BSTNode<T>) node.getLeft(), k);}
			else if(count < k){return orderStatistic((BSTNode<T>) node.getRight(), k - count - 1 );}
			else{
				return node.getData();
			}
		
	}

	public int distance(T nodeParent, T nodeDescedent){
		int index = 0;
		return distance(this.search(nodeParent), nodeDescedent, index);
	}
	
	private int distance(BSTNode<T> node, T value, int index) {
		if(node.isEmpty()){return -1;}
		else if(node.getData().equals(value)){return index;}
		else if(node.getData().compareTo(value) > 0){
			return distance((BSTNode<T>) node.getLeft(), value, ++index);
		}
		else{
			return distance((BSTNode<T>) node.getRight(), value, ++index);
		}
		
	}
	
public int countLeaves(){
		return countLeaves(this.getRoot());
	}


	private int countLeaves(BSTNode<T> node) {
		int result = 0;
		if(node.isEmpty()){result =  0;}
		else if(node.getLeft().isEmpty() && node.getRight().isEmpty()){result = 1; }
		else{
			result = countLeaves((BSTNode<T>) node.getLeft()) + countLeaves((BSTNode<T>) node.getRight());
		}
		return result;
		
	}
	
	public boolean isSameStructure(BSTImpl<T> tree1, BSTImpl<T> tree2){
		return isSameStructure(tree1.getRoot(), tree2.getRoot());
	}

	private boolean isSameStructure(BTNode<T> root1, BTNode<T> root2) {
		boolean result = false;
		if(root1.isEmpty() && root2.isEmpty()){ result = true;}
		else if(!root1.isEmpty() && !root2.isEmpty()){
			result = isSameStructure(root1.getLeft(), root2.getLeft()) && isSameStructure(root1.getRight(), root2.getRight());
		}
		return result;
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
