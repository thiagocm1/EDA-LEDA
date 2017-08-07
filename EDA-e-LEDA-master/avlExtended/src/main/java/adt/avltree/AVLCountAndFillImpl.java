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
		this.LLcounter =0;
		this.LRcounter = 0;
		this.RRcounter = 0;
		this.RLcounter =0;
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
	
	protected void rebalance(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return;
		int balance = calculateBalance(node);
		if (Math.abs(balance) <= 1)
			return;
		if (balance < 0) { // left pending
			int sonBalance = calculateBalance((BSTNode<T>) node.getLeft());
			if (sonBalance > 0) { // if zig-zag
				this.leftRotation((BSTNode<T>) node.getLeft());
				this.LRcounter++;
			} else {
				this.LLcounter++;
			}
			this.rightRotation(node);
		} else { // right peding
			int sonBalance = calculateBalance((BSTNode<T>) node.getRight());
			if (sonBalance < 0) { // if zig-zag
				this.rightRotation((BSTNode<T>) node.getRight());
				this.RLcounter++;
			} else {
				this.RRcounter++;
			}
			this.leftRotation(node);
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
		this.root = new BSTNode<T>(); // limpa Árvore ;)
		
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
