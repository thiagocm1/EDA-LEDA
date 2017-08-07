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
	@Override
	public void insert(T element){
		if(element == null){return;}
		BSTNode<T> result = this.search(element);
		if(result == null){return;}
		super.insert(element);
		rebalance(result);
	}
	
	@Override
	public void remove(T element){
		if (element == null || isEmpty())
            return;

        BSTNode<T> node = this.search(element);

        if (node.isEmpty()){
            return;
        }

        super.remove(element);
        rebalanceUp((BSTNode<T>) node.getParent());
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result;
  		if (node == null || node.isEmpty()){ 
  			result = 0;
  		}
  		else{
  		result = height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft());
  		}
  		return result;
	}
 
  	// AUXILIARY
  	protected void rebalance(BSTNode<T> node) {
  		int balanceFactor = calculateBalance(node);
  		if (balanceFactor < -1){
  			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0){
  				leftRotation((BSTNode<T>) node.getLeft());
  			}
  			rightRotation(node);
  		}else if (balanceFactor > 1){
  			if (calculateBalance((BSTNode<T>) node.getRight()) < 0){
  				rightRotation((BSTNode<T>) node.getRight());
  			}
  			leftRotation(node);
  		}
 
  	}
 
  	// AUXILIARY
  	protected void rebalanceUp(BSTNode<T> node) {
  		if (node == null || node.isEmpty()) return;
  		rebalance(node);
  		if (node.getParent() != null){
  			rebalanceUp((BSTNode<T>) node.getParent());
  		}
  	}
 
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
