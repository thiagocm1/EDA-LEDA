package adt.btree;

import java.util.ArrayList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

   protected BNode<T> root;
   protected int order;

   public BTreeImpl(int order) {
      this.order = order;
      this.root = new BNode<T>(order);
   }

   @Override
   public BNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return this.root.isEmpty();
   }

   @Override
   public int height() {
      if (!root.isEmpty())
         return height(this.root);
      return -1;
   }

   private int height(BNode<T> node) {

      int height = 0;
      if (!node.isEmpty())
         if (!node.isLeaf())
            height += 1 + height(node.getChildren().getFirst());

      return height;

   }

   @Override
   public BNode<T>[] depthLeftOrder() {
      if (isEmpty())
         return new BNode[0];

      ArrayList<BNode<T>> list = new ArrayList<>();
      depthLeftOrder(list, this.root);
      BNode<T>[] array = (BNode<T>[]) new BNode[list.size()];
      return list.toArray(array);
   }

   private void depthLeftOrder(ArrayList<BNode<T>> list, BNode<T> node) {
		if(!node.isEmpty()){
			list.add(node);
			if(!node.isLeaf()){
				node.getChildren().forEach((element -> {
					depthLeftOrder(list, element);
				}));
			}
		}
	}

   @Override
   public int size() {
      return size(this.root);
   }

   private int size(BNode<T> node) {

      int size = node.size();
      for (BNode<T> n : node.getChildren()) {
         size += size(n);
      }
      return size;
   }

   @Override
   public BNodePosition<T> search(T element) {
      if (!root.isEmpty())
         return search(root, element);
      else
         return new BNodePosition<>();
   }

   private BNodePosition<T> search(BNode<T> node, T element) {
      BNodePosition<T> out = new BNodePosition<>();
      int indexOf = node.getElements().indexOf(element);
      if (indexOf != -1) {
         out = new BNodePosition<>(node, indexOf);
      } else {
         if (!node.isLeaf()) {
            for (BNode<T> no : node.getChildren()) {
               BNodePosition<T> aux = search(no, element);
               if (aux.node != null) {
                  out = aux;
                  break;
               }
            }
         }
      }
      return out;
   }

   @Override
   public void insert(T element) {
      //se o element n for null e não estiver na arvore
      if (element != null && this.search(element).node == null)
         insert(this.root, element);

   }

   private void insert(BNode<T> node, T element) {

      if (node.isLeaf()) {
         node.addElement(element);
      }

      else {
         int index = 0;
         while (index < node.getElements().size() && node.getElementAt(index).compareTo(element) < 0) {
            index++;
         }
         insert(node.getChildren().get(index), element);
      }

      if (node.getMaxKeys() < node.getElements().size()) {
         split(node);
      }

   }

   private void split(BNode<T> node) {

      int medianIndex = node.getElements().size() / 2;
      T middle = findMiddle(node);

      BNode<T> left = new BNode<T>(order);
      BNode<T> right = new BNode<T>(order);

      boolean addInLeft = true;
      for (T e : node.getElements()) {
         if (e.equals(middle)) {
            addInLeft = false;
         } else if (addInLeft) {
            left.addElement(e);
         } else {
            right.addElement(e);
         }
      }

      if (node == root) {
         BNode<T> newRoot = new BNode<T>(order);
         newRoot.addElement(middle);
         node.setParent(newRoot);
         root = newRoot;

         for (int i = 0; i < node.getChildren().size(); i++) {
            if (i <= medianIndex) {
               left.addChild(i, node.getChildren().get(i));
            } else {
               right.addChild(i - medianIndex - 1, node.getChildren().get(i));
            }
         }

         newRoot.addChild(0, left);
         newRoot.addChild(1, right);
         newRoot.getChildren().get(0).setParent(newRoot);
         newRoot.getChildren().get(1).setParent(newRoot);

      } else {

         promote(node, left, right);
      }

   }

   private void promote(BNode<T> node, BNode<T> left, BNode<T> right) {

      node.addChild(0, left);
      node.addChild(1, right);
      T medianElement = findMiddle(node);

      node.getElements().clear();
      node.addElement(medianElement);

      BNode<T> parent = node.getParent();

      if (parent != null) {
         node.getChildren().get(0).setParent(parent);
         node.getChildren().get(1).setParent(parent);
         int index = parent.getChildren().indexOf(node);
         parent.addElement(medianElement);
         parent.addChild(index, node.getChildren().get(0));
         parent.addChild(index + 1, node.getChildren().get(1));
         node.getChildren().get(0).setParent(parent);
         node.getChildren().get(1).setParent(parent);
         parent.getChildren().remove(node);
      }
   }

   // NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
   @Override
   public BNode<T> maximum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public BNode<T> minimum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public void remove(T element) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   private T findMiddle(BNode<T> node) {
      return node.getElements().get((node.getElements().size()) / 2);
   }
}
