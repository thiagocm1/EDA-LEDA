package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      return blackHeight((RBNode<T>) getRoot());
   }

   private int blackHeight(RBNode<T> node) {
      int result;
      if (node.isEmpty() || node == null) {
         result = 1;
      } else if (node.equals((RBNode<T>) getRoot()) || node.getColour().equals(getColourRed())) {
         result = Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
      } else {
         result = 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
      }
      return result;
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildrenOfRedNodes((RBNode<T>) this.getRoot());
   }

   private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
      boolean result;
      if (node.isEmpty()) {
         result = true;
      } else if (node.getColour().equals(getColourRed())
            && ((RBNode<T>) node.getLeft()).getColour().equals(getColourRed())) {
         result = false;
      } else if (node.getColour().equals(getColourRed())
            && ((RBNode<T>) node.getRight()).getColour().equals(getColourRed())) {
         result = false;
      } else {
         result = verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
               && verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
      }
      return result;
   }

   /**
    * Verifies the black-height property from the root. The method blackHeight
    * returns an exception if the black heights are different.
    */
   private boolean verifyBlackHeight() {
      return verifyBlackHeight((RBNode<T>) this.getRoot());
   }

   private boolean verifyBlackHeight(RBNode<T> node) {
      boolean result;
      if (node.isEmpty()) {
         result = true;
      } else if (blackHeight((RBNode<T>) node.getLeft()) == blackHeight((RBNode<T>) node.getRight())) {
         result = verifyBlackHeight((RBNode<T>) node.getLeft()) && verifyBlackHeight((RBNode<T>) node.getRight());
      } else {
         result = false;
      }
      return result;
   }

   @Override
   public void insert(T value) {
      if (value == null) {
         return;
      } else {
         insert((RBNode<T>) this.getRoot(), value);
      }
   }

   public void insert(RBNode<T> node, T element) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setColour(getColourRed());
         node.setLeft(new RBNode<T>());
         node.setRight(new RBNode<T>());
         node.getLeft().setParent(node);
         node.getRight().setParent(node);
         fixUpCase1(node);
      } else {
         if (element.compareTo(node.getData()) < 0)
            insert((RBNode<T>) node.getLeft(), element);
         else if (element.compareTo(node.getData()) > 0)
            insert((RBNode<T>) node.getRight(), element);
      }
   }

   @Override
   public RBNode<T>[] rbPreOrder() {
      RBNode<T>[] array = new RBNode[this.size()];
      int index = 0;
      rbPreOrder(array, 0, (RBNode<T>) this.getRoot());
      return array;
   }

   private int rbPreOrder(RBNode<T>[] array, int index, RBNode<T> node) {
      if (!node.isEmpty()) {
         array[index++] = node;
         index = rbPreOrder(array, index, (RBNode<T>) node.getLeft());
         index = rbPreOrder(array, index, (RBNode<T>) node.getRight());
      }
      return index;

   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node.equals((RBNode<T>) this.getRoot())) {
         node.setColour(getColourBlack());
      } else {
         fixUpCase2(node);
      }
   }

   protected void fixUpCase2(RBNode<T> node) {
      if (((RBNode<T>) node.getParent()).getColour().equals(getColourBlack())) {
         ;
      } else {
         fixUpCase3(node);
      }
   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandParent = (RBNode<T>) parent.getParent();
      RBNode<T> uncle = null;
      if (parent.equals(grandParent.getLeft())) {
         uncle = (RBNode<T>) grandParent.getRight();
      } else {
         uncle = (RBNode<T>) grandParent.getLeft();
      }
      if (uncle.getColour().equals(getColourRed())) {
         parent.setColour(getColourBlack());
         uncle.setColour(getColourBlack());
         grandParent.setColour(getColourRed());
         fixUpCase1(grandParent);
      } else {
         fixUpCase4(node);
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> nextNode = node;
      RBNode<T> parent = (RBNode<T>) node.getParent();
      if (node.equals(parent.getRight()) && parent.equals(parent.getParent().getLeft())) {
         leftRotation(parent);
         nextNode = (RBNode<T>) node.getLeft();
      } else if (node.equals(parent.getLeft()) && parent.equals(parent.getParent().getRight())) {
         rightRotation(parent);
         nextNode = (RBNode<T>) node.getRight();
      }
      fixUpCase5(nextNode);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandParent = (RBNode<T>) parent.getParent();
      parent.setColour(getColourBlack());
      grandParent.setColour(getColourRed());
      if (node.equals(parent.getLeft())) {
         rightRotation(grandParent);
      } else {
         leftRotation(grandParent);
      }

   }

   private Colour getColourRed() {
      return Colour.RED;
   }

   private Colour getColourBlack() {
      return Colour.BLACK;
   }

   public void leftRotation(RBNode<T> node) {
      RBNode<T> newNode = (RBNode<T>) Util.leftRotation(node);
      if (newNode.getParent() == null) {
         this.root = newNode;
      }
   }

   public void rightRotation(RBNode<T> node) {
      RBNode<T> newNode = (RBNode<T>) Util.rightRotation(node);
      if (newNode.getParent() == null) {
         this.root = newNode;
      }
   }
   

   public int countRedNodes(){
	   return countRedNodes((RBNode<T>)getRoot());
   }
   
   private int countRedNodes(RBNode<T> node) {
	   int result = 0;
	   if(node.isEmpty()){
		   result = 0;
	   }
	   else{
		   if(node.getColour().equals(getColourRed())){
			   result ++;
		   }
		   result = result + countRedNodes((RBNode<T>) node.getLeft()) + countRedNodes((RBNode<T>) node.getRight());
	   }
	   return result;

   }
   
   public int countBlackNodes(){
	   return countBlackNodes((RBNode<T>) getRoot());
   }
   
   
   private int countBlackNodes(RBNode<T> node) {
	   int result = 0;
	   if(node.isEmpty()){
		   result = 0;
	   }
	   else{
		   if(node.getColour().equals(getColourBlack())){
			   result ++;
		   }
		 
		 result = result + countBlackNodes((RBNode<T>) node.getLeft()) + countBlackNodes((RBNode<T>) node.getRight());
	   }
	   return result;
   }
	 public RBNode[] blackNodes(){
	   int countBlackNode = countBlackNodes();
	   RBNode[] array = new RBNode[countBlackNode];
	   int index = 0;
	   blackNodes(array, (RBNode<T>) getRoot(), index);
	   return array;
   }

   private int blackNodes(RBNode[] array, RBNode<T> node, int index) {
	   if(!node.isEmpty()){
		   if(node.getColour().equals(getColourBlack())){
			   array[index++] = node;
		   }
		   index = blackNodes(array, (RBNode<T>) node.getLeft(), index);
		   index = blackNodes(array, (RBNode<T>) node.getRight(), index);
		
	   }
	   return index;
   }
   
   public RBNode[] redNodes(){
	   int countRedNode = countRedNodes();
	   RBNode[] array = new RBNode[countRedNode];
	   int index = 0;
	   redNodes(array, (RBNode<T>)getRoot(), index);
	   return array;
   }

   private int redNodes(RBNode[] array, RBNode<T> node, int index) {
	   if(!node.isEmpty()){
		   if(node.getColour().equals(getColourRed())){
			   array[index++] = node;
		   }
		   index = redNodes(array, (RBNode<T>) node.getLeft(), index);
		   index = redNodes(array, (RBNode<T>) node.getRight(), index);
	   }
	   return index;
   }
   
	
}
