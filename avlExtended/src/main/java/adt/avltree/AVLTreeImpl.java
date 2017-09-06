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
	
	@Override
    public void remove(T element) {
        if (element == null || isEmpty())
            return;

        BSTNode<T> node = search(element);

        if (node.isEmpty())
            return;

        super.remove(element);
        rebalanceUp((BSTNode<T>) node.getParent());
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
  		int i = calculateBalance(node);
  		if (i < -1){
  			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0){
  				leftRotation((BSTNode<T>) node.getLeft());
  			}
  			rightRotation(node);
  		}else if (i > 1){
  			if (calculateBalance((BSTNode<T>) node.getRight()) < 0){
  				rightRotation((BSTNode<T>) node.getRight());
  			}
  			leftRotation(node);
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
	public void rightRotation(BSTNode<T> node){
  		BSTNode<T> newNode = Util.rightRotation(node);
  		if (newNode.getParent() == null){
  			this.root = newNode;
  		}
  	}
 
  	public void leftRotation(BSTNode<T> node){
  		BSTNode<T> newNode = Util.leftRotation(node);
  		if (newNode.getParent() == null){
  			this.root = newNode;
  		}
  	}
}
