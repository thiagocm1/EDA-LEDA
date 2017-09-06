package adt.avltree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}
	@Override
  	protected void rebalance(BSTNode<T> node) {
	int i = calculateBalance(node);
  		if (i < -1){
  			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0){
  				LRcounter++;
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
	
	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array == null || array.length == 0)
			return ;
		
		T[] order = this.order();
		List<T> list = new ArrayList<T>(array.length + order.length);
		Collections.addAll(list, array);
		Collections.addAll(list, (T[]) order);
		Collections.sort(list);
		this.root = new BSTNode<T>();

		Deque<Integer[]> fila = new LinkedList<Integer[]>();
		Integer[] elem = this.getVectorElement(0, list.size() - 1);
		fila.add(elem);
		int count = 0;
		while(count < list.size()) {
			elem = fila.remove();
			insert(list.get(elem[1]));
			count++;
			if (elem[0] != elem[1]) {
				fila.addLast(this.getVectorElement(elem[0], elem[1] - 1));
			}
			if (elem[1] != elem[2]) {
				fila.addLast(this.getVectorElement(elem[1] + 1, elem[2]));
			}
		}
	}

	private Integer[] getVectorElement(int inicio, int fim) {
		Integer[] result = new Integer[3];
		result[0] = inicio;
		result[1] = (inicio + fim) / 2;
		result[2] = fim;
		return result;
	}
	

}
