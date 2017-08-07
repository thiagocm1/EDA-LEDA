package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

   // TODO Do not forget: you must override the methods insert and remove
   // conveniently.

    @Override
   public void remove(T element) {
	   if (element == null || isEmpty())
           return;

       BSTNode<T> node = search(element);

       if (node.isEmpty())
           return;

       super.remove(element);
       rebalanceUp((BSTNode<T>) node.getParent());}
   

   @Override
   public void insert(T element) {
	   super.insert(element);
 		BSTNode<T> node = search(element);
 		rebalanceUp(node);
 	}
   

   // AUXILIARY
   protected int calculateBalance(BSTNode<T> node) {
	   if (node == null || node.isEmpty()) return 0;
 		return height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft());
   }

   // AUXILIARY
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

 	// AUXILIARY
 	protected void rebalanceUp(BSTNode<T> node) {
 		if (node == null || node.isEmpty()) return;
 		rebalance(node);
 		if (node.getParent() != null)
 			rebalanceUp((BSTNode<T>) node.getParent());
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
