package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	protected void insert(T element, BTNode<T> node, BTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				this.insert(element, node.getLeft(), node);
			} else if (element.compareTo(node.getData()) > 0) {
				this.insert(element, node.getRight(), node);
			}
			rebalance((BSTNode<T>) node);
		}
	}
	
	public void remove(T element) {
		BSTNode<T> node = search(element);
		remove(node);
		if (node == this.root)
			this.rebalanceUp((BSTNode<T>) this.root);
		else
			this.rebalanceUp((BSTNode<T>) node.getParent());
	}
	
	/**
	 * Calculate node's balance.
	 * 
	 * @param node
	 * @return negative value, in case of the pending left; positive value, in
	 *         case of the pending right. Value 0, if (node == null || node.isEmpty())
	 */
	protected int calculateBalance(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return 0;
		return height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft());
	}

	// faz verificação
	protected void rebalance(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return ;
		int balance = calculateBalance(node);
		if (Math.abs(balance) <= 1) return ;
		if (balance < 0) { // left pending
			int sonBalance = calculateBalance((BSTNode<T>) node.getLeft());
			if (sonBalance > 0) { // if zig-zag
				this.leftRotation((BSTNode<T>) node.getLeft());
			}
			this.rightRotation(node);
		} else { // right peding
			int sonBalance = calculateBalance((BSTNode<T>) node.getRight());
			if (sonBalance < 0) { // if zig-zag
				this.rightRotation((BSTNode<T>) node.getRight());
			}
			this.leftRotation(node);
		}
	}

	// faz verificação completa, na árvore inteira. e.g. remove
	protected void rebalanceUp(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return ;
		while (!node.isEmpty()) {
			this.rebalance(node);
			node = (BSTNode<T>) node.getParent();
		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return ;
		if (node == this.root)
			this.root = (BSTNode<T>) node.getRight();
		BTNode<T> right = node.getRight();
		node.setRight(right.getLeft());
		node.getRight().setParent(node);
		right.setParent(node.getParent());
		if (node.getParent().getLeft() == node)
			node.getParent().setLeft(right);
		else
			node.getParent().setRight(right);
		right.setLeft(node);
		node.setParent(right);
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return ;
		if (node == this.root)
			this.root = (BSTNode<T>) node.getLeft();
		BTNode<T> left = node.getLeft();
		node.setLeft(left.getRight());
		node.getLeft().setParent(node);
		left.setParent(node.getParent());
		if (node.getParent().getLeft() == node)
			node.getParent().setLeft(left);
		else
			node.getParent().setRight(left);
		left.setRight(node);
		node.setParent(left);
	}
}
