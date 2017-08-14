package adt.heap;

import java.util.Comparator;

import util.Util;

public class QueueHeap<T> extends HeapImpl<Integer> {
	
	public QueueHeap() {
	
		super((o1,o2) -> ((Comparable) o2).compareTo(o1)); 
		// TODO Auto-generated constructor stub
	}
	
	public void enqueue(T element){
		if(element == null){return;}
		if(isEmpty()){
			super.insert((Integer) element);
		}
		else{
			Integer root = rootElement();
			Integer helper = (Integer) element;
			if(root < helper){
				super.insert(helper);
			}
			else{
				super.insert(helper);
				Util.swap(getHeap(), 0, this.index); // errado mas work 
													// GARANTIR QUE O PRIMEIRO ELEMENTO SEMPRE FIQUE EM PRIMEIRO
			}
		}
	}
	
	public T dequeue(){
		T result;
		if(isEmpty()){result = null;}
		int helper = index;
		result = (T) extractRootElement();
		return result;
	}


	public T head(){
		T result;
		if(isEmpty()){
			result = null;
		}
		else{
			result = (T) rootElement();
		}
		return result;
	}
	
	private void invertHeap(T[] heap) {
	
		
	}

	public boolean isEmpty(){
		return super.isEmpty();
	}
}


